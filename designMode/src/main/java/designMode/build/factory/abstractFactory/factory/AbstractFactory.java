package designMode.build.factory.abstractFactory.factory;

public abstract class AbstractFactory {

    public static void main(String[] args) {

        //创建具体工厂1
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();

        System.out.println("factory1创建的具体产品:"+productA1.getName());
        System.out.println("factory1创建的具体产品:"+productB1.getName());

        //创建具体工厂2
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();

        System.out.println("factory2创建的具体产品:"+productA2.getName());
        System.out.println("factory2创建的具体产品:"+productB2.getName());
    }

    protected abstract AbstractProductA createProductA();

    protected abstract AbstractProductB createProductB();


}

abstract class AbstractProductA {

    protected abstract String getName();

}

abstract class AbstractProductB {

    protected abstract String getName();

}

class ConcreteFactory1 extends AbstractFactory {


    @Override
    protected AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    protected AbstractProductB createProductB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 extends AbstractFactory{

    @Override
    protected AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    protected AbstractProductB createProductB() {
        return new ProductB2();
    }
}

class ProductA1 extends AbstractProductA {

    @Override
    protected String getName() {
        return "产品A1";
    }
}

class ProductA2 extends AbstractProductA {

    @Override
    protected String getName() {
        return "产品A2";
    }
}

class ProductB1 extends AbstractProductB {

    @Override
    protected String getName() {
        return "产品B1";
    }
}

class ProductB2 extends AbstractProductB {

    @Override
    protected String getName() {
        return "产品B2";
    }
}

