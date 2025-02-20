package task;

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
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    // Convert task to string format for saving (e.g., "T | 1 | read book")
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
