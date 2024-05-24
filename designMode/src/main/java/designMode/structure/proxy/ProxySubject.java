package designMode.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxySubject {

    public static void main(String[] args) {
        //JDK动态代理
        ProxySubject proxyCreator = new ProxySubject(new RealSubject());
        Subject proxy = proxyCreator.createProxy();

        proxy.method();
    }

    //被代理类
    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    public Subject createProxy() {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("增强前置方法");
                Object o = method.invoke(realSubject, args);
                System.out.println("增强后置方法");
                return o;
            }
        };

        return (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),
                invocationHandler);
    }

}
