package designMode.build.singleton;

public class DelaySingleton {

    public static void main(String[] args) {
        DelaySingleton instance = DelaySingleton.getInstance();
        System.out.println("单例模式(延迟加载)："+instance);
    }

    //全局单例
    private static DelaySingleton instance;

    //私有构造方法
    private DelaySingleton(){}

    //双重检查保证多线程下的单例唯一性
    public static DelaySingleton getInstance() {
        if (instance == null){
            synchronized (DelaySingleton.class){
                if (instance == null){
                    instance = new DelaySingleton();
                }
            }
        }
        return instance;
    }
}
