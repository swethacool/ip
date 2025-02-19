import java.util.ArrayList;
import java.util.Scanner;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.DataManager; // if it's in the task package
import java.util.List;



public class EchoBox {
    private static final String EXIT_COMMAND = "bye";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm EchoBox");
        System.out.println("How can I help you today?");
        tasks = DataManager.loadTasks();  // Load tasks from file
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
        } else if (command.startsWith("delete ")) {
            try{
                handleDelete(userInput);
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
        if (tasks.isEmpty()) {
            System.out.println("EchoBox: No tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Helper method to check if a string is a number
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");  // Returns true if str contains only digits
    }

    private static void handleDelete(String input) throws EchoBoxException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1])) { //check if user provided number
            throw new EchoBoxException("EchoBox: Oops! Please provide a valid task number to delete.");
        }
        int taskNum = Integer.parseInt(parts[1]) - 1; // Convert user input to index
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new EchoBoxException("EchoBox: Oops! Task number out of range.");
        }
        Task removedTask = tasks.remove(taskNum);
        DataManager.saveTasks(tasks);  // Save the tasks to file
        System.out.println("EchoBox: Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleMark(String input) throws EchoBoxException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1])) {
            throw new EchoBoxException("EchoBox: Oops! Please provide a valid task number to mark.");
        }
        int taskNum = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new EchoBoxException("EchoBox: Oops! Task number out of range.");// Ensures valid range
        }
        tasks.get(taskNum).markAsDone(); // Marks task as done
        DataManager.saveTasks(tasks);  // Save the tasks to file
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNum));
    }

    private static void handleUnmark(String input) throws EchoBoxException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || !isNumeric(parts[1])) {
            throw new EchoBoxException("EchoBox: Oops! Please provide a valid task number to mark.");
        }
        int taskNum = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new EchoBoxException("EchoBox: Oops! Task number out of range.");// Ensures valid range
        }
        tasks.get(taskNum).unmarkAsDone(); // Marks task as not done
        DataManager.saveTasks(tasks);  // Save the tasks to file
        System.out.println("EchoBox: OK, I've unmarked this task. Let's get it done soon! 💪");
        System.out.println("  " + tasks.get(taskNum)); // Displays updated task
    }

    private static void handleAddTodo(String input) throws EchoBoxException {
        String[] parts = input.split(" ", 2); // Split only into two parts: "todo" and description
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EchoBoxException("EchoBox:OOPS!! Task description cannot be empty!");
        }
        String taskDescription = parts[1].trim(); // Get the task description
        tasks.add(new Todo(taskDescription)); // Create the new Todo task
        DataManager.saveTasks(tasks);  // Save the tasks to file
        printTaskAdded();
    }

    private static void handleAddDeadline(String input) throws EchoBoxException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw new EchoBoxException("EchoBox: Incorrect format! Use: deadline <desc> /by <time>");
        }
        tasks.add(new Deadline(parts[0], parts[1]));
        DataManager.saveTasks(tasks);  // Save the tasks to file
        printTaskAdded();
    }

    private static void handleAddEvent(String input) throws EchoBoxException {
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new EchoBoxException("EchoBox: Incorrect format! Use: event <desc> /from <start> /to <end>");
        }
        if (parts[0].isEmpty()) {
            throw new EchoBoxException("EchoBox: Task description cannot be empty!");
        }
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        DataManager.saveTasks(tasks);  // Save the tasks to file
        printTaskAdded();
    }

    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

