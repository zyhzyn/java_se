package reflect;

import java.lang.reflect.Method;

public class ReflectDemo5 {
    public static void main(String[] args) throws Exception {
        Class cla=Class.forName("reflect.Person");
        Object obj=cla.newInstance();
        Method m1=cla.getMethod("say", String.class);
        m1.invoke(obj,"你好");
        Method m2=cla.getMethod("say", String.class, int.class);
        m2.invoke(obj,"呵呵",5);
    }
}
