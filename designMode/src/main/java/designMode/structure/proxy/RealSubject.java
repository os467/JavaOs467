package designMode.structure.proxy;

public class RealSubject implements Subject {

    @Override
    public void method() {
        System.out.println("方法被执行了");
    }
}
