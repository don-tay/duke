import java.util.List;

public class TaskList {
    List<Task> taskList;
    Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.loadTask();
    }

    public void addTask(String taskName){
        try {
            String taskType = taskName.split(" ", 2)[0];
            String taskDesc = taskName.split(" ", 2)[1];
            Task newTask;
            if (taskType.equals("todo")) {
                newTask = new ToDo(taskDesc);
            } else if (taskType.equals("deadline")) {
                String[] in = taskDesc.split("/",2);
                String task = in[0].trim();
                String dateAndPreposition = in[1];
                String[] res = dateAndPreposition.split(" ", 2);
                String preposition = res[0];
                String dateTime = res[1];
                newTask = new Deadline(task, preposition, dateTime);
            } else {
                String[] in = taskDesc.split("/", 2);
                String task = in[0].trim();
                String dateAndPreposition = in[1];
                String[] res = dateAndPreposition.split(" ", 2);
                String preposition = res[0];
                String dateTime = res[1];
                newTask = new Event(task, preposition, dateTime);
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
    public void printList(){
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for(Task task : taskList){
            System.out.println("     " + i + "." + task);
            i++;
        }
    }

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
