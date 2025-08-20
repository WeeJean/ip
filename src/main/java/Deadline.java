public class Deadline extends Task{
    private String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        String s = String.format("[D]%s %s (by: %s)", this.getIsdone(), super.getTaskName(), this.date);
        return s;
    }
}
