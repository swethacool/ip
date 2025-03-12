package task;

/**
 * Represents a task with a deadline. Inherits from the Task class.
 * A Deadline task has a description and a time by which it needs to be completed.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    // Convert Deadline object to the string format for saving to file
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
