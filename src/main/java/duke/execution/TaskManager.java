package duke.execution;

import java.util.ArrayList;
import duke.task.*;
/**
 * TaskManager class of Duke.
 * In charge of storing and updating existing list of Tasks.
 */
public class TaskManager {
    private ArrayList<Task> todoList;

    /**
     * Constructor for TaskManager.
     * Initiates TaskManager object with empty ArrayList
     */
    public TaskManager(){
        this.todoList=new ArrayList<>();
    }

    /**
     * Adds new Task to ArrayList
     * Task type added depends on input
     * @param taskInfo information regarding new task given by user input
     * @return returns the task added to the list for easy I/O processing and file write
     * @throws DukeException thrown when user input does not adhere to default format
     */
    protected Task addNewTask(String[] taskInfo) throws DukeException{
        if(taskInfo[0].equals("todo")){
            return this.addTodo(taskInfo[1]);
        }else if(taskInfo[0].equals("event")){
            return this.addEvent(taskInfo[1]);
        }else if (taskInfo[0].equals("deadline")){
            return this.addDeadline(taskInfo[1]);
        }
        throw new DukeException("Something wrong lol");
    }

    /**
     * Adds Todo task to the list
     * @param task task information
     * @return Todo object
     */
    private Todo addTodo(String task) {
        task = task.trim();
        Todo todo = new Todo(task);
        this.todoList.add(todo);
        return todo;
    }

    /**
     * Adds Event task to the list
     * @param task task information
     * @return Event object
     * @throws DukeException
     */
    private Event addEvent(String task) throws DukeException {
        int split = task.indexOf(" /at");
        if (split == -1) {
            throw new DukeException("Event time not given. Specify event time using \"/at\"");
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);       //exception may occur
                description = description.trim();
                deadline = deadline.trim();
                Event event = new Event(description, deadline);
                this.todoList.add(event);
                return event;
            } catch (IndexOutOfBoundsException e) {         // happens when input is "event xx /at" with no time given
                throw new DukeException("No event time provided");
            }
        }
    }

    /**
     * Adds Deadline task to the list
     * @param task task information
     * @return Deadline object
     * @throws DukeException
     */
    private Deadline addDeadline(String task) throws DukeException {
        int split = task.indexOf(" /by");
        if (split == -1) {
            throw new DukeException("Deadline not given. Specify deadline using \"/by\"");
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);
                description = description.trim();
                deadline = deadline.trim();
                Deadline newDeadline = new Deadline(description, deadline);
                this.todoList.add(newDeadline);
                return newDeadline;
            } catch (IndexOutOfBoundsException e) { //same as event time
                throw new DukeException("No deadline provided");
            }
        }
    }

    /**
     * Helper function for FileManager {@link FileManager#saveToFile(Task)}
     * @param task task to add to list
     */
    protected void load(Task task){
        this.todoList.add(task);
    }

    /**
     * Helper function for FileManager {@link FileManager#saveToFile(Task)}
     * Similar to done method, except this takes in int argument rather than String.
     * @param index index of done task
     */
    protected void savedDone(int index){
        this.todoList.get(index).setDone();
    }

    /**
     * Sets task as done
     * @param doneIndex index of done task
     * @return task that was set as done
     * @throws DukeException
     */
    protected Task done(String doneIndex) throws DukeException{
        try {
            int doneInt = Integer.parseInt(doneIndex);
            this.todoList.get(doneInt - 1).setDone();
            return this.todoList.get(doneInt - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Bad task index"); // for wrong index provided
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task index");  //for index>array length
        }
    }

    /**
     * Delete task specified by index
     * @param deleteIndex Index of task to be deleted
     * @return Deleted task
     * @throws DukeException
     */
    protected Task delete(String deleteIndex) throws DukeException{  //exception same as done method
        try {
            int deleteInt = Integer.parseInt(deleteIndex);
            Task deletedTask = this.todoList.get(deleteInt - 1);
            this.todoList.remove(deleteInt - 1);
            return deletedTask;
        } catch (NumberFormatException e) {
            throw new DukeException("Bad task index");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task index");
        }
    }

    /**
     * Returns a string containing all the tasks currently in the list
     * @return string of tasks
     */
    protected String generateList() {
        StringBuilder outputMessage = new StringBuilder();
        int i = todoList.size();
        for (int x = 1; x <= i; x++) {
            outputMessage.append("\n  " + x + "." + todoList.get(x - 1));
        }
        return outputMessage.toString();
    }

    /**
     * Returns string containing all tasks that is keyword in its description
     * @param keyword word to match with task description
     * @return string of tasks
     */
    protected String findMatch(String keyword){
        ArrayList<Task> foundList = new ArrayList<>();
        int foundTask = 0;
        for(Task t: todoList){
            if(t.description.contains(keyword)){
                foundTask++;
                foundList.add(t);
            }
        }
        return this.listToString(foundList);
    }

    /**
     * Returns ArrayList of tasks as a String. Helper function in "list" command from user.
     * @param taskList
     * @return
     */
    private String listToString(ArrayList<Task> taskList){
        StringBuilder outputMessage = new StringBuilder();
        int i = taskList.size();
        for(int x = 1; x <= i; x++){
            outputMessage.append("\n  " + x + "." + taskList.get(x-1));
        }
        return outputMessage.toString();
    }

    /**
     * Returns number of tasks currently
     * @return number of tasks
     */

    protected int getSize(){
        return this.todoList.size();
    }
}
