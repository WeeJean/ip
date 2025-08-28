package weewee.ui;

import weewee.task.TaskList;
import weewee.task.Task;

import java.util.Scanner;

/**
 * Handles user interaction for the Weewee chatbot.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /** Prints a greeting message when the chatbot starts. */
    public void showGreet() {
            System.out.println("Hello! I'm Weewee\nWhat can I do for you?\n");
    }

    /** Prints a goodbye message when the chatbot ends. */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon! smoochsmooch <3");
    }

    /** @return The raw input string typed by the user. */
    public String readNextCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list to display.
     */
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

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param tasks The updated task list.
     */
    public void showDelete(Task task, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    /**
     * Displays a message confirming that a todo task has been added.
     *
     * @param task  The task that was added.
     * @param tasks The updated task list.
     */
    public void showTodo(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    /** Displays a message confirming that a deadline task has been added. */
    public void showDeadline(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

    /** Displays a message confirming that an event task has been added. */
    public void showEvent(Task task, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", task, tasks.size());
        System.out.println();
    }

}
