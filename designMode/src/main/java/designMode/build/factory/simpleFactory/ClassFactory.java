package designMode.build.factory.simpleFactory;

import designMode.build.factory.product.Product;
import designMode.build.factory.product.ProductA;
import designMode.build.factory.product.ProductB;
import designMode.build.factory.product.ProductC;

import java.util.HashMap;
import java.util.Map;

public class ClassFactory {

    public static void main(String[] args) {
        ClassFactory classStaticFactory = new ClassFactory();
        //向静态工厂注册子类型
        classStaticFactory.registerProduct("A", ProductA.class);
        classStaticFactory.registerProduct("B", ProductB.class);
        classStaticFactory.registerProduct("C", ProductC.class);

        try {//创建实例
            Product a = classStaticFactory.create("A");
            Product b = classStaticFactory.create("B");
            Product c = classStaticFactory.create("C");
            System.out.println("简单工厂(反射):"+ a.getName());
            System.out.println("简单工厂(反射):"+ b.getName());
            System.out.println("简单工厂(反射):"+ c.getName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private Map<String,Class> registeredProducts = new HashMap<>();

    //向工厂注册一种新的类型
    public void registerProduct(String productId,Class<? extends Product> productClass){
        registeredProducts.put(productId,productClass);
    }

    //通过反射动态创建类的实例
    public Product create(String productId) throws IllegalAccessException, InstantiationException {
        Class productClass = registeredProducts.get(productId);
        return (Product) productClass.newInstance();
    }


}
