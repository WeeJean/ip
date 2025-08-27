import java.util.ArrayList;

public class Weewee {

    public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNIDENTIFIED
    }

    public static Command getCommand(String input) {
        if (input.equals("list")) return Command.LIST;
        if (input.startsWith("mark")) return Command.MARK;
        if (input.startsWith("unmark")) return Command.UNMARK;
        if (input.startsWith("delete")) return Command.DELETE;
        if (input.startsWith("todo")) return Command.TODO;
        if (input.startsWith("deadline")) return Command.DEADLINE;
        if (input.startsWith("event")) return Command.EVENT;
        return Command.UNIDENTIFIED;
     }

    public static void main(String[] args) {
        Storage storage = new Storage("./data/weewee.txt");
        ArrayList<Task> tasks = storage.load();

        Ui ui = new Ui();
        ui.showGreet();
        String input = ui.readNextCommand();

        while(!input.equals("bye")) {
            try{
                Command cmd = getCommand(input);
                switch (cmd) {
                    //if input in command: list
                    case LIST:
                        ui.showList(tasks);

                        input = ui.readNextCommand();
                        break;

                    case MARK:
                        String[] marksplit = input.split(" ");
                        int marknumber = Integer.parseInt(marksplit[1]);
                        if (marksplit.length != 2 || marknumber < 1 || marknumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }

                        tasks.get(marknumber - 1).setDone();
                        ui.showMark(tasks.get(marknumber - 1));
                        input = ui.readNextCommand();
                        break;

                    case UNMARK:
                        String[] unmarksplit = input.split(" ");
                        int unmarknumber = Integer.parseInt(unmarksplit[1]);
                        if (unmarksplit.length != 2 || unmarknumber < 1 || unmarknumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }

                        tasks.get(unmarknumber - 1).setUndone();
                        ui.showUnmark(tasks.get(unmarknumber - 1));
                        input = ui.readNextCommand();
                        break;

                    case DELETE:
                        String[] deletesplit = input.split(" ");
                        int deletenumber = Integer.parseInt(deletesplit[1]);
                        if (deletesplit.length != 2 || deletenumber < 1 || deletenumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }

                        tasks.remove(deletenumber - 1);
                        ui.showDelete(tasks.get(deletenumber - 1), tasks);
                        input = ui.readNextCommand();
                        break;

                    case TODO:
                        String[] todosplit = input.split("todo ");
                        if (todosplit.length < 2) {
                            throw new WeeweeException("toDo format is wrong baka >v< ! e.g todo <activity>\n");
                        }

                        Task todo = new ToDo(todosplit[1].trim());
                        tasks.add(todo);
                        ui.showTodo(todo, tasks);
                        input = ui.readNextCommand();
                        break;

                    case DEADLINE:
                        String[] deadlinesplit = input.split("deadline | /by ");
                        if (deadlinesplit.length < 3) {
                            throw new WeeweeException("Deadline format is wrong baka >v< ! e.g deadline <activity> /by <YYYY-MM-DD HHmm>\n");
                        }

                        Task deadline = new Deadline(deadlinesplit[1].trim(), deadlinesplit[2].trim());
                        tasks.add(deadline);
                        ui.showDeadline(deadline, tasks);
                        input = ui.readNextCommand();
                        break;

                    case EVENT:
                        String[] eventsplit = input.split("event | /from | /to ");
                        if (eventsplit.length < 4) {
                            throw new WeeweeException("Event format is wrong baka >v<! e.g event <activity> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm>\n");
                        }

                        Task event = new Event(eventsplit[1].trim(), eventsplit[2].trim(), eventsplit[3].trim());
                        tasks.add(event);
                        ui.showEvent(event, tasks);
                        input = ui.readNextCommand();
                        break;

                    case UNIDENTIFIED:
                        throw new WeeweeException("Sorry, I don’t understand what that means </3\n");
                }
            } catch (WeeweeException e) {
                System.out.println(e.getMessage());
                input = ui.readNextCommand();
            } catch (Exception e) {
                System.out.println("OOPS Something went wrong: " + e.getMessage());
                input = ui.readNextCommand();
            }
        }
        storage.save(tasks);
        ui.showBye();
    }
}
