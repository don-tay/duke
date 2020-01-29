import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store the taskList.
 */
public class Storage {
    String path;
    static final String HOME = System.getProperty("user.dir");

    /**
     * Constructor.
     * @param filepath Filepath of Storage
     */
    public Storage(String filepath){
        path = HOME + "/" + filepath;
    }

    /**
     * Saves taskList to storage.
     * @param taskList taskList to be saved.
     */
    public void saveTask(List<Task> taskList){
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(taskList);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get taskList from storage. Usually executed at initial startup of Duke.
     * If not taskList found in storage, it will return an empty taskList initialized and stored in Storage filepath.
     * @return taskList.
     */
    @SuppressWarnings("unchecked")
    public List<Task> loadTask() {
        String parentDirectory = new File(path).getParent();
        if (!Files.exists(Paths.get(path))) { // if file or directory does not exist
            try {
                new File(parentDirectory).mkdirs(); // create new directory
                Files.createFile(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(path);
                if (fileIn.available() > 0) {
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    try {
                        List<Task> obj = (ArrayList<Task>) objectIn.readObject();
                        return obj;
                    } catch (IOException ex) {} // end file reading if EOF
                    finally {
                        objectIn.close();
                        fileIn.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
