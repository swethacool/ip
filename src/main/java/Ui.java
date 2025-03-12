import java.util.Scanner;

/**
 * The Ui class handles all user interactions, including reading commands and displaying messages to the user.
 * It provides methods for showing welcome and exit messages, as well as handling errors and displaying custom messages.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     * This message is shown when the program starts.
     */
    public void showWelcome() {
        System.out.println("Hey there! I'm EchoBox\nHow is your day?"); //greeting message
    }

    /**
     * Displays an exit message to the user.
     * This message is shown when the program exits.
     */
    public void showExit() {
        System.out.println("EchoBox: Bye! Don't forget to take breaks! See you soon!"); //exit message
    }

    /**
     * Displays an error message when loading tasks fails.
     * @param message The error message to be displayed.
     */
    public void showLoadingError(String message) {
        System.out.println("Error loading tasks: " + message);
    }

    /**
     * Reads a command entered by the user.
     * @return The command entered by the user as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a custom message to the user.
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
