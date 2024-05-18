package designMode.build.factory.abstractFactory.methodFactory;

import designMode.build.factory.product.Product;
import designMode.build.factory.product.ProductB;

public abstract class ProductFactory {

    public static void main(String[] args) {
        //创建子类工厂
        ProductFactory productAFactory = new ProductAFactory();
        Product a = productAFactory.create();
        System.out.println("工厂方法模式:"+a.getName());

        //匿名具体工厂方法模式，创建实例C
        ProductFactory productBFactory = new ProductFactory() {
            @Override
            protected Product create() {
                return new ProductB();
            }
        };

        Product b = productBFactory.create();
        System.out.println("匿名具体工厂方法模式:"+b.getName());

    }

    //抽象方法，将具体创建实例的逻辑交由具体工厂类实现
    protected abstract Product create();

}
