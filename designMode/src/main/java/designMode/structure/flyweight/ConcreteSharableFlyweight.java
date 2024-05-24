package designMode.structure.flyweight;

public class ConcreteSharableFlyweight implements Flyweight{

    private final String intrinsicState;

    public ConcreteSharableFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("Intrinsic State = " + intrinsicState + ", Extrinsic State = " + extrinsicState);
    }
}
