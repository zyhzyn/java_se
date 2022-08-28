package reflect;
@AutoRunClass
public class Person {
    String name;
    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @AutoRunMethod(1)
    public void sayHello(){
        System.out.println(name+"说：hello");
    }
    @AutoRunMethod(1)
    public void sing(){
        System.out.println(name+"正在唱歌");
    }
    public void playGame(){
        System.out.println(name+"正在打游戏");
    }
    public void say(String info){
        System.out.println(name+"说："+info);
    }
    public void say(String info ,int count){
        for (int i = 1; i <=count ; i++) {
            System.out.println(name+"第"+i+"次说："+info);
        }
    }
    @AutoRunMethod
    public void eat(){
        System.out.println(name+":正在干饭");
    }
    private void heihei(){
        System.out.println("我是私有方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
