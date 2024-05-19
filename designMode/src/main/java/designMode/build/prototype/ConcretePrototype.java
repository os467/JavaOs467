package designMode.build.prototype;

public class ConcretePrototype implements Cloneable {

    public static void main(String[] args) {

        ConcretePrototype concretePrototype = new ConcretePrototype();

        try {
            ConcretePrototype prototype = (ConcretePrototype) concretePrototype.clone();

            System.out.println("是否新对象:"+ (prototype != concretePrototype) );

            System.out.println("是否浅拷贝:"+ (prototype.attachment == concretePrototype.attachment) );

            System.out.println(prototype);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    private int anInt = 1;

    private double aDouble = 2.2f;

    private String string = "str";

    private Attachment attachment = new Attachment();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "anInt=" + anInt +
                ", aDouble=" + aDouble +
                ", string='" + string + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}

class Attachment{

    private String str = "attachment";

    private boolean aBoolean = false;

    @Override
    public String toString() {
        return "Attachment{" +
                "str='" + str + '\'' +
                ", aBoolean=" + aBoolean +
                '}';
    }
}
