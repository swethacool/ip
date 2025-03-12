public class MarkCommand extends Command{
    MarkCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        int index = parseIndex(input, tasks);
        tasks.getTask(index).markAsDone();
        storage.saveTasks(tasks.getTasks());
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
    }
}
