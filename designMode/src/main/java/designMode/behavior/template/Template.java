package designMode.behavior.template;

public abstract class Template {

    public static void main(String[] args) {
        Template t1 = new Template1();
        Template t2 = new Template2();
        t1.info();
        t2.info();

        t1.doSomething();
        t2.doSomething();

    }

    public void info(){
        System.out.println("This class is a subclass of the Template class.");
    }

    public abstract void doSomething();

}

class Template1 extends Template{

    @Override
    public void doSomething() {
        System.out.println("I am template1.");
    }

}


class Template2 extends Template{

    @Override
    public void doSomething() {
        System.out.println("I am template2.");
    }

}