import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    private static final String DESCRIPTION_CANNOT_BE_EMPTY = "Description cannot be empty";
    private static LocalDateTime DATE_TIME_OF_EVENT = LocalDateTime.now();

    private final String description;
    private final LocalDateTime timeStamp;

    public EventLog() {
        throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
    }

    public EventLog(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
        }

        this.description = description;
        this.timeStamp = EventLog.DATE_TIME_OF_EVENT;
    }

    public String getDescription() {
        return description;
    }

    public String viewInfo() {
        String formatDateTime = this.timeStamp.format(formatter);

        return String.format("[%s] %s", formatDateTime, description);
    }
}
