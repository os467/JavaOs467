package designMode.behavior.state;

public class ConcreteState1 implements State{
    @Override
    public void handle(Context context) {
        System.out.println("状态1 - 触发事件1");
        context.setState(new ConcreteState2());
    }
}

class ConcreteState2 implements State{
    @Override
    public void handle(Context context) {
        System.out.println("状态2 - 触发事件2");
        context.setState(new ConcreteState3());
    }
}

class ConcreteState3 implements State{
    @Override
    public void handle(Context context) {
        System.out.println("状态3 - 触发事件3");
        context.setState(new ConcreteState1());
    }
}
