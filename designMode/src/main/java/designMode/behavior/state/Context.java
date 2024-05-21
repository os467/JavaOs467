package designMode.behavior.state;

public class Context {

    public static void main(String[] args) {

        Context context = new Context(new ConcreteState1());

        for (int i = 0; i < 10; i++) {
            context.request();
        }

    }

    //当前状态
    private State state;

    public Context(State state) {
        this.state = state;
    }

    //请求改变状态
    public void request(){
        state.handle(this);
    }

    public void setState(State state) {
        this.state = state;
    }
}
