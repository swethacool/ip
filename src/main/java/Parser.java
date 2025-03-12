import task.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static Command parse(String input) {
        String command = input.trim().toLowerCase();
        if (command.equals("bye")) {
            return new ByeCommand(input);
        }
        else if (command.equals("list")) {
            return new ListCommand(input);
        }
        else if (command.startsWith("mark ")) {
            return new MarkCommand(input);
        }
        else if (command.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        }
        else if (command.startsWith("find ")) {
            return new FindCommand(input);
        }
        else if (command.startsWith("todo ")) {
            return new TodoCommand(input);
        }
        else if (command.startsWith("deadline ")) {
            return new DeadlineCommand(input);
        }
        else if (command.startsWith("event ")) {
            return new EventCommand(input);
        }
        else if (command.startsWith("delete ")) {
            return new DeleteCommand(input);
        }
        else if (command.equals("sad")) {
            return new FeelingsCommand(input, true);
        }
        else if (command.equals("good")) {
            return new FeelingsCommand(input, false);
        }
        else {
            return new Command(input);
        }
    }
}
