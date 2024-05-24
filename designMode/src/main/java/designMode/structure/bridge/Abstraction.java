package designMode.structure.bridge;

public abstract class Abstraction {

    protected Implementation impl;

    public Abstraction(Implementation impl) {
        this.impl = impl;
    }

    public abstract void operation();

}

//扩充抽象类，可以水平扩充
abstract class Refined extends Abstraction{

    public Refined(Implementation impl) {
        super(impl);
    }

    public void extendedOperation(){
        System.out.println("抽象类拓展的方法");
    }

    @Override
    public void operation() {
        impl.operation();
    }

}

//具体类，继承扩充的抽象类
class Concrete extends Refined {

    public Concrete(Implementation impl) {
        super(impl);
    }

    @Override
    public void operation() {
        extendedOperation();
        super.operation();
    }
}