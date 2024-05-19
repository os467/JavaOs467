package designMode.behavior.chainOfResponsibility;

public interface Handler {

    void handleRequest(Request request);

    void setNextHandler(Handler handler);

}
