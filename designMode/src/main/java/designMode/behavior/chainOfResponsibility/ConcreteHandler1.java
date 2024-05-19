package designMode.behavior.chainOfResponsibility;

public class ConcreteHandler1 implements Handler {

    private Handler handler;

    @Override
    public void handleRequest(Request request) {
        if (canHandle(request)){
            //处理请求
            System.out.println(request.getName());
        }else {
            handler.handleRequest(request);
        }
    }

    private boolean canHandle(Request request) {
        if (request.getName().equals("请求类型1")){
            return true;
        }
        return false;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.handler = handler;
    }
}
