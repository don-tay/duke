
public abstract class AbstractTask implements Task {

    String taskName;
    boolean isDone;

    public AbstractTask(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    protected String taskStateString(){
        return (this.isDone) ? "[✓]" : "[✗]";
    }

    @Override
    public String getTaskName() {
        return this.taskName;
    }

    public void markDone(){
        this.isDone = true;
    }

}
