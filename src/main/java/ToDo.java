public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String s = String.format("[T]%s %s", this.getIsdone(), super.getTaskName());
        return s;
    }
}
