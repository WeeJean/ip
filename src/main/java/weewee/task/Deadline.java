package weewee.task;

import weewee.exception.WeeweeException;
import weewee.parser.DateParser;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String taskName, String dateTime) throws WeeweeException {
        super(taskName);
        try {
            this.dateTime = DateParser.dateParse(dateTime);  // may throw DateTimeParseException
        } catch (Exception e) {
            throw new WeeweeException("Deadline date/time format is wrong baka >v<! Use YYYY-MM-DD HHmm");
        }
    }

    //nice output
    public String getDate() {
        return DateParser.displayFormat(this.dateTime);
    }

    //strict input for save
    public String getRawDate() {
        return DateParser.formatForSave(dateTime); // saving
    }

    @Override
    public String toString() {
        String s = String.format("[D]%s %s (by: %s)", this.getIsdone(), super.getTaskName(), getDate());
        return s;
    }
}
