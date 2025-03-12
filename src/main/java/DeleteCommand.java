import task.Event;
import task.Task;

/**
 * Represents a command to delete a task.
 * Parses the user input to identify the task index, deletes the task from the task list,
 * and updates the storage. Displays a confirmation message.
 * Throws an {@link EchoBoxException} for invalid task index.
 */
public class DeleteCommand extends Command{
    DeleteCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        int index = parseIndex(input, tasks);
        Task removed = tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Noted. I've removed this task:\n  " + removed);
    }
}
