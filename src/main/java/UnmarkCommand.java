import task.Task;

import java.util.List;

public class UnmarkCommand extends Command{
    UnmarkCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        int index = parseIndex(input, tasks);
        tasks.getTask(index).unmarkAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("OK, I've unmarked this task:\n  " + tasks.getTask(index));
    }
}

