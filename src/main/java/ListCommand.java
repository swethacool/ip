import task.Task;
import java.util.List;

/**
 * Represents a command that lists all the tasks in the current task list.
 * If the task list is empty, it displays a message indicating so. Otherwise, it displays all tasks.
 */
public class ListCommand extends Command{
   ListCommand(String input){
       super(input);
   }

   @Override
   void execute(Ui ui, TaskList tasks, Storage storage){
       List<Task> taskList = tasks.getTasks();
       if (taskList.isEmpty()) {
           ui.showMessage("EchoBox: No tasks in your list!");
       }
       else {
           for (int i = 0; i < taskList.size(); i++) {
               ui.showMessage((i + 1) + ". " + taskList.get(i));
           }
       }
   }
}
