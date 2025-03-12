/**
 * Executes the bye command, which sets the exit flag to true, indicating
 * that the application should terminate.
 */
public class ByeCommand extends Command{
    ByeCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) {
        exit = true;
    }
}
