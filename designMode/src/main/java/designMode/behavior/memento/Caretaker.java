package designMode.behavior.memento;

public class Caretaker {

    public static void main(String[] args) {

        Originator originator = new Originator();

        originator.setState("state1");
        //保存状态
        Originator.Memento memento = originator.saveState();
        //设置新状态
        originator.setState("state2");
        //恢复状态
        originator.restoreState(memento);

        System.out.println("当前状态为:"+originator.getState());

    }

}
