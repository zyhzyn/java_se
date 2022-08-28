package reflect;

import java.io.File;
import java.net.URISyntaxException;

public class Test3 {
    public static void main(String[] args) throws Exception {
        File file = new File(Test3.class.getResource(".").toURI());
        File[] subs = file.listFiles(f -> f.getName().endsWith(".class"));
        for (File f : subs) {
            String fileName = f.getName();
            String className = fileName.substring(0, fileName.indexOf("."));
            //Class cls=Class.forName("reflect."+f.getName());
            Class cls = Class.forName(Test2.class.getPackage().getName() + "." + className);
            if (cls.isAnnotationPresent(AutoRunClass.class)){
                System.out.println("正在实例化:"+cls.getName());
                Object obj=cls.getCanonicalName();
                System.out.println(obj);
            }
        }
    }
}
