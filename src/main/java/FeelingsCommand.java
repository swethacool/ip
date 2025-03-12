/**
 * Represents a command that responds to the user's emotional state.
 * The command displays a message based on whether the user is feeling sad or not.
 * If the user is sad, a message of encouragement is shown; otherwise, a positive message is displayed.
 */
public class FeelingsCommand extends Command{
    private boolean isSad;
    FeelingsCommand(String input, boolean isSad){
        super(input);
        this.isSad = isSad;
    }

    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        if(isSad)
            ui.showMessage("Don't be sad! Here's a hug :)"); //cheer up message
        else
            ui.showMessage("That's good to hear, keep going!"); //cheer up message
    }
}
