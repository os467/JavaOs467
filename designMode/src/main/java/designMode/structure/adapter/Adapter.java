package designMode.structure.adapter;

public class Adapter extends OldClass implements Target {

    @Override
    public void newMethod() {
        System.out.println("新的接口方法");
    }

}
