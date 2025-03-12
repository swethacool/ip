package task;

/**
 * Represents a task with a time range. Inherits from the Task class.
 * An Event task has a description and a time range defined by the start and end times.
 */
public class Event extends Task {
    protected String from, to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    // Convert Event object to the string format for saving to file
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
