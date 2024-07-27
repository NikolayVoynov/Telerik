import java.time.LocalDate;
import java.util.ArrayList;

public class BoardItem {
    private static final int MIN_TITLE_LENGTH = 5;
    private static final int MAX_TITLE_LENGTH = 30;
    private static final String PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS =
            "Please provide a title with length between 5 and 30 chars";

    private String title;
    private LocalDate dueDate;
    private Status status;
    private ArrayList<EventLog> history;

    public BoardItem(String title, LocalDate dueDate) {
        this(title,dueDate, Status.OPEN);
    }

    public BoardItem(String title, LocalDate dueDate, Status status) {
        validateTitleLength(title);
        validateDueDateIsNotInThePast(dueDate);

        this.title = title;
        this.dueDate = dueDate;
        history = new ArrayList<>();
        this.status = status;

        logEvent(String.format("Item created: %s", viewInfo()));
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitleLength(title);

        logEvent(String.format("Title changed from %s to %s", this.title, title));

        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        validateDueDateIsNotInThePast(dueDate);

        logEvent(String.format("DueDate changed from %s to %s", this.dueDate, dueDate));

        this.dueDate = dueDate;
    }


    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public void revertStatus() {
        switch (status) {
            case TODO:
                setStatus(Status.OPEN);
                logEvent(String.format("Status changed from %s to %s", Status.TODO, Status.OPEN));
                break;
            case IN_PROGRESS:
                setStatus(Status.TODO);
                logEvent(String.format("Status changed from %s to %s", Status.IN_PROGRESS, Status.TODO));
                break;
            case DONE:
                setStatus(Status.IN_PROGRESS);
                logEvent(String.format("Status changed from %s to %s", Status.DONE, Status.IN_PROGRESS));
                break;
            case VERIFIED:
                setStatus(Status.DONE);
                logEvent(String.format("Status changed from %s to %s", Status.VERIFIED, Status.DONE));
                break;
            default:
                logEvent("Can't revert, already at Open");
        }
    }

    public void advanceStatus() {
        switch (status) {
            case OPEN:
                setStatus(Status.TODO);
                logEvent(String.format("Status changed from %s to %s", Status.OPEN, Status.TODO));
                break;
            case TODO:
                setStatus(Status.IN_PROGRESS);
                logEvent(String.format("Status changed from %s to %s", Status.TODO, Status.IN_PROGRESS));
                break;
            case IN_PROGRESS:
                setStatus(Status.DONE);
                logEvent(String.format("Status changed from %s to %s", Status.IN_PROGRESS, Status.DONE));
                break;
            case DONE:
                setStatus(Status.VERIFIED);
                logEvent(String.format("Status changed from %s to %s", Status.DONE, Status.VERIFIED));
                break;
            default:
                logEvent("Can't advance, already at Verified");
        }
    }

    protected String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

    public String getHistory() {
        StringBuilder builder = new StringBuilder();

        for (EventLog event : history) {
            builder.append(event.viewInfo()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    protected void logEvent(String eventString) {
        this.history.add(new EventLog(eventString));
    }

    private static void validateTitleLength(String title) {
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(PLEASE_PROVIDE_A_TITLE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS);
        }
    }

    private static void validateDueDateIsNotInThePast(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date should not be in the past");
        }
    }
}
