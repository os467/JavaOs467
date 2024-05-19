package designMode.behavior.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    private List<Command> commandList = new ArrayList<>();

    //清空命令
    public void clearCommands(){
        commandList.clear();
    }

    //添加命令
    public void addCommand(Command command){
        commandList.add(command);
    }

    //执行命令
    public void executeCommands(){
        for (Command command : commandList) {
            command.execute();
        }
    }

}
