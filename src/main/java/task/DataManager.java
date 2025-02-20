package task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String FILE_PATH = "./data/EchoBox.txt"; // File path for the task list

    // Ensure data folder exists
    static {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs(); // Create the folder if it doesn't exist
        }
    }

    // Method to load tasks from the file
    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Check if file exists, if not return empty task list
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task;

                // Create tasks based on type (you can add more types like Deadline, Event)
                switch (taskType) {
                    case "T":
                        task = new Todo(description);
                        task.isDone = isDone;
                        break;
                    case "D":
                        String by = parts[3]; // Deadline's "by" value
                        task = new Deadline(description, by);
                        task.isDone = isDone;
                        break;
                    case "E":
                        String from = parts[3]; // Event's "from" value
                        String to = parts[4];   // Event's "to" value
                        task = new Event(description, from, to);
                        task.isDone = isDone;
                        break;
                    default:
                        task = new Task(description);
                        task.isDone = isDone;
                        break;
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Method to save tasks to the file
    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString()); // Convert task to the required string format
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
