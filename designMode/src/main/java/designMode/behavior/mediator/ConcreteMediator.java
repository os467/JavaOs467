package designMode.behavior.mediator;

import java.util.HashMap;
import java.util.Map;

public class ConcreteMediator implements Mediator{

    private Map<String,Colleague> colleagues = new HashMap<>();

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague c1 = new ConcreteColleague1(mediator);
        Colleague c2 = new ConcreteColleague2(mediator);
        Colleague c3 = new ConcreteColleague3(mediator);

        mediator.register("c1",c1);
        mediator.register("c2",c2);
        mediator.register("c3",c3);

        c1.sendTo("c2","hello c2,im c1");
        c2.sendTo("c3","hello c3,im c2");
    }

    public void register(String id,Colleague colleague){
        colleagues.put(id,colleague);
    }

    public void sendMessageTo(String id,String message){
        Colleague colleague = colleagues.get(id);
        if (colleague != null){
            colleague.receive(message);
        }
    }

}
