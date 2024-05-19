package designMode.build.prototype;

import java.io.*;

public class DeepCloneConcretePrototype implements Serializable {

    private static final long serialVersionUID = 1236212586122492189L;

    public static void main(String[] args) {

        DeepCloneConcretePrototype deepCloneConcretePrototype = new DeepCloneConcretePrototype();

        DeepCloneConcretePrototype prototype = null;
        try {
            prototype = deepCloneConcretePrototype.deepClone();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("是否新对象:"+ (prototype != deepCloneConcretePrototype) );

        System.out.println("是否深拷贝:"+ (prototype.attachment != deepCloneConcretePrototype.attachment) );

        System.out.println(prototype);

    }

    private DeepCloneConcretePrototype deepClone() throws IOException, ClassNotFoundException {
        //序列化
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (DeepCloneConcretePrototype)ois.readObject();
    }

    private int anInt = 1;

    private double aDouble = 2.2f;

    private String string = "str";

    private DeepCloneAttachment attachment = new DeepCloneAttachment();

    @Override
    public String toString() {
        return "DeepCloneConcretePrototype{" +
                "anInt=" + anInt +
                ", aDouble=" + aDouble +
                ", string='" + string + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}

class DeepCloneAttachment implements Serializable{

    private String str = "attachment";

    private boolean aBoolean = false;

    @Override
    public String toString() {
        return "DeepCloneAttachment{" +
                "str='" + str + '\'' +
                ", aBoolean=" + aBoolean +
                '}';
    }
}
