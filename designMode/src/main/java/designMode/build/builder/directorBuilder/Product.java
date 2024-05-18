package designMode.build.builder.directorBuilder;

public abstract class Product {

    public abstract Double getPrice();

    public abstract String getName();

    public abstract Long getId();

    public abstract String getDesc();

    @Override
    public String toString() {
        return super.toString();
    }

}
