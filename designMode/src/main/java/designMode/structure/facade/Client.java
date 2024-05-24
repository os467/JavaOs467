package designMode.structure.facade;

public class Client {

    public static void main(String[] args) {
        FacadeImpl facade = new FacadeImpl(new Subsystem1(), new Subsystem2(), new Subsystem3());

        facade.operation();

    }

}
