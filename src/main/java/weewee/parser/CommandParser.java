package weewee.parser;

import java.util.ArrayList;

import weewee.exception.WeeweeException;
import weewee.task.Event;
import weewee.task.Task;
import weewee.task.TaskList;
import weewee.task.ToDo;
import weewee.task.Deadline;
import weewee.ui.Ui;

public class CommandParser {

    public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, UNIDENTIFIED
    }

    public static Command getCommand(String input) {
        if (input.equals("list")) return Command.LIST;
        if (input.startsWith("mark")) return Command.MARK;
        if (input.startsWith("unmark")) return Command.UNMARK;
        if (input.startsWith("delete")) return Command.DELETE;
        if (input.startsWith("todo")) return Command.TODO;
        if (input.startsWith("deadline")) return Command.DEADLINE;
        if (input.startsWith("event")) return Command.EVENT;
        if (input.startsWith("find")) return Command.FIND;
        return Command.UNIDENTIFIED;
    }

    public static void parseAndExecute(String input, TaskList tasks, Ui ui) throws WeeweeException {
        Command cmd = getCommand(input);
        switch (cmd) {
        case LIST:
            ui.showList(tasks);
            break;

        case MARK:
            String[] marksplit = input.split(" ");
            int marknumber = Integer.parseInt(marksplit[1]);
            if (marksplit.length != 2 || marknumber < 1 || marknumber > tasks.size()) {
                throw new WeeweeException("Baka only valid task number is allowed!\n");
            }

            tasks.get(marknumber - 1).setDone();
            ui.showMark(tasks.get(marknumber - 1));
            break;

        case UNMARK:
            String[] unmarksplit = input.split(" ");
            int unmarknumber = Integer.parseInt(unmarksplit[1]);
            if (unmarksplit.length != 2 || unmarknumber < 1 || unmarknumber > tasks.size()) {
                throw new WeeweeException("Baka only valid task number is allowed!\n");
            }

            tasks.get(unmarknumber - 1).setUndone();
            ui.showUnmark(tasks.get(unmarknumber - 1));
            break;

        case DELETE:
            String[] deletesplit = input.split(" ");
            int deletenumber = Integer.parseInt(deletesplit[1]);
            if (deletesplit.length != 2 || deletenumber < 1 || deletenumber > tasks.size()) {
                throw new WeeweeException("Baka only valid task number is allowed!\n");
            }

            Task deleted = tasks.remove(deletenumber - 1);
            ui.showDelete(deleted, tasks);
            break;

        case TODO:
            String[] todosplit = input.split("todo ");
            if (todosplit.length < 2) {
                throw new WeeweeException("toDo format is wrong baka >v< ! e.g todo <activity>\n");
            }

            Task todo = new ToDo(todosplit[1].trim());
            tasks.add(todo);
            ui.showTodo(todo, tasks);
            break;

        case DEADLINE:
            String[] deadlinesplit = input.split("deadline | /by ");
            if (deadlinesplit.length < 3) {
                throw new WeeweeException("Deadline format is wrong baka >v< ! e.g deadline <activity> /by <YYYY-MM-DD HHmm>\n");
            }

            Task deadline = new Deadline(deadlinesplit[1].trim(), deadlinesplit[2].trim());
            tasks.add(deadline);
            ui.showDeadline(deadline, tasks);
            break;

        case EVENT:
            String[] eventsplit = input.split("event | /from | /to ");
            if (eventsplit.length < 4) {
                throw new WeeweeException("Event format is wrong baka >v<! e.g event <activity> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>\n");
            }

            Task event = new Event(eventsplit[1].trim(), eventsplit[2].trim(), eventsplit[3].trim());
            tasks.add(event);
            ui.showEvent(event, tasks);
            break;

        case FIND:
            String[] findsplit = input.split("\\s+");
            if (findsplit.length < 2) {
                throw new WeeweeException("Find format is wrong baka >v<! e.g find <keyword>\n");
            }

            TaskList matchingTasks = new TaskList(new ArrayList<>());

            for (int i = 0; i< tasks.size(); i++) {
                boolean matching = true;
                for (int j = 1; j < findsplit.length; j++) {
                    if (!tasks.get(i).getTaskName().matches(".*\\b" + findsplit[j].toLowerCase() + "\\b.*")) {
                        matching = false;
                        break;
                    }
                }
                if (matching) {
                    matchingTasks.add(tasks.get(i));
                }
            }

            ui.showFind(matchingTasks);
            break;

        case UNIDENTIFIED:
            throw new WeeweeException("Sorry, I don't understand what that means </3\n");
        }
    }
}
