package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo3 {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Person p=new Person("张三",22);
        System.out.println(p);

        Class cls=Class.forName("reflect.Person");
        Constructor c=cls.getConstructor(String.class,int.class);
        Object obj=c.newInstance("李四",21);
        System.out.println(obj);
    }
}
