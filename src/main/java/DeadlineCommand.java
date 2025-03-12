import task.Deadline;
import task.Todo;

public class DeadlineCommand extends Command{
    DeadlineCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) throw new EchoBoxException("Incorrect format! Use: deadline <desc> /by <time>");
        tasks.addTask(new Deadline(parts[0], parts[1]));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this deadline:\n  " + tasks.getTask(tasks.size() - 1));
    }
}
