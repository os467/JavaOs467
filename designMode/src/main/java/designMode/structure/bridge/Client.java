package designMode.structure.bridge;


public class Client {

    public static void main(String[] args) {

        Implementation implA = new SpecificImplA();
        Implementation implB = new SpecificImplB();

        Abstraction abstractionA = new Concrete(implA);

        Abstraction abstractionB = new Concrete(implB);


        abstractionA.operation();
       
        abstractionB.operation();
    }

}
