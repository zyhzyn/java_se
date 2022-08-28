package reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

public class Test5 {
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
                if (method.isAnnotationPresent(AutoRunMethod.class)){
                    AutoRunMethod arm=method.getAnnotation(AutoRunMethod.class);
                    int num=arm.value();
                    System.out.println("自动调用:"+method.getName()+"()"+num+"次");
                    for (int i = 0; i <num ; i++) {
                        method.invoke(obj);
                    }

                    }
                }

            }
    }
}
