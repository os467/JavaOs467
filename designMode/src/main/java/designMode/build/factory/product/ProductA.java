package designMode.build.factory.product;

public class ProductA implements Product{
    @Override
    public String getName() {
        return "产品A";
    }

    @Override
    public Product newInstance() {
        return new ProductA();
    }
}
