package designMode.behavior.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{

    public static void main(String[] args) {

        //创建可观察的具体类
        Subject subject = new ConcreteSubject();

        //注册观察者
        subject.attach(new ConcreteObserver1());
        subject.attach(new ConcreteObserver2());
        subject.attach(new ConcreteObserver3());

        //通知所有观察者
        subject.notifyObserver("ConcreteSubject的状态改变了");

    }


    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(String message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }

}
