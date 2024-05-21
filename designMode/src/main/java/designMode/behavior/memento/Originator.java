package designMode.behavior.memento;

//发起者是要被保存状态的类
public class Originator {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveState(){
        return new Memento(state);
    }

    public void restoreState(Memento memento){
        this.state = memento.getState();
    }


    //备忘录类作为静态内部类，以便发起者访问，同时内部方法对外界是不公开的
    public static class Memento {

        private final String state;

        public Memento(String state) {
            this.state = state;
        }

        private String getState(){
            return state;
        }

    }

}
