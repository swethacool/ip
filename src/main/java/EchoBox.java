import java.util.Scanner;

public class EchoBox {
    private static final String EXIT_COMMAND = "bye";
    private static final Task[] tasks = new Task[100];  // Fixed-size array
    private static int taskCount = 0;  // Track number of tasks added

    public static void main(String[] args) {
        System.out.println("Hello! I'm EchoBox");
        System.out.println("How can I help you today?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                System.out.println("EchoBox: Bye! Don't forget to take breaks! See you soon!");
                break;
            }
            if (userInput.equalsIgnoreCase("Hello")) {
                System.out.println("EchoBox: Hey there! How is your day?");
            } else if (userInput.equalsIgnoreCase("sad")) {
                System.out.println("EchoBox: Don't be sad! Here's a hug :)");
            }
            processCommand(userInput);
        }
    }

    private static void processCommand(String userInput) {
        // Convert user input to lowercase to ensure case-insensitive comparison
        String command = userInput.toLowerCase();

        if (command.startsWith("list")) {
            displayTaskList();
        } else if (command.startsWith("mark ")) {
            handleMark(userInput);
        } else if (command.startsWith("unmark ")) {
            handleUnmark(userInput);
        } else if (command.startsWith("todo ")) {
            handleAddTodo(userInput);
        } else if (command.startsWith("deadline ")) {
            handleAddDeadline(userInput);
        } else if (command.startsWith("event ")) {
            handleAddEvent(userInput);
        }
    }

    private static void displayTaskList() {
        if (taskCount == 0) {
            System.out.println("EchoBox: No tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    private static void handleMark(String input) {
        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
        if (isValidTask(taskNum)) {
            tasks[taskNum].markAsDone();
            System.out.println("EchoBox: Nice! I've marked this task as done:");
            System.out.println(tasks[taskNum]);
        }
    }

    private static void handleUnmark(String input) {
        int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
        if (isValidTask(taskNum)) {
            tasks[taskNum].unmarkAsDone();
            System.out.println("EchoBox: OK, I've unmarked this task. Let's get it done soon! 💪");
            System.out.println(tasks[taskNum]);
        }
    }

    private static void handleAddTodo(String input) {
        if (taskCount >= tasks.length) {
            System.out.println("EchoBox: Task list is full!");
            return;
        }
        String taskDescription = input.substring(5).trim();
        tasks[taskCount] = new Todo(taskDescription);
        taskCount++;
        printTaskAdded();
    }

    private static void handleAddDeadline(String input) {
        if (taskCount >= tasks.length) {
            System.out.println("EchoBox: Task list is full!");
            return;
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            System.out.println("EchoBox: Incorrect format! Use: deadline <desc> /by <time>");
            return;
        }
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        printTaskAdded();
    }

    private static void handleAddEvent(String input) {
        if (taskCount >= tasks.length) {
            System.out.println("EchoBox: Task list is full!");
            return;
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            System.out.println("EchoBox: Incorrect format! Use: event <desc> /from <start> /to <end>");
            return;
        }
        tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
        taskCount++;
        printTaskAdded();
    }

    private static boolean isValidTask(int taskNum) {
        if (taskNum < 0 || taskNum >= taskCount) {
            System.out.println("EchoBox: Invalid task number!");
            return false;
        }
        return true;
    }

    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

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

    static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

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

    static class Event extends Task {
        protected String from, to;

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
}
