package duke.execution;

import java.util.Scanner;
import duke.task.Task;

/**
 * UserInterface class of Duke.
 * In charge of program I/O.
 */
public class UserInterface {
    Scanner sc;

    /**
     * Constructor for UserInterface
     * Initializes with a Scanner object
     */
    public UserInterface(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Print method of Duke
     * @param message message to print
     */
    private void print(String message){
        System.out.println(">>" + message + "\n");
    }

    /**
     * Prints welcome message
     */
    public void printWelcome(){
        String logo = "\n" +
                " ______       ___    _ .--.   .--.      .-''-.   \n" +
                "|    _ `''. .'   |  | ||  | _/  /     .'_ _   \\  \n" +
                "| _ | ) _  \\|   .'  | || (`' ) /     / ( ` )   ' \n" +
                "|( ''_'  ) |.'  '_  | ||(_ ()_)     . (_ o _)  | \n" +
                "| . (_) `. |'   ( \\.-.|| (_,_)   __ |  (_,_)___| \n" +
                "|(_    ._) '' (`. _` /||  |\\ \\  |  |'  \\   .---. \n" +
                "|  (_.\\.' / | (_ (_) _)|  | \\ `'   / \\  `-'    / \n" +
                "|       .'   \\ /  . \\ /|  |  \\    /   \\       /  \n" +
                "'-----'`      ``-'`-'' `--'   `'-'     `'-..-'   \n" +
                "(◕ᴗ◕✿)\n";
        System.out.println("Hewwo from" + logo);
    }

    public String printWelcomeGui(){
        return "H-hewwo from Duke (◕ω◕✿)\n";
    }

    /**
     * Prints message when task is successfully added
     * @param task task added
     * @param listSize current number of tasks
     */
    private String generateAddMessage(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Task added:\n");
        outputMessage.append("    " + task);
        outputMessage.append("\n  There are " + listSize + " tasks in the list.");
        return outputMessage.toString();
    }

    public void printAdd(Task task, int listSize){
        this.print(generateAddMessage(task, listSize));
    }

    public String printAddGui(Task task, int listSize){
        return generateAddMessage(task, listSize);
    }

    /**
     * Print load save file message
     */
    public void printLoadSave(){
        this.print("Save file loaded successfully");
    }

    /**
     * Print new file message
     */
    public void printNewFile(){
        this.print("New save file created");
    }

    /**
     * Print file set to done message
     * @param task
     */
    public void printDone(Task task){
        this.print("The following task has been marked as done:\n    " + task);
    }

    public String printDoneGui(Task task){
        return "The following task has been marked as done:\n    " + task;
    }

    /**
     * Print file deleted message
     * @param task deleted task
     * @param listSize number of tasks left
     */
    private String generateDeleteMessage(Task task, int listSize){
        StringBuilder outputMessage = new StringBuilder("Following task yeeted:\n    " + task);
        outputMessage.append("\n  " + listSize + " tasks left in the list\n");
        return outputMessage.toString();
    }
    public void printDelete(Task task, int listSize){
        this.print(generateDeleteMessage(task, listSize));
    }

    public String printDeleteGui(Task task, int listSize){
        return generateDeleteMessage(task, listSize);
    }

    /**
     * Prints error message
     * @param message error details
     */
    public void printError(String message){
        this.print("Error: " + message);
    }

    public String printErrorGui(String message){
        return "Error xwx: " + message;
    }

    /**
     * Prints list
     * @param list the list
     */
    public void printList(String list){
        this.print("List:" + list);
    }

    public String printListGui(String list){ return "Listing tasks owo:" + list; }

    /**
     * Prints list of tasks that match keyword given by user
     * @param list list of tasks
     */
    public void printFind(String list){
        this.print("Matching tasks:" + list);
    }

    public String printFindGui(String list){
        return "Here are the matching tasks owo:" + list;
    }

    /**
     * Reads and return next command input from user
     * @return next user command
     */
    public String readLine(){
        return sc.nextLine();
    }

    /**
     * Prints exit message
     */
    public void printExit(){
        this.print("Goodbye. Hope to see you again UwU");
    }

    public String printByeGui(){
        return "Goodbye. This window will close soon. Hope to see you again UwU";
    }
}

