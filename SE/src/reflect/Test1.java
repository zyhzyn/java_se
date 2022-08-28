package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test1 {
    public static void main(String[] args) throws Exception {
        Class cls=Class.forName("reflect.Person");
        Object obj=cls.newInstance();
        String line=cls.getCanonicalName();
        System.out.println(line);
        Method[] methods=cls.getDeclaredMethods();
        for (Method method:methods){
            if (method.getParameterCount()==0&&method.getModifiers()== Modifier.PUBLIC){
                System.out.println("自动调用："+method.getName()+"()");
                method.invoke(obj);
            }
        }
    }
}
