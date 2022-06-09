package com.aashrayakrishnani.javafx.datamodel;

import javafx.beans.value.ObservableValueBase;

public class Contact {

    private String firstName;
    private String lastName;
    private String phNum;
    private String notes;


    public Contact(String firstName, String lastName, String phNum, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phNum = phNum;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }


    public String getNotes() {
        return notes;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
