package designMode.build.factory.product;

public interface Product {

    String getName();

    Product newInstance();

}
