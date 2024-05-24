package designMode.structure.decorator;

public class ConcreteDecorator extends AbstractDecorator{

    public static void main(String[] args) {
        Component component = new ComponentImpl();

        Decorator decorator = new ConcreteDecorator(component);

        decorator.operation();
    }

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("前置增强方法");
        super.operation();
        System.out.println("后置增强方法");
    }
}
