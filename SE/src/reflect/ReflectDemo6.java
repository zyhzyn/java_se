package reflect;

import java.lang.reflect.Method;

public class ReflectDemo6 {
    public static void main(String[] args) throws Exception {
        Class cla=Class.forName("reflect.Person");
        Object obj=cla.newInstance();
        Method method=cla.getDeclaredMethod("heihei");
        method.setAccessible(true);
        method.invoke(obj);
    }
}
