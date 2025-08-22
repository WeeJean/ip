import java.util.ArrayList;
import java.util.Scanner;

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
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon! smoochsmooch <3";
        ArrayList<Task> tasks = new ArrayList<>(100);

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            try{
                Command cmd = getCommand(input);
                switch (cmd) {
                    //if input in command: list
                    case LIST:
                        if (tasks.isEmpty()) {
                            throw new WeeweeException("Your list is empty UwU!\n");
                        }
                        System.out.println("Here are the tasks in your list:\n");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                        }
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case MARK:
                        String[] marksplit = input.split(" ");
                        int marknumber = Integer.parseInt(marksplit[1]);
                        if (marksplit.length != 2 || marknumber < 1 || marknumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }
                        System.out.println("Nice! I've marked this task as done:\n");
                        tasks.get(marknumber - 1).setDone();
                        System.out.println(tasks.get(marknumber - 1).toString());
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case UNMARK:
                        String[] unmarksplit = input.split(" ");
                        int unmarknumber = Integer.parseInt(unmarksplit[1]);
                        if (unmarksplit.length != 2 || unmarknumber < 1 || unmarknumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }
                        System.out.println("OK, I've marked this task as not done yet:\n");
                        tasks.get(unmarknumber - 1).setUndone();
                        System.out.println(tasks.get(unmarknumber - 1).toString());
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case DELETE:
                        String[] deletesplit = input.split(" ");
                        int deletenumber = Integer.parseInt(deletesplit[1]);
                        if (deletesplit.length != 2 || deletenumber < 1 || deletenumber > tasks.size()) {
                            throw new WeeweeException("Baka only valid task number is allowed!\n");
                        }
                        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", tasks.get(deletenumber - 1).toString(), tasks.size() - 1);
                        System.out.println();
                        tasks.remove(deletenumber - 1);
                        input = sc.nextLine();
                        break;

                    case TODO:
                        String[] todosplit = input.split("todo ");
                        if (todosplit.length < 2) {
                            throw new WeeweeException("toDo format is wrong baka >v< ! e.g todo <activity>\n");
                        }
                        Task todo = new ToDo(todosplit[1].trim());
                        tasks.add(todo);
                        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", todo.toString(), tasks.size());
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case DEADLINE:
                        String[] deadlinesplit = input.split("deadline | /by ");
                        if (deadlinesplit.length < 3) {
                            throw new WeeweeException("Deadline format is wrong baka >v< ! e.g deadline <activity> /by <date>\n");
                        }
                        Task deadline = new Deadline(deadlinesplit[1].trim(), deadlinesplit[2].trim());
                        tasks.add(deadline);
                        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", deadline.toString(), tasks.size());
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case EVENT:
                        String[] eventsplit = input.split("event | /from | /to ");
                        if (eventsplit.length < 4) {
                            throw new WeeweeException("Event format is wrong baka >v<! e.g event <activity> /from <date> /to <date>\n");
                        }
                        Task event = new Event(eventsplit[1].trim(), eventsplit[2].trim(), eventsplit[3].trim());
                        tasks.add(event);
                        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", event.toString(), tasks.size());
                        System.out.println();
                        input = sc.nextLine();
                        break;

                    case UNIDENTIFIED:
                        throw new WeeweeException("Sorry, I don’t understand what that means </3\n");
                }
            } catch (WeeweeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println("OOPS Something went wrong: " + e.getMessage());
                input = sc.nextLine();
            }
        }
        System.out.println(bye);
    }
}
