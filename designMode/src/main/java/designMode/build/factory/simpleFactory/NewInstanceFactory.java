package designMode.build.factory.simpleFactory;

import designMode.build.factory.product.Product;
import designMode.build.factory.product.ProductA;
import designMode.build.factory.product.ProductB;
import designMode.build.factory.product.ProductC;

import java.util.HashMap;
import java.util.Map;

public class NewInstanceFactory {

    public static void main(String[] args) {
        NewInstanceFactory newInstanceStaticFactory = new NewInstanceFactory();
        //向工厂注册子类型
        newInstanceStaticFactory.registerProduct("A", new ProductA());
        newInstanceStaticFactory.registerProduct("B", new ProductB());
        newInstanceStaticFactory.registerProduct("C", new ProductC());

        Product a = newInstanceStaticFactory.create("A");
        Product b = newInstanceStaticFactory.create("B");
        Product c = newInstanceStaticFactory.create("C");
        System.out.println("简单工厂(实例):"+ a.getName());
        System.out.println("简单工厂(实例):"+ b.getName());
        System.out.println("简单工厂(实例):"+ c.getName());



    }

    private Map<String,Product> registeredProducts = new HashMap<>();

    //向工厂注册新的实例
    public void registerProduct(String productId,Product product){
        registeredProducts.put(productId,product);
    }

    //通过保存的实例创建新的实例
    public Product create(String productId){
        Product product = registeredProducts.get(productId);
        if (product != null){
            return product.newInstance();
        }
        return null;
    }


}
