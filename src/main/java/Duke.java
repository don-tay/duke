public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor
     * @param filePath filepath of where the taskList's storage will be.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
            tasks = new TaskList(storage);
            ui.setTaskList(tasks);
    }

    /**
     * Run Duke.
     */
    public void run() {
        ui.awaitUserInput();
    }

    /**
     * Main driver.
     * @param args boilerplate code.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
