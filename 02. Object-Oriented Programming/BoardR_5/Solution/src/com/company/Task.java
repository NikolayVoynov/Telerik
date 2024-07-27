package com.company;

import java.time.LocalDate;

public class Task extends BoardItem {

    private static final int ASSIGNEE_MIN_LENGTH = 5;
    private static final int ASSIGNEE_MAX_LENGTH = 30;

    private String assignee;

    public Task(String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);

        ensureValidAssignee(assignee);
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        ensureValidAssignee(assignee);

        logEvent(String.format("Assignee changed from %s to %s", this.getAssignee(), assignee));

        this.assignee = assignee;
    }

    private void ensureValidAssignee(String assignee) {
        if (assignee.length() < ASSIGNEE_MIN_LENGTH || assignee.length() > ASSIGNEE_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(
                    "The assignee's name must be between %d and %d characters.",
                    ASSIGNEE_MIN_LENGTH, ASSIGNEE_MAX_LENGTH));
        }
    }

    @Override
    public void revertStatus() {
        switch (getStatus()) {
            case INPROGRESS:
                setStatus(Status.TODO);
                logEvent(String.format("Task status changed from %s to %s", Status.INPROGRESS, Status.TODO));
                break;
            case DONE:
                setStatus(Status.INPROGRESS);
                logEvent(String.format("Task status changed from %s to %s", Status.DONE, Status.INPROGRESS));
                break;
            case VERIFIED:
                setStatus(Status.DONE);
                logEvent(String.format("Task status changed from %s to %s", Status.VERIFIED, Status.DONE));
                break;
            default:
                logEvent("Can't revert, already at To do");
        }
    }

    @Override
    public void advanceStatus() {
        switch (getStatus()) {
            case TODO:
                setStatus(Status.INPROGRESS);
                logEvent(String.format("Task status changed from %s to %s", Status.TODO, Status.INPROGRESS));
                break;
            case INPROGRESS:
                setStatus(Status.DONE);
                logEvent(String.format("Task status changed from %s to %s", Status.INPROGRESS, Status.DONE));
                break;
            case DONE:
                setStatus(Status.VERIFIED);
                logEvent(String.format("Task status changed from %s to %s", Status.DONE, Status.VERIFIED));
                break;
            default:
                logEvent("Can't advance, already at Verified");
        }
    }
}
