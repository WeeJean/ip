import java.util.ArrayList;
import java.util.Scanner;

public class Weewee {
    public static void main(String[] args) {
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";
        ArrayList<String> toDo = new ArrayList<>(100);

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            //if input in command: list
            if (input.equals("list")) {
                for (int i = 0; i < toDo.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, toDo.get(i));
                }
                input = sc.nextLine();
            } else {
                //if input is task
                System.out.println("added: " + input + "\n");
                toDo.add(input);
                input = sc.nextLine();
            }
        }
        System.out.println(bye);
    }
}
