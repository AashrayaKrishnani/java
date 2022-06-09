package com.aashrayakrishnani.javafx;


import com.aashrayakrishnani.javafx.datamodel.Contact;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phNum;
    @FXML
    private TextArea notes;

    private String firstNameText = "One Word - Only Alphabets";
    private String lastNameText = "Same As Above :)";
    private String phNumText = "Only Digits Accepted - No Blank Spaces";
    private String notesText = "Full Liberty Here. Type All You Want lol XD";

    @FXML
    public void initialize(){
        this.firstName.setText(firstNameText);
        this.lastName.setText(lastNameText);
        this.phNum.setText(phNumText);
        this.notes.setText(notesText);
    }

    @FXML
    public void clearFirstNameField(){
        if (this.firstName.getText().equals(firstNameText))
            firstName.clear();
    }

    @FXML
    public void clearLastNameField(){
        if (this.lastName.getText().equals(lastNameText))
            lastName.clear();
    }

    @FXML
    public void clearPhNumField(){
        if (this.phNum.getText().equals(phNumText))
            phNum.clear();
    }

    @FXML
    public void clearNotesField(){
        if (this.notes.getText().equals(notesText))
            notes.clear();
    }




    public Contact processResults(){

        String firstName = this.firstName.getText().trim();

        if(firstName.isBlank()){
            firstName = "-";
        }
        else if(firstName.length() < 30 && !firstName.equals(firstNameText) ){
            char[] temp = firstName.toCharArray();
            for( char ch : temp){
                if(Character.isLetter(ch) == false) {
                    firstName="-";
                    break;
                }
            }
        }
        else{
            firstName = "-";
        }

        String lastName = this.lastName.getText().trim();

        if(lastName.isBlank()){
            lastName = "-";
        }
        else if(lastName.length() < 30 && !lastName.equals(lastNameText) ){
            char[] temp = lastName.toCharArray();
            for( char ch : temp){
                if(Character.isLetter(ch) == false) {
                    lastName="-";
                    break;
                }
            }
        }
        else {
            lastName = "-";
        }

        String phNum = this.phNum.getText().trim();

        if(phNum.isBlank()){
            phNum = "-";
        }
        else if(phNum.length() < 20 && !phNum.equals(phNumText) ){
            char[] temp = phNum.toCharArray();
            for( int i=0 ; i < temp.length ; i++ ){
                if(Character.isDigit(temp[i]) == false) {

                    if (i==0){
                        continue;
                    }

                    phNum="-";
                    break;
                }
            }
        }
        else {
            phNum = "-";
        }

        String notes = this.notes.getText().isBlank() ? "-" : this.notes.getText().trim();

        Contact newItem = new Contact(firstName, lastName, phNum, notes);

        return newItem;
    }

}
