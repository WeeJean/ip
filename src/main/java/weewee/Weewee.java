package weewee;

import weewee.storage.Storage;
import weewee.task.TaskList;
import weewee.ui.Ui;
import weewee.parser.CommandParser;
import weewee.exception.WeeweeException;

public class Weewee {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Weewee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() {
        ui.showGreet();
        String input = ui.readNextCommand();

        while(!input.equals("bye")) {
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

    public static void main(String[] args) {
        new Weewee("./data/weewee.txt").run();
    }
}