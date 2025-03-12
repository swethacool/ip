import task.Deadline;
import task.Event;

/**
 * Represents a command that adds an event to the task list.
 * The event command expects the format: "event <desc> /from <start> /to <end>".
 * It parses the user input, creates an Event object, and adds it to the task list.
 * Afterward, the task list is saved and the user is shown a confirmation message.
 */
public class EventCommand extends Command{
    EventCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) throw new EchoBoxException("Incorrect format! Use: event <desc> /from <start> /to <end>");
        tasks.addTask(new Event(parts[0], parts[1], parts[2]));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this event:\n  " + tasks.getTask(tasks.size() - 1));
    }
}
