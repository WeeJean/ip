import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showGreet() {
            System.out.println("Hello! I'm Weewee\nWhat can I do for you?\n");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon! smoochsmooch <3");
    }

    public String readNextCommand() {
        return sc.nextLine();
    }

    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty UwU!\n");
            return;
        }
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        System.out.println();
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task);
        System.out.println();
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(task);
        System.out.println();
    }

    public void showDelete(Task task, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    public void showTodo(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    public void showDeadline(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    public void showEvent(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

}
