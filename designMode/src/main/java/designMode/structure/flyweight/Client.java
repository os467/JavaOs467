package designMode.structure.flyweight;

public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();

        Flyweight fw1 = factory.getFlyweight("state1");
        Flyweight fw2 = factory.getFlyweight("state1");

        fw1.operation("state1");
        fw2.operation("state2");

    }
}