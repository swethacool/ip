import java.util.Scanner;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class EchoBox {
    private static final String EXIT_COMMAND = "bye";
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm EchoBox");
        System.out.println("How can I help you today?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine().trim();
            processCommand(userInput);  // Call the method to process the command
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                System.out.println("EchoBox: Bye! Don't forget to take breaks! See you soon!");
                break; // Exit the loop
            }
        }
    }

    private static void processCommand(String userInput) {
        String command = userInput.toLowerCase();
        if (command.startsWith("list")) {
            displayTaskList();
        } else if (command.startsWith("mark ")) {
            try {
                handleMark(userInput);  // This may throw EchoBoxException
            } catch (EchoBoxException e) {
                System.out.println("EchoBox: " + e.getMessage());  // Handle and print the exception message
            }
        }  else if (command.startsWith("unmark ")) {
            try {
                handleUnmark(userInput);  // This may throw EchoBoxException
            } catch (EchoBoxException e) {
                System.out.println("EchoBox: " + e.getMessage());  // Handle and print the exception message
            }
        } else if (command.startsWith("todo ")) {
            try {
                handleAddTodo(userInput);
            } catch (EchoBoxException e) {
                System.out.println("EchoBox: " + e.getMessage());
            }
        } else if (command.startsWith("deadline ")) {
            try {
                handleAddDeadline(userInput);
            } catch (EchoBoxException e) {
                System.out.println("EchoBox: " + e.getMessage());
            }
        } else if (command.startsWith("event ")) {
            try {
                handleAddEvent(userInput);
            } catch (EchoBoxException e) {
                System.out.println("EchoBox: " + e.getMessage());
            }
        } else if (userInput.equalsIgnoreCase("Hello")) {
            System.out.println("EchoBox: Hey there! How is your day?");
        } else if (userInput.equalsIgnoreCase("sad")) {
            System.out.println("EchoBox: Don't be sad! Here's a hug :)");
        } else if(!userInput.equalsIgnoreCase(EXIT_COMMAND)){
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    private static void handleMark(String input) throws EchoBoxException {
        try {
            int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
            if (isValidTask(taskNum)) {
                tasks[taskNum].markAsDone();
                System.out.println("EchoBox: Nice! I've marked this task as done:");
                System.out.println(tasks[taskNum]);
            }
        } catch (NumberFormatException e) {
            throw new EchoBoxException("Oops! Invalid task number format. Please provide a valid task number.");
        }
    }

    private static void handleUnmark(String input) throws EchoBoxException {
        try {
            int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
            if (isValidTask(taskNum)) {
                tasks[taskNum].unmarkAsDone();
                System.out.println("EchoBox: OK, I've unmarked this task. Let's get it done soon! 💪");
                System.out.println(tasks[taskNum]);
            }
        } catch (NumberFormatException e) {
            System.out.println("EchoBox: Oops! Invalid task number format. Please provide a valid task number.");
        }
    }

    private static void handleAddTodo(String input) throws EchoBoxException {
        String[] parts = input.split(" ", 2); // Split only into two parts: "todo" and description
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            //System.out.println("EchoBox:OOPS!! Task description cannot be empty!");
            throw new EchoBoxException("EchoBox:OOPS!! Task description cannot be empty!");
        }
        String taskDescription = parts[1].trim(); // Get the task description
        if (taskCount >= tasks.length) {
            throw new EchoBoxException("EchoBox: Task list is full!");
        }
        // Create the new Todo task
        tasks[taskCount++] = new Todo(taskDescription);
        printTaskAdded();
    }

    private static void handleAddDeadline(String input) throws EchoBoxException {
        if (taskCount >= tasks.length) {
            throw new EchoBoxException("EchoBox: Task list is full!");
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw new EchoBoxException("EchoBox: Incorrect format! Use: deadline <desc> /by <time>");
        }
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        printTaskAdded();
    }

    private static void handleAddEvent(String input) throws EchoBoxException {
        if (taskCount >= tasks.length) {
            throw new EchoBoxException("EchoBox: Task list is full!");
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new EchoBoxException("EchoBox: Incorrect format! Use: event <desc> /from <start> /to <end>");
        }
        if (parts[0].isEmpty()) {  // Corrected here
            throw new EchoBoxException("EchoBox: Task description cannot be empty!");
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
}

