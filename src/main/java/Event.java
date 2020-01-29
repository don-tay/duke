import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extends from AbstractTask with additional attribute of date and time (optional).
 */
public class Event extends AbstractTask {
    LocalDate date;
    String time;
    String[] parseDateTime;

    /**
     * Constructor
     * @param taskName Name of task to be done
     * @param dateTime Date and time of task as a String
     */
    public Event(String taskName, String dateTime){
        super(taskName);
        parseDateTime = dateTime.split(" ",  3);
        this.date = LocalDate.parse(parseDateTime[1]);
        if (parseDateTime.length > 2){
            this.time = parseDateTime[2];
        }
    }

    /**
     * Formats date from yyyy-mm-dd to MMM d yyyy
     * @return
     */
    private String formattedDate(){
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString(){
        return (this.time != null) ? "[E]" + taskStateString() + " " + this.taskName + parseDateTime[0] + " " + formattedDate() + " " + this.time
                : "[E]" + taskStateString() + " " + this.taskName + parseDateTime[0] + " " + formattedDate();
    }

}