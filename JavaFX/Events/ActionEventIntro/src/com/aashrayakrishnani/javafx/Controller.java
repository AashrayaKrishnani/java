package com.aashrayakrishnani.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;    // @FXML is used to link/associate/connect code files with FXML files :D
    @FXML
    private TextField outputField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox checkBox1;


    // Action Events Allow us to get information about the specific Control that caused the given event
    // handler to be called, i.e., name of the Control, type, etc. :D
    //
    // It is useful since we can define just one function with the similar major functionality and then
    // use the ActionEvent to get information about the Control and make the necessary small changes as
    // required, instead of creating a whole new function for the Control altogether :D

    @FXML                           // @FXML is used to link/associate/connect code files with FXML files :D
    private void onButtonClick(ActionEvent event){

        String message = "-";

        if( this.nameField.getText().isBlank() ){
            message = ", Random Person! ";
        }
        else{
            message = ", " + this.nameField.getText().trim() + "! ";
        }

        if(event.getSource().equals(helloButton))
            message = "Hello" + message + ":D";
        else if(event.getSource().equals(byeButton))
            message = "Bye" + message + ":)";

        outputField.clear();
        outputField.setText(message);

        if( checkBox1.isSelected() ){
            nameField.clear();
        }
    }


}
