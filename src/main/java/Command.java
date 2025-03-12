public class Command {
    protected String input;
    protected boolean exit;

    Command(String input){
        this.input = input;
        exit = false;
    }

    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException{
        ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-("); //unknown command
    }

    public boolean isExit(){
        return exit;
    }

    /**
     * Parses and validates the task index from user input.
     */
    protected int parseIndex(String input, TaskList tasks) throws EchoBoxException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || !parts[1].matches("\\d+")) throw new EchoBoxException("Invalid task number!");
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) throw new EchoBoxException("Task number out of range!");
        return index;
    }
}
