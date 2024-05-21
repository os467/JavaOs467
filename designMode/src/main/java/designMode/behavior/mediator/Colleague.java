package designMode.behavior.mediator;

public interface Colleague {
    void receive(String message);

    void sendTo(String id, String message);
}
