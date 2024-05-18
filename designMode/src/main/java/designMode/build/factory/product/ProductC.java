package designMode.build.factory.product;

public class ProductC implements Product{
    @Override
    public String getName() {
        return "产品C";
    }

    @Override
    public Product newInstance() {
        return new ProductC();
    }
}
