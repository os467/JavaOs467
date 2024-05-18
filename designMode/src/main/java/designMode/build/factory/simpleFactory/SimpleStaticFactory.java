package designMode.build.factory.simpleFactory;

import designMode.build.factory.product.Product;
import designMode.build.factory.product.ProductA;
import designMode.build.factory.product.ProductB;
import designMode.build.factory.product.ProductC;

public class SimpleStaticFactory {

    public enum ProductType{
        A,B,C
    }

    public static Product create(ProductType productType){
        if (productType.equals(ProductType.A)){
            return new ProductA();
        }
        else if (productType.equals(ProductType.B)){
            return new ProductB();
        }
        else if (productType.equals(ProductType.C)){
            return new ProductC();
        }
        return null;
    }

}
