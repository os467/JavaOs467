package designMode.behavior.visitor;

public interface Visitor {

    void visit(ConcreteElementA concreteElementA);

    void visit(ConcreteElementB concreteElementB);
}

class ConcreteVisitor1 implements Visitor{

    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("访问者1访问元素A的逻辑");
        concreteElementA.operationA();
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("访问者1访问元素B的逻辑");
        concreteElementB.operationB();
    }
}

class ConcreteVisitor2 implements Visitor{


    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("访问者2访问元素A的逻辑");
        concreteElementA.operationA();
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("访问者2访问元素B的逻辑");
        concreteElementB.operationB();
    }
}