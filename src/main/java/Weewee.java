import java.util.Scanner;

public class Weewee {
    public static void main(String[] args) {
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input+ "\n");
            input = sc.nextLine();
        }

        System.out.println(bye);
    }
}
