package designMode.build.builder.directorBuilder;

public interface ProductBuilder {

    void buildProduct();

    void addName(String name);

    void addId(Long id);

    void addDesc(String desc);

    void addPrice(Double price);

    Product getProduct();
}
