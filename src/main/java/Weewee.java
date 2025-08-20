import java.util.ArrayList;
import java.util.Scanner;

public class Weewee {
    public static void main(String[] args) {
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";
        ArrayList<Task> toDo = new ArrayList<>(100);

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            //if input in command: list
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < toDo.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, toDo.get(i));
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
                    toDo.get(number - 1).setDone();
                    System.out.println(toDo.get(number - 1).toString());
                }
                input = sc.nextLine();

                //if input in command: unmark
            } else if (input.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:\n");
                String[] splitinput = input.split(" ");

                if (splitinput.length == 2) {
                    String numberPart = splitinput[1];   // "2"
                    int number = Integer.parseInt(numberPart);
                    toDo.get(number - 1).setUndone();
                    System.out.println(toDo.get(number - 1).toString());
                }

                input = sc.nextLine();

               //if input is task
            } else {
                System.out.println("added: " + input + "\n");
                Task newTask = new Task(input);
                toDo.add(newTask);
                input = sc.nextLine();
            }
        }
        System.out.println(bye);
    }
}
