public class Event extends Task{
    private String start;
    private String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String s = String.format("[E]%s %s (from: %s to: %s)", this.getIsdone(), super.getTaskName(), this.start, this.end);
        return s;
    }
}
