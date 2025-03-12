import task.Event;
import task.Task;

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
