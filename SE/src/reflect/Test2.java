package reflect;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test2 {
    public static void main(String[] args) throws Exception {
        File file=new File(Test2.class.getResource(".").toURI());
        File[] subs=file.listFiles(f->f.getName().endsWith(".class"));
        for (File f:subs){
            String fileName=f.getName();
            String className=fileName.substring(0,fileName.indexOf("."));
            //Class cls=Class.forName("reflect."+f.getName());
            Class cls=Class.forName(Test2.class.getPackage().getName()+"."+className);
            Object obj=cls.newInstance();

            Method[] methods=cls.getMethods();
            for (Method method:methods) {
                if (method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC&&method.getName().contains("s")){
                    System.out.println("自动调用："+method.getName()+"()");
                    method.setAccessible(true);
                    method.invoke(obj);
                }
            }
        }

    }
}
