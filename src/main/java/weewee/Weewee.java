package weewee;

import weewee.exception.WeeweeException;
import weewee.parser.CommandParser;
import weewee.storage.Storage;
import weewee.task.TaskList;
import weewee.ui.Ui;

/**
 * The main entry point for the Weewee chatbot application.
 * This class initializes the UI, storage, and task list components.
 * It manages the main program loop: reading user commands,
 * executing them, handling errors, and saving tasks upon exit.
 */
public class Weewee {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new instance of the chatbot with a file path for storing tasks.
     *
     * @param filePath String of the saved file path.
     */
    public Weewee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Starts the chatbot loop and terminate after bye command
     */
    public void run() {
        ui.showGreet();
        String input = ui.readNextCommand();

        while (!input.equals("bye")) {
            try {
                CommandParser.parseAndExecute(input, tasks, ui);
            } catch (WeeweeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("OOPS Something went wrong: " + e.getMessage());
            }

            input = ui.readNextCommand();
        }
        storage.save(tasks);
        ui.showBye();
    }

    /**
     * Application entry point.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Weewee("./data/weewee.txt").run();
    }
}
