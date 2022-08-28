package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectDemo4 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        Person p=new Person();
        p.sayHello();

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入类名：");
        String className=scanner.nextLine();
        System.out.println("请输入方法名：");
        String methodName=scanner.nextLine();

        //Class cla=Class.forName("reflect.Person");
        Class cla=Class.forName(className);
        Object obj=cla.newInstance();

        //Method method=cla.getMethod("sayHello");
        Method method=cla.getMethod(methodName);
        method.invoke(obj);

    }
}
