import java.io.ByteArrayInputStream;
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
            return (isDone ? "[X]" : "[ ] ");
        }   

        public void markAsDone() {
            isDone = true;
        }

        public void unmarkAsDone() {
            isDone = false;
        }

        @Override
        public String toString() {
            return getStatusIcon() + " " + description;
        }
    }

    // Subclass for ToDos
    static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    // Subclass for Deadlines
    static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    // Subclass for Events
    static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        String simulatedInput = "Hello\nlist\nbye\n"; // Simulated user inputs
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes())); // Simulate input for Scanner
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0; // Counter for stored tasks


        System.out.println("Hello! I'm EchoBox");
        System.out.println("How can I help you today?");


        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("EchoBox: Bye! Don't forget to take breaks! See you soon!");
                break;
            }

            if (userInput.equalsIgnoreCase("Hello")) {
                System.out.println("EchoBox: Hey there! How is your day?");
            } else if (userInput.equalsIgnoreCase("sad")) {
                System.out.println("EchoBox: Don't be sad! Here's a hug");
            }
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) { // Display task list
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                continue;
            }

            if (userInput.startsWith("mark")) {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    tasks[taskNum].markAsDone();
                    System.out.println("EchoBox: Nice! I've marked this task as done: ");
                    System.out.println(tasks[taskNum]); // Use `toString()` to show updated task
                } else {
                    System.out.println("EchoBox: Invalid task number!");
                }
                continue;
            }

            if (userInput.startsWith("unmark")) {
                int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    tasks[taskNum].unmarkAsDone();
                    System.out.println("EchoBox: OK, I've unmarked this task. Let's get it done soon! 💪");
                    System.out.println(tasks[taskNum]); // Use `toString()` to show updated task
                } else {
                    System.out.println("EchoBox: Invalid task number!");
                }
                continue;
            }

            if (userInput.startsWith("todo ")) {
                String taskDescription = userInput.substring(5);
                tasks[taskCount] = new Todo(taskDescription);
                taskCount++;
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                continue;
            }

            if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split(" /by ");
                if (parts.length < 2) {
                    System.out.println("EchoBox: Incorrect format! Use: deadline <desc> /by <time>");
                    continue;
                }
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                continue;
            }

            if (userInput.startsWith("event")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                if (parts.length < 3) {
                    System.out.println("EchoBox: Incorrect format! Use: event <desc> /from <start> /to <end>");
                    continue;
                }
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                continue;
            }
        }
    }
}
