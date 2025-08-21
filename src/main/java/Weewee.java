import java.util.ArrayList;
import java.util.Scanner;

public class Weewee {
    public static void main(String[] args) {
        String greet = "Hello! I'm Weewee\n" + "What can I do for you?";
        String bye = "Bye. Hope to see you again soon! smoochsmooch <3";
        ArrayList<Task> tasks = new ArrayList<>(100);

        System.out.println(greet + "\n");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            try{
                //if input in command: list
                if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new WeeweeException("Your list is empty UwU!\n");
                    }
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    input = sc.nextLine();

                    //if input in command: mark
                } else if (input.startsWith("mark")) {
                    //method split inspired by https://www.geeksforgeeks.org/java/split-string-java-examples/
                    String[] splitinput = input.split(" ");
                    String numberPart = splitinput[1];
                    int number = Integer.parseInt(numberPart);
                    if (splitinput.length != 2 || number < 1 || number > tasks.size()) {
                        throw new WeeweeException("Baka only valid task number is allowed!\n");
                    }
                    System.out.println("Nice! I've marked this task as done:\n");
                    tasks.get(number - 1).setDone();
                    System.out.println(tasks.get(number - 1).toString());
                    input = sc.nextLine();

                    //if input in command: unmark
                } else if (input.startsWith("unmark")) {
                    String[] splitinput = input.split(" ");
                    String numberPart = splitinput[1];
                    int number = Integer.parseInt(numberPart);
                    if (splitinput.length != 2 || number < 1 || number > tasks.size()) {
                        throw new WeeweeException("Baka only valid task number is allowed!\n");
                    }
                    System.out.println("OK, I've marked this task as not done yet:\n");
                    tasks.get(number - 1).setUndone();
                    System.out.println(tasks.get(number - 1).toString());
                    input = sc.nextLine();

                } else if (input.startsWith("delete")) {
                    String[] splitinput = input.split(" ");
                    String numberPart = splitinput[1];
                    int number = Integer.parseInt(numberPart);
                    if (splitinput.length != 2 || number < 1 || number > tasks.size()) {
                        throw new WeeweeException("Baka only valid task number is allowed!\n");
                    }
                    System.out.printf("Noted. I've removed this task: \n%s \nNow you have %d tasks in the list.\n", tasks.get(number - 1).toString(), tasks.size() - 1);
                    tasks.remove(number - 1);
                    input = sc.nextLine();

                    //if input is task
                } else {
                    if (input.startsWith("todo")) {
                        String[] splitinput = input.split("todo ");
                        if (splitinput.length < 2) {
                            throw new WeeweeException("toDo format is wrong baka >v< ! e.g todo <activity>\n");
                        }
                        Task newTask = new ToDo(splitinput[1]);
                        tasks.add(newTask);
                        System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                        input = sc.nextLine();

                    } else if (input.startsWith("deadline")) {
                        String[] splitinput = input.split("deadline | /by ");
                        if (splitinput.length < 3) {
                            throw new WeeweeException("Deadline format is wrong baka >v< ! e.g deadline <activity> /by <date>\n");
                        }
                        Task newTask = new Deadline(splitinput[1], splitinput[2]);
                        tasks.add(newTask);
                        System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                        input = sc.nextLine();

                    } else if (input.startsWith("event")) {
                        String[] splitinput = input.split("event | /from | /to ");
                        if (splitinput.length < 4) {
                            throw new WeeweeException("Event format is wrong baka >v<! e.g event <activity> /from <date> /to <date>\n");
                        }
                        Task newTask = new Event(splitinput[1], splitinput[2], splitinput[3]);
                        tasks.add(newTask);
                        System.out.printf("Got it. I've added this task: \n%s \nNow you have %d tasks in the list.\n", newTask.toString(), tasks.size());
                        input = sc.nextLine();
                    } else {
                        //if input is not a command: throw exception
                        throw new WeeweeException("Sorry, I don’t understand what that means </3\n");
                    }
                }
            } catch (WeeweeException e) {
                System.out.println(" " + e.getMessage());
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println(" OOPS Something went wrong: " + e.getMessage());
                input = sc.nextLine();
            }
        }
        System.out.println(bye);
    }
}
