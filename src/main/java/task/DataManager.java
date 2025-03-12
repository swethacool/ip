package task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages loading and saving tasks to and from a file.
 * It handles file operations, including checking the existence of the file
 * and ensuring the necessary directories are created.
 */
public class DataManager {
    private static final String FILE_PATH = "./data/EchoBox.txt"; // File path for the task list
    // Ensure data folder exists
    static {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs(); // Create the folder if it doesn't exist
        }
    }

    /**
     * Loads the list of tasks from the file.
     * It reads the file, parses each task, and returns a list of Task objects.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list of tasks read from the file.
     */
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

                // Create tasks based on type
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

    /**
     * Saves the given list of tasks to the file.
     * Each task is converted into a string format and written to the file.
     * @param tasks The list of tasks to be saved.
     */
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
