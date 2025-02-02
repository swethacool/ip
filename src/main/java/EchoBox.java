import java.util.Scanner;

public class EchoBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // Fixed-size array for storing tasks
        int taskCount = 0; // Counter for stored tasks

        System.out.println("Hello! I'm EchoBox"); // Your chatbot's new name
        System.out.println("😊 How can I help you today?");

        while (true) {
            String userInput = scanner.nextLine();

            if (taskCount < 100) {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("EchoBox: ✅ added: " + userInput);
            } else {
                System.out.println("EchoBox: ⚠️ Task list is full!");
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("EchoBox: 👋 Bye! Hope to see you again soon! 🚀");
                break;
            } else if (userInput.equalsIgnoreCase("Hello")) {
                System.out.println("EchoBox: Hey there! 😊 How’s your day?");
            } else if (userInput.equalsIgnoreCase("sad")) {
                System.out.println("EchoBox: Don't be sad! Here's a hug 🤗");
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) { // Display task list
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("   " + (i) + ". " + tasks[i]);
                }
            }
        }
    }
}