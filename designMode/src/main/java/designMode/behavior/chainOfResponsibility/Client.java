package designMode.behavior.chainOfResponsibility;

public class Client {

    public static void main(String[] args) {

        //创建具体处理器
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        ConcreteHandler3 handler3 = new ConcreteHandler3();
        //设置责任链
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        //处理请求
        handler1.handleRequest(new Request("请求类型1"));
        handler1.handleRequest(new Request("请求类型2"));
        handler1.handleRequest(new Request("请求类型3"));

        //处理请求-失败
        handler1.handleRequest(new Request("请求类型100"));

    }

}
