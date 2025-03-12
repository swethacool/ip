import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command that searches for tasks containing a specific keyword in their description.
 * The command extracts the keyword from the input, searches the task list, and displays matching tasks.
 */
public class FindCommand extends Command{
    FindCommand(String input){
        super(input);
    }
    @Override
    void execute(Ui ui, TaskList tasks, Storage storage) throws EchoBoxException {
        String keyword = input.substring(5).trim(); // Extract keyword after 'find '
        if (keyword.isEmpty()) {
            ui.showMessage("OOPS!!! Please provide a keyword to search for.");
            return;
        }
        List<Task> matchingTasks = new ArrayList<>();

        // Loop through all tasks and check if the description contains the keyword
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found with the keyword: " + keyword); //no match
        }
        else {
            ui.showMessage("Here are the matching tasks in your list:"); //match spotted
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
