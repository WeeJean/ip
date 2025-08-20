import java.util.ArrayList;
import java.util.Scanner;

public class Weewee {
    public static void main(String[] args) {
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";
        ArrayList<Task> tasks = new ArrayList<>(100);

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            //if input in command: list
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
                input = sc.nextLine();

            //if input in command: mark
            } else if (input.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:\n");
                //method split inspired by https://www.geeksforgeeks.org/java/split-string-java-examples/
                String[] splitinput = input.split(" ");
                if (splitinput.length == 2) {
                    String numberPart = splitinput[1];   // "2"
                    int number = Integer.parseInt(numberPart);
                    tasks.get(number - 1).setDone();
                    System.out.println(tasks.get(number - 1).toString());
                }
                input = sc.nextLine();

                //if input in command: unmark
            } else if (input.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:\n");
                String[] splitinput = input.split(" ");

                if (splitinput.length == 2) {
                    String numberPart = splitinput[1];   // "2"
                    int number = Integer.parseInt(numberPart);
                    tasks.get(number - 1).setUndone();
                    System.out.println(tasks.get(number - 1).toString());
                }

                input = sc.nextLine();

               //if input is task
            } else {
                if (input.startsWith("todo")) {
                    String[] splitinput = input.split("todo ");
                    Task newTask = new ToDo(splitinput[1]);
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                    input = sc.nextLine();

                } else if (input.startsWith("deadline")) {
                    String[] splitinput = input.split("deadline | /by ");
                    Task newTask = new Deadline(splitinput[1], splitinput[2]);
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                    input = sc.nextLine();

                } else if (input.startsWith("event")) {
                    String[] splitinput = input.split("event | /from | /to ");
                    Task newTask = new Event(splitinput[1], splitinput[2], splitinput[3]);
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                    input = sc.nextLine();
                } else {
                    //if input is not a command: echo
                    System.out.println(input);
                    input = sc.nextLine();
                }
            }
        }
        System.out.println(bye);
    }
}
