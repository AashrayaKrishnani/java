package com.aashrayakrishnani.javafx.datamodel;

import java.time.LocalDate;

public class ToDoItem {
    private String shortDescription = "-";
    private String longDescription = "-";
    private LocalDate deadLine;

    public ToDoItem(String shortDescription, String longDescription, LocalDate deadLine) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.deadLine = deadLine;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return this.shortDescription;
    }
}
