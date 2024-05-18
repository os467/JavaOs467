package designMode.build.builder.chainTypeBuilder;

public class UseExample {

    public static void main(String[] args) {
        Product product = new Product.Builder()
                .setName("product-1")
                .setDesc("description for product-1")
                .setPrice(2.9D)
                .setType("type-1")
                .setVersion("version 1.0")
                .build();

        System.out.println(product);
    }

}
