package designMode.behavior.mediator;

public class ConcreteColleague1 implements Colleague{

    private Mediator mediator;

    public ConcreteColleague1(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void receive(String message) {
        System.out.println(this+"接收到消息:"+message);
    }

    @Override
    public void sendTo(String id, String message) {
        mediator.sendMessageTo(id,message);
    }
}

class ConcreteColleague2 implements Colleague{

    private Mediator mediator;

    public ConcreteColleague2(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void receive(String message) {
        System.out.println(this+"接收到消息:"+message);
    }

    @Override
    public void sendTo(String id, String message) {
        mediator.sendMessageTo(id,message);
    }
}

class ConcreteColleague3 implements Colleague{

    private Mediator mediator;

    public ConcreteColleague3(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void receive(String message) {
        System.out.println(this+"接收到消息:"+message);
    }

    @Override
    public void sendTo(String id, String message) {
        mediator.sendMessageTo(id,message);
    }
}
