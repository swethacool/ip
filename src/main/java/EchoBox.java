import java.util.Scanner;

public class EchoBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Hello! I'm EchoBox!");
        System.out.println("😊 How can I help you today?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("EchoBox: 👋 Bye! Hope to see you again soon! 🚀");
                break;
            } else if (userInput.equalsIgnoreCase("hello")) {
                System.out.println("EchoBox: " + userInput);
                System.out.println("EchoBox: Hey there! 😊 How’s your day?");
            } else if (userInput.equalsIgnoreCase("sad")) {
                System.out.println("EchoBox: " + userInput);
                System.out.println("EchoBox: Don't be sad! Here's a hug 🤗");
            } else {
                System.out.println("EchoBox: " + userInput);
            }
        }
    }
}
