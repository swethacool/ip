/**
 * The EchoBox class serves as the main entry point of the EchoBox application.
 * It initializes all components and manages the main program loop.
 */
public class EchoBox {
    private Storage storage; // Handles loading and saving tasks to a file
    private TaskList tasks; // Holds the current list of tasks
    private Ui ui; // Manages interactions with the user (input/output)
    private Parser parser; // Parses and executes user commands

    public EchoBox(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        }
        catch (EchoBoxException e) {
            ui.showLoadingError(e.getMessage()); //loading failure shows error
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks, storage); // Parser has access to everything needed
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            isExit = parser.parseAndExecute(input);
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new EchoBox("./data/EchoBox.txt").run();
    }
}
