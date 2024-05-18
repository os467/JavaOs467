package designMode.build.builder.chainTypeBuilder;

public class Product {

    public static class Builder{

        private Product product = new Product();

        public Builder setName(String name){
            product.setName(name);
            return this;
        }

        public Builder setDesc(String desc){
            product.setDesc(desc);
            return this;
        }

        public Builder setPrice(Double price){
            product.setPrice(price);
            return this;
        }

        public Builder setType(String type){
            product.setType(type);
            return this;
        }

        public Builder setVersion(String version){
            product.setVersion(version);
            return this;
        }

        public Product build(){
            return product;
        }

    }

    private String name;

    private String desc;

    private Double price;

    private String type;

    private String version;

    private Product(){}

    private void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
