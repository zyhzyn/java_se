package reflect;

public class ReflectDemo1 {
    public static void main(String[] args) {
        Class cls=String.class;
        String name=cls.getName();
        System.out.println(name);
        name=cls.getSimpleName();
        System.out.println(name);
    }
}
