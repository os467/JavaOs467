package designMode.build.builder.directorBuilder;

public class Product2 extends Product{

    private String name;

    private Long id;

    private String desc;

    private Double price;

    public void addName(String name) {
        this.name = name;
    }

    public void addId(Long id) {
        this.id = id;
    }

    public void addDesc(String desc) {
        this.desc = desc;
    }

    public void addPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Product2{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
