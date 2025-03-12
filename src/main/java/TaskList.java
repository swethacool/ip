import task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a collection of tasks.
 * It provides methods to add, delete, and retrieve tasks, as well as check the list size or emptiness.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on the provided index.
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by index.
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     * @return True if the TaskList is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
