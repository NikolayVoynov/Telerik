package com.company;

import java.time.LocalDate;

public class Issue extends BoardItem {

    private final String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate, Status.OPEN);

        if (description.isEmpty()) {
            this.description = "No description";
        } else {
            this.description = description;
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void revertStatus() {
        if (getStatus().equals(Status.VERIFIED)) {
            setStatus(Status.OPEN);
            logEvent(String.format("Issue status set to %s", Status.OPEN));
        } else {
            logEvent(String.format("Issue status already %s", Status.OPEN));
        }
    }

    @Override
    public void advanceStatus() {
        if (!getStatus().equals(Status.VERIFIED)) {
            setStatus(Status.VERIFIED);
            logEvent(String.format("Issue status set to %s", Status.VERIFIED));
        } else {
            logEvent(String.format("Issue status already %s", Status.VERIFIED));
        }
    }
}
