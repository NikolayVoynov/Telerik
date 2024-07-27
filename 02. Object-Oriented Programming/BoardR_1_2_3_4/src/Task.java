import java.time.LocalDate;

public class Task extends BoardItem {
    private static final int MIN_ASSIGNEE_LENGTH = 5;
    private static final int MAX_ASSIGNEE_LENGTH = 30;
    private static final String PLEASE_PROVIDE_ASSIGNEE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS =
            "Please provide a assignee with length between %d and %d chars";

    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        validateAssigneeLength(assignee);
        this.assignee = assignee;
    }

    public String getAssignee(){

        return assignee;
    }

    private void setAssignee(String assignee) {
        validateAssigneeLength(assignee);
        logEvent(String.format("Assignee changed from %s to %s", this.assignee, assignee));
        this.assignee = assignee;
    }

    private static void validateAssigneeLength(String assignee) {
        if (assignee.length() < MIN_ASSIGNEE_LENGTH || assignee.length() > MAX_ASSIGNEE_LENGTH) {
            throw new IllegalArgumentException(String.format(PLEASE_PROVIDE_ASSIGNEE_WITH_LENGTH_BETWEEN_5_AND_30_CHARS,
                    MIN_ASSIGNEE_LENGTH,
                    MAX_ASSIGNEE_LENGTH));
        }
    }

    @Override
    public String viewInfo(){
        String baseInfo = super.viewInfo();

        return String.format("Task: %s, Assignee: %s", baseInfo, this.getAssignee());
    }
}
