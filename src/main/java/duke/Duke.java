package duke;

import duke.execution.Command;
import duke.execution.DukeException;
import duke.execution.FileManager;
import duke.execution.Parser;
import duke.execution.TaskManager;
import duke.execution.UserInterface;

/**
 * Duke is a mini list AI project for CS2103 iP and it steals my soul
 *  p.s. i'm not a furry i swear the uwus are just a joke :)
 */
public class Duke{
    private static final String PATHFILE = "src/main/data/list.txt";
    private UserInterface ui;
    private TaskManager tasks;
    private FileManager fileManager;

    /**
     * Constructor for Duke. Instantiates a new UserInterface, TaskManager, FileManager
     * Prints response through ui depending on whether file is successfully found/transferred to local list
     */
    public Duke() {
        this.ui = new UserInterface();
        ui.printWelcome();
        this.tasks = new TaskManager();
        try{
            this.fileManager = new FileManager();
            if(this.fileManager.initialize(PATHFILE, tasks)){
                ui.printNewFile();
            }else {
                ui.printLoadSave();
            }
        }catch(DukeException e){
            ui.printError(e.getMessage());
        }
    }

    /**
     * Run process of Duke.
     * ui reads and return user input line by line, passing it to Parser which will convert it to Command
     * Command executes accordingly
     * Each loop checks for possible exit command
     * All DukeException will be caught and the message printed here.
     */
    private void run(){
        boolean isExit = false;
        while(!isExit){
            try {
                String userCommand = ui.readLine();
                Command c = Parser.parse(userCommand);
                c.execute(tasks, ui, fileManager);
                isExit = c.isExit();
            }catch(DukeException e){
                ui.printError(e.getMessage());
            }
        }
        ui.printExit();
    }

    /**
     * main process of Duke
     * @param args
     */
    public static void main(String[] args) {
        Duke process = new Duke();
        process.run();
    }
    /**
     * Returns String as response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeGui(tasks, ui, fileManager);
        }catch(DukeException e){
            return ui.printErrorGui(e.getMessage());
        }
    }

}
