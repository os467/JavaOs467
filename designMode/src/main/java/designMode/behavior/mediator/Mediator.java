package designMode.behavior.mediator;

public interface Mediator {
    void register(String id,Colleague colleague);

    void sendMessageTo(String id,String message);
}
