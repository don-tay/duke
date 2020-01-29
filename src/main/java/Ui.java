import java.util.Scanner;

public class Ui {
//            String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
    String logo = "DUKE!\n";
    Scanner sc;
    Parser p;
    TaskList taskList;

    /**
     * Interface which user interacts with, with corresponding Parser instance.
     */
    public Ui(){
        sc = new Scanner(System.in);
        p = new Parser();
        System.out.println("Hello from " + logo);
    }

    /**
     * Sets taskList that user using Ui will interact with.
     * Ui interacts with one taskList at any one time.
     * @param taskList taskList to be interacted with
     */
    public void setTaskList(TaskList taskList){
        this.taskList = taskList;
        p.setTaskList(taskList);
    }

    /**
     * Accepts user input to be parsed by Parser instance p.
     * Terminates when user input "bye"
     */
    public void awaitUserInput(){
        String in = sc.nextLine();
        while (!in.equals("bye")){
            p.getInput(in);
            in = sc.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
