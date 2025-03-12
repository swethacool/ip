import task.Todo;

/**
 * The TodoCommand class represents a command to add a Todo task.
 * It parses the user input, validates the description, creates a new Todo task,
 * and updates the task list and storage.
 */
public class TodoCommand extends Command{
    TodoCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        String desc = input.substring(5).trim();
        if (desc.isEmpty()) throw new EchoBoxException("Task description cannot be empty!");
        tasks.addTask(new Todo(desc));
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + tasks.getTask(tasks.size() - 1));
    }
}
