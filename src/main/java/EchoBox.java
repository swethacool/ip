import java.util.Scanner;

public class EchoBox {
    // Task class to represent a task
    static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // Mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void unmarkAsDone() {
            isDone = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Fixed-size array for storing tasks
        int taskCount = 0; // Counter for stored tasks

        System.out.println("Hello! I'm EchoBox"); // Your chatbot's new name
        System.out.println("😊 How can I help you today?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("EchoBox: 👋 Bye! Hope to see you again soon! 🚀");
                break;
            }

            if (userInput.equalsIgnoreCase("Hello")) {
                System.out.println("EchoBox: Hey there! 😊 How’s your day?");
            } else if (userInput.equalsIgnoreCase("sad")) {
                System.out.println("EchoBox: Don't be sad! Here's a hug 🤗");
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) { // Display task list
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            } else if (userInput.startsWith("unmark")) {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    tasks[taskNum].unmarkAsDone();
                    System.out.println("EchoBox: OK, I've marked this task as not done yet: ");
                    System.out.println("[ ]" + tasks[taskNum].description);
                } else {
                    System.out.println("EchoBox: ⚠️ Invalid task number!");
                }
            } else if (userInput.startsWith("mark")) {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    tasks[taskNum].markAsDone();
                    System.out.println("EchoBox: Nice! I've marked this task as done: ");
                    System.out.println("[X]" + tasks[taskNum].description);
                } else {
                    System.out.println("EchoBox: ⚠️ Invalid task number!");
                }
            }
            else {
                // Add new task if not "list", "mark", or "unmark"
                if (taskCount < 100) {
                    tasks[taskCount] = new Task(userInput); // Create a new Task object
                    taskCount++;
                    System.out.println("EchoBox: ✅ added: " + userInput);
                } else {
                    System.out.println("EchoBox: ⚠️ Task list is full!");
                }
            }
        }
    }
}