package designMode.structure.decorator;

public interface Decorator {

    void operation();

}

class AbstractDecorator implements Decorator{

    private Component component;

    public AbstractDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        //调用原始方法
        component.originalMethod();
    }

}