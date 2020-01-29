import java.util.List;

/**
 * Class containing the list of tasks when the "list" command is used
 */
public class TaskList {
    List<Task> taskList;
    Storage storage;

    /**
     * Constructor
     * @param storage the Storage disk where this taskList is stored in
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.loadTask();
    }

    /**
     * Add task to taskList. And prints add response to UI.
     * @param taskName Command followed by the name of task to be done.
     *                 Time is optional, demarcated by an additional spacing.
     */
    public void addTask(String taskName){
        try {
            String taskType = taskName.split(" ", 2)[0];
            String taskDesc = taskName.split(" ", 2)[1];
            Task newTask;
            if (taskType.equals("todo")) {
                newTask = new ToDo(taskDesc);
            } else if (taskType.equals("deadline")) {
                String[] in = taskDesc.split("/",2);
                newTask = new Deadline(in[0], in[1]);
            } else {
                String[] in = taskDesc.split("/", 2);
                newTask = new Event(in[0], in[1]);
            }
            taskList.add(newTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e){
            System.err.println("     ☹ OOPS!!! The description of a task cannot be empty.");
        } finally {
            storage.saveTask(taskList);
        }
    }

    /**
     * Delete task from taskList. And prints delete response to UI.
     * @param taskName Command followed by the name of task to be done.
     *                 Time is optional, demarcated by an additional spacing.
     */
    public void deleteTask(String taskName){
        try {
            String taskNum = taskName.split(" ", 2)[1];
            Task currTask = taskList.get(Integer.parseInt(taskNum) - 1);
            taskList.remove(currTask);
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + currTask);
            System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e){
            System.err.println("     ☹ OOPS!!! Please input a valid number in the range of the task list to delete.");
        } catch (NumberFormatException e) { // when non-int arg provided
            System.err.println("OOPS!!! Delete must take a valid integer in the range of the task list.");
        } finally {
            storage.saveTask(taskList);
        }
    }

    /**
     * Prints the list of tasks out.
     */
    public void printList(){
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for(Task task : taskList){
            System.out.println("     " + i + "." + task);
            i++;
        }
    }

    /**
     * Marks a particular task number, ordered by its position in taskList, as done, and prints done response.
     * @param in Task number that is to be marked done
     */
    public void printDone(String in){
        try{
            int num = Integer.parseInt(in.substring(5));
            System.out.println("     Nice! I've marked this task as done: ");
            Task taskDone = taskList.get(num - 1);
            taskDone.markDone();
            String out =  "       " + taskDone;
            System.out.println(out);
        } catch (IndexOutOfBoundsException e){ // when no int arg provided
            System.err.println("OOPS!!! Done must take a valid number in the range of the task list.");
        } catch (NumberFormatException e) { // when non-int arg provided
            System.err.println("OOPS!!! Done must take a valid integer in the range of the task list.");
        } finally {
            storage.saveTask(taskList);
        }
    }
}
