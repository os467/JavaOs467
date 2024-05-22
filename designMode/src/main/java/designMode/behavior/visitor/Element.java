package designMode.behavior.visitor;

public interface Element {
    void accept(Visitor visitor);
}

class ConcreteElementA implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA(){
        System.out.println("具体元素A的操作");
    }
}

class ConcreteElementB implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB(){
        System.out.println("具体元素B的操作");
    }
}