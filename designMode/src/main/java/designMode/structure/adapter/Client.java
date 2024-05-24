package designMode.structure.adapter;

public class Client {

    public static void main(String[] args) {

        Adapter adapter = new Adapter();

        adapter.oldMethod();
        adapter.newMethod();

    }

}
