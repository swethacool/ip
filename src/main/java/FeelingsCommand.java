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
