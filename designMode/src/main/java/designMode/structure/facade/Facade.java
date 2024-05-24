package designMode.structure.facade;

public interface Facade {
    void operation();
}

class FacadeImpl implements Facade{

    private Subsystem1 subsystem1;
    private Subsystem2 subsystem2;
    private Subsystem3 subsystem3;

    public FacadeImpl(Subsystem1 subsystem1, Subsystem2 subsystem2, Subsystem3 subsystem3) {
        this.subsystem1 = subsystem1;
        this.subsystem2 = subsystem2;
        this.subsystem3 = subsystem3;
    }

    @Override
    public void operation() {
        subsystem1.operation1();
        subsystem2.operation2();
        subsystem3.operation3();
    }
}
