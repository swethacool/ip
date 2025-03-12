/**
 * Represents a command that marks a specific task as done in the task list.
 * The task is identified by its index, and the task's status is updated.
 */
public class MarkCommand extends Command{
    MarkCommand(String input){
        super(input);
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * If the task is found and marked, it updates the storage and shows a confirmation message.
     */
    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        int index = parseIndex(input, tasks);
        tasks.getTask(index).markAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
    }
}
