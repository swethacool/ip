import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hey there! I'm EchoBox\nHow is your day?"); //greeting message
    }

    public void showExit() {
        System.out.println("EchoBox: Bye! Don't forget to take breaks! See you soon!"); //exit message
    }

    public void showLoadingError(String message) {
        System.out.println("Error loading tasks: " + message);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
