package designMode.build.builder.directorBuilder;



public class Product1Builder implements ProductBuilder{

    private Product1 product;

    @Override
    public void buildProduct() {
      product = new Product1();
    }

    @Override
    public void addName(String name) {
        product.addName(name);
    }

    @Override
    public void addId(Long id) {
        product.addId(id);
    }

    @Override
    public void addDesc(String desc) {
        product.addDesc(desc);
    }

    @Override
    public void addPrice(Double price) {
        product.addPrice(price);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
