import task.Task;
import java.util.List;
import task.DataManager;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It uses DataManager to perform the actual file operations such load and save.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws EchoBoxException {
        return DataManager.loadTasks(); //DataManager is implemented for loading
    }

    public void saveTasks(List<Task> tasks) {
        DataManager.saveTasks(tasks); //DataManager is implemented for saving
    }
}

