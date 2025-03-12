public class ByeCommand extends Command{
    ByeCommand(String input){
        super(input);
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) {
        exit = true;
    }
}
