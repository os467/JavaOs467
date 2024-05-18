package designMode.build.factory.abstractFactory.methodFactory;

import designMode.build.factory.product.Product;
import designMode.build.factory.product.ProductA;

public class ProductAFactory extends ProductFactory{

    @Override
    protected Product create() {
        return new ProductA();
    }

}
