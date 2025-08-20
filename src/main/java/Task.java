public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getIsdone() {
        if(isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String toString() {
        return getIsdone() + " " + taskName;
    }
}
