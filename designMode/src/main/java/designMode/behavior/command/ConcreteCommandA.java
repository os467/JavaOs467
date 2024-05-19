package designMode.behavior.command;

public class ConcreteCommandA implements Command{

    private CommandAReceiver receiver;

    public ConcreteCommandA(CommandAReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.operation();
    }

}

class ConcreteCommandB implements Command{

    private CommandBReceiver receiver;

    public ConcreteCommandB(CommandBReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.operation();
    }
}

class ConcreteCommandC implements Command{

    private CommandCReceiver receiver;

    public ConcreteCommandC(CommandCReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.operation();
    }
}
