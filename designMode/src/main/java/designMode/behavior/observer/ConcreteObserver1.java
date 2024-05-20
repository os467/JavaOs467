package designMode.behavior.observer;

public class ConcreteObserver1 implements Observer{
    @Override
    public void update(String message) {
        System.out.println("观察者1接收消息:"+message);
    }
}

class ConcreteObserver2 implements Observer{
    @Override
    public void update(String message) {
        System.out.println("观察者2接收消息:"+message);
    }
}

class ConcreteObserver3 implements Observer{
    @Override
    public void update(String message) {
        System.out.println("观察者2接收消息:"+message);
    }
}