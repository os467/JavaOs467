package designMode.behavior.visitor;

public class Client {

    public static void main(String[] args) {

        Element[] elements = {new ConcreteElementA(),new ConcreteElementB()};

        Visitor visitor1 = new ConcreteVisitor1();
        Visitor visitor2 = new ConcreteVisitor2();

        for (Element element : elements) {
            element.accept(visitor1);
            element.accept(visitor2);
        }
    }

}
