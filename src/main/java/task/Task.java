package task;

/**
 * Represents a task with a description and a completion status.
 * This is the base class for all task types, such as Deadline, Event, etc.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ] ");
    }
    public void markAsDone() {
        isDone = true;
    }
    public void unmarkAsDone() {
        isDone = false;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    // Convert task to string format for saving (e.g., "T | 1 | read book")
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
