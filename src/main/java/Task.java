/**
 * Represents a task in a checklist
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that initializes a Task object.
     * Set isDone to false since newly created tasks are mostly not completed
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes Task isDone parameter to true
     */
    public void setDone() {
        this.isDone = true;
    }

    public boolean checkDone(){
        return this.isDone;
    }

    /**
     * Returns Task object description in a specific format for writing to save file
     * @return Task object full description for save file
     */
    protected abstract String toFileString();

    /**
     * Returns Task object description in a specific format for printing
     * @return Task object full description for I/O
     */
    @Override
    public abstract String toString();
}

