package designMode.structure.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{

    public static void main(String[] args) {
        Composite composite = new Composite();
        Component leaf1 = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");

        composite.addChild(leaf1);
        composite.addChild(leaf2);
        composite.addChild(leaf3);

        composite.operation();
    }

    private List<Component> children = new ArrayList<>();

    public void addChild(Component component){
        children.add(component);
    }

    public void removeChild(Component component){
        children.remove(component);
    }

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}
