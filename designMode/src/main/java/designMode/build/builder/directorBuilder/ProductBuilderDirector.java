package designMode.build.builder.directorBuilder;

public class ProductBuilderDirector {


    public static void main(String[] args) {
        ProductBuilderDirector director = new ProductBuilderDirector();

        Product product1 = director.buildProduct1(new Product1Builder());
        Product product2 = director.buildProduct2(new Product2Builder());

        System.out.println("director协助下完成product1的构建:"+product1);
        System.out.println("director协助下完成product2的构建:"+product2);
    }

    public Product buildProduct1(ProductBuilder builder) {
        builder.buildProduct();
        builder.addId(System.currentTimeMillis());
        builder.addName("product-1");
        builder.addDesc("product-1 version 1.0");
        builder.addPrice(3.5D);
        return builder.getProduct();
    }

    public Product buildProduct2(ProductBuilder builder) {
        builder.buildProduct();
        builder.addId(System.currentTimeMillis());
        builder.addName("product-2");
        builder.addDesc("product-2 version 1.0");
        builder.addPrice(8.5D);
        return builder.getProduct();
    }


}
