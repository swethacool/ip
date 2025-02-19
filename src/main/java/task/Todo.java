package task;

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
