package designMode.build.singleton;

public class Singleton {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("单例模式(提前加载)："+instance);
    }


    //由于类加载的特性，static属性只会加载一次，因此static属性的对象也是唯一的
    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }

}
