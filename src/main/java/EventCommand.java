import task.Deadline;
import task.Event;

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
