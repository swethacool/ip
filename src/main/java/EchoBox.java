/**
 * The EchoBox class serves as the main entry point of the EchoBox application.
 * It initializes all components and manages the main program loop.
 */
public class EchoBox {
    private Storage storage; // Handles loading and saving tasks to a file
    private TaskList tasks; // Holds the current list of tasks
    private Ui ui; // Manages interactions with the user (input/output)

    /**
     * Constructs an EchoBox instance.
     * Initializes the UI, storage, and parser components.
     * Attempts to load the tasks from the specified file.
     *filePath The path to the file where tasks are stored.
     */
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
    }

    /**
     * Runs the main loop of the EchoBox application.
     * Reads user input, parses and executes commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, tasks, storage);
                isExit = c.isExit();
            } catch (EchoBoxException e) {
                ui.showMessage("EchoBox: " + e.getMessage());
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new EchoBox("./data/EchoBox.txt").run();
    }
}
