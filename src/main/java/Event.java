import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String taskName, String start, String end) throws WeeweeException {
        super(taskName);
        try {
            this.start = DateParser.dateParse(start);
            this.end = DateParser.dateParse(end);
        } catch (Exception e) {
            throw new WeeweeException("Event date/time format is wrong baka >v<! Use YYYY-MM-DD HHmm");
        }
    }

    //nice output
    public String getStart() {
        return DateParser.displayFormat(start);
    }

    //strict input for save
    public String getRawStart() {
        return DateParser.formatForSave(start);
    }

    //nice output
    public String getEnd() {
        return DateParser.displayFormat(end);
    }

    //strict input for save
    public String getRawEnd() {
        return DateParser.formatForSave(end);
    }

    @Override
    public String toString() {
        String s = String.format("[E]%s %s (from: %s to: %s)", this.getIsdone(), super.getTaskName(), getStart(), getEnd());
        return s;
    }
}
