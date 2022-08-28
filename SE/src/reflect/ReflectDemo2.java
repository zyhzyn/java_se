package reflect;

import java.util.Scanner;

public class ReflectDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Person person=new Person();
        System.out.println(person);

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入一个类名:");
        String className=scanner.nextLine();
        Class cla=Class.forName(className);
        Object obj=cla.newInstance();
        System.out.println(obj);
    }
}
