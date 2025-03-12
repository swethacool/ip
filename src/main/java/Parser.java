import task.*;
import java.util.List;

/**
 * The Parser class handles interpreting and executing user commands.
 * It connects the user interface, task list, and storage components.
 */

public class Parser {
    private final Ui ui; // Handles user input and output
    private final TaskList tasks; // The list of tasks being managed
    private final Storage storage; // Manages saving/loading tasks to/from storage

    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    public boolean parseAndExecute(String input) {
        String command = input.trim().toLowerCase();

        try {
            if (command.equals("bye")) {
                return true;
            }
            else if (command.equals("list")) {
                handleList();
            }
            else if (command.startsWith("mark ")) {
                handleMark(input);
            }
            else if (command.startsWith("unmark ")) {
                handleUnmark(input);
            }
            else if (command.startsWith("todo ")) {
                handleAddTodo(input);
            }
            else if (command.startsWith("deadline ")) {
                handleAddDeadline(input);
            }
            else if (command.startsWith("event ")) {
                handleAddEvent(input);
            }
            else if (command.startsWith("delete ")) {
                handleDelete(input);
            }
            else if (command.equals("sad")) {
                ui.showMessage("Don't be sad! Here's a hug :)"); //cheer up message
            }
            else if (command.equals("good")) {
                ui.showMessage("That's good to hear, keep going!"); //cheer up message
            }
            else {
                ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-("); //unknown command
            }
        }
        catch (EchoBoxException e) {
            ui.showMessage("EchoBox: " + e.getMessage());
        }
        return false;
    }

    /**
     * Displays the current list of tasks.
     */
    private void handleList() {
        List<Task> taskList = tasks.getTasks();
        if (taskList.isEmpty()) {
            ui.showMessage("EchoBox: No tasks in your list!");
        }
        else {
            for (int i = 0; i < taskList.size(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Marks a task as done based on user input.
     */
    private void handleMark(String input) throws EchoBoxException {
        int index = parseIndex(input);
        tasks.getTask(index).markAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
    }

    /**
     * Unmarks a completed task based on user input.
     */
    private void handleUnmark(String input) throws EchoBoxException {
        int index = parseIndex(input);
        tasks.getTask(index).unmarkAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("OK, I've unmarked this task:\n  " + tasks.getTask(index));
    }

    /**
     * Adds a Todo task based on user input.
     */
    private void handleAddTodo(String input) throws EchoBoxException {
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) throw new EchoBoxException("Task description cannot be empty!");
        tasks.addTask(new Todo(desc));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + tasks.getTask(tasks.size() - 1));
    }

    /**
     * Adds a Deadline task based on user input.
     */
    private void handleAddDeadline(String input) throws EchoBoxException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) throw new EchoBoxException("Incorrect format! Use: deadline <desc> /by <time>");
        tasks.addTask(new Deadline(parts[0], parts[1]));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this deadline:\n  " + tasks.getTask(tasks.size() - 1));
    }

    /**
     * Adds an Event task based on user input.
     */
    private void handleAddEvent(String input) throws EchoBoxException {
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) throw new EchoBoxException("Incorrect format! Use: event <desc> /from <start> /to <end>");
        tasks.addTask(new Event(parts[0], parts[1], parts[2]));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this event:\n  " + tasks.getTask(tasks.size() - 1));
    }

    /**
     * Deletes a task based on user input.
     */
    private void handleDelete(String input) throws EchoBoxException {
        int index = parseIndex(input);
        Task removed = tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Noted. I've removed this task:\n  " + removed);
    }

    /**
    * Parses and validates the task index from user input.
    */
    private int parseIndex(String input) throws EchoBoxException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || !parts[1].matches("\\d+")) throw new EchoBoxException("Invalid task number!");
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) throw new EchoBoxException("Task number out of range!");
        return index;
    }
}
