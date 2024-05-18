package designMode.build.factory.product;

public class ProductB implements Product{
    @Override
    public String getName() {
        return "产品B";
    }

    @Override
    public Product newInstance() {
        return new ProductB();
    }
}
