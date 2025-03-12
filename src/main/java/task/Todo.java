package task;

/**
 * Represents a Todo task that can be marked as done or undone.
 * Inherits from the Task class and provides a string representation of a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // Convert Todo object to the string format for saving to file
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
