package duke.execution;

import duke.task.Task;
/**
 * Command class of Duke.
 * Represents a command that the program executes depending on user input.
 */
public class Command {
    String[] info;
    private boolean isAdd;
    private boolean isDelete;
    private boolean isDone;
    private boolean isFind;
    private boolean isExit = false;

    /**
     * Constructor that contains information from the user input
     * @param info information given by user
     * @param add if this is an add command
     * @param delete if this is a delete command
     * @param done if this is a done command
     */
    public Command(String[] info, boolean add, boolean delete, boolean done, boolean find){
        this.info = info;
        this.isAdd = add;
        this.isDelete = delete;
        this.isDone = done;
        this.isFind = find;
    }

    /**
     * Carries out the command by editing TaskManager, FileManager and using UserInterface objects
     * @param tasks TaskManager of the Duke instance
     * @param ui UserInterface object
     * @param fileManager FileManager of the Duke instance
     * @throws DukeException
     */
    public void execute(TaskManager tasks, UserInterface ui, FileManager fileManager) throws DukeException{
        if(isAdd){
            Task task = tasks.addNewTask(info);
            fileManager.saveToFile(task);
            ui.printAdd(task, tasks.getSize());
            return;
        }else if(isDelete){
            Task task = tasks.delete(info[1]);
            fileManager.fileDelete(Integer.parseInt(info[1]) - 1);
            ui.printDelete(task, tasks.getSize());
            return;
        }else if(isDone){
            Task task = tasks.done(info[1]);
            fileManager.fileSetDone(Integer.parseInt(info[1]) - 1);
            ui.printDone(task);
            return;
        }else if(isFind){
            String foundList = tasks.findMatch(info[1]);
            ui.printFind(foundList);
        }
        if(info[0].equals("list")){
            ui.printList(tasks.generateList());
        }else if(info[0].equals("bye")){
            this.isExit = true;
        }
    }

    public String executeGui(TaskManager tasks, UserInterface ui, FileManager fileManager) throws DukeException{
        if(isAdd){
            Task task = tasks.addNewTask(info);
            fileManager.saveToFile(task);
            return ui.printAddGui(task, tasks.getSize());
        }else if(isDelete){
            Task task = tasks.delete(info[1]);
            fileManager.fileDelete(Integer.parseInt(info[1]) - 1);
            return ui.printDeleteGui(task, tasks.getSize());
        }else if(isDone){
            Task task = tasks.done(info[1]);
            fileManager.fileSetDone(Integer.parseInt(info[1]) - 1);
            return ui.printDoneGui(task);
        }else if(isFind){
            String foundList = tasks.findMatch(info[1]);
            return ui.printFindGui(foundList);
        }
        if(info[0].equals("list")){
            return ui.printListGui(tasks.generateList());
        }else if(info[0].equals("bye")){
            return ui.printByeGui();
        }
        return "Command not recognized?";
    }

    public boolean isExit(){
        return this.isExit;
    }

}
