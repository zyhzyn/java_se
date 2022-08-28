package com.webserver.controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理和用户相关的业务类
 */
@Controller
public class UserController {
    private static File userDir;

    static {
        userDir = new File("./users");
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    @RequestMapping("/userList")
    public void userList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理动态页面!!!!!!!!!!!!!!!!!!!");
        /*
            1:读取users目录下的所有obj文件并反序列化然后将得到的所有User对象存入一个List集合
            2:生成HTML页面并将所用用户信息体现在其中将其发送给浏览器
         */
        //1
        List<User> userList = new ArrayList<>();
        //1.1获取users目录中的所有obj文件
        File[] subs = userDir.listFiles(f -> f.getName().endsWith(".obj"));
        //1.2将每个文件都反序列化得到User对象
        for (File file : subs) {
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                userList.add(user);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userList);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println("<!DOCTYPE html>");
        pw.println("<html lang=\"en\">");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\">");
        pw.println("<title>用户列表</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<center>");
        pw.println("<h1>用户列表</h1>");
        pw.println("<table border=\"1\">");
        pw.println("<tr>");
        pw.println("<td>用户名</td>");
        pw.println("<td>密码</td>");
        pw.println("<td>昵称</td>");
        pw.println("<td>年龄</td>");
        pw.println("<td>操作</td>");
        pw.println("</tr>");

        for (User user : userList) {
            pw.println("<tr>");
            pw.println("<td>" + user.getUsername() + "</td>");
            pw.println("<td>" + user.getPassword() + "</td>");
            pw.println("<td>" + user.getNickname() + "</td>");
            pw.println("<td>" + user.getAge() + "</td>");
            pw.println("<td><a href='/deleteUser?username=" + user.getUsername() + "'>删除</a></td>");
            pw.println("</tr>");
        }

        pw.println("</table>");
        pw.println("</center>");
        pw.println("</body>");
        pw.println("</html>");


    }

    @RequestMapping("/loginUser")
//    public void login(HttpServletRequest request, HttpServletResponse response) {
    public void login(String username,String password, HttpServletResponse response) {
        //1
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        System.out.println(username+","+password);
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("/login_info_error.html");
            return;
        }

        //2
        //根据登录用户的用户名去users目录下寻找该用户信息
        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//文件存在则说明该用户存在(用户名输入正确)
            //反序列化文件中该用户曾经的注册信息
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                //比较登录密码和注册时输入的密码是否一致
                // a = "abc123"    b = "AbC123"
                //a.equals(b) ==> false
                //a.equalsIgnoreCase(b) ==> true
                if (user.getPassword().equals(password)) {
                    //登录成功
                    response.sendRedirect("/login_success.html");
                    return;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //登录失败
        response.sendRedirect("/login_fail.html");
    }

    @RequestMapping("/regUser")
//    public void reg(HttpServletRequest request, HttpServletResponse response) {
    public void reg(User user, HttpServletResponse response) {
        //1获取表单信息
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String nickname = request.getParameter("nickname");
//        String ageStr = request.getParameter("age");
//        System.out.println(username + "," + password + "," + nickname + "," + ageStr);
//
//        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
//                nickname == null || nickname.isEmpty() || ageStr == null || ageStr.isEmpty() ||
//                !ageStr.matches("[0-9]+")) {
//            response.sendRedirect("/reg_info_error.html");
//            return;
//        }
//
//        int age = Integer.parseInt(ageStr);
//        User user = new User(username, password, nickname, age);
//        File file = new File(userDir, username + ".obj");

        File file = new File(userDir, user.getUsername() + ".obj");
        if (file.exists()) {//文件已经存在说明是重复用户
            response.sendRedirect("/have_user.html");
            return;
        }

        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/reg_success.html");

    }
}
