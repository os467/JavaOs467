package designMode.behavior.command;

public class Client {

    public static void main(String[] args) {

        CommandInvoker invoker = new CommandInvoker();

        //添加具体的命令，每个命令都保留一份命令接收者的引用用于执行具体的操作
        invoker.addCommand(new ConcreteCommandA(new CommandAReceiver()));
        invoker.addCommand(new ConcreteCommandB(new CommandBReceiver()));
        invoker.addCommand(new ConcreteCommandC(new CommandCReceiver()));

        //执行命令
        invoker.executeCommands();
    }

}
