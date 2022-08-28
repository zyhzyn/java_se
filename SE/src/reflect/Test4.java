package reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

public class Test4 {
    public static void main(String[] args) throws Exception {
        File file = new File(Test3.class.getResource(".").toURI());
        File[] subs = file.listFiles(f -> f.getName().endsWith(".class"));
        for (File f : subs) {
            String fileName = f.getName();
            String className = fileName.substring(0, fileName.indexOf("."));
            //Class cls=Class.forName("reflect."+f.getName());
            Class cls = Class.forName(Test2.class.getPackage().getName() + "." + className);
            Object obj=cls.newInstance();
            Method[] methods=cls.getDeclaredMethods();
            for (Method method:methods){
                if (method.isAnnotationPresent(AutoRunMethod.class));
                System.out.println(method.getName()+"被调用了");
                method.invoke(obj);
            }
        }
    }
}
