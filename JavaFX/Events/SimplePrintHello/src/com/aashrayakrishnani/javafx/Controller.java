package com.aashrayakrishnani.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;    // @FXML is used to link/associate/connect code files with FXML files :D
    @FXML
    private Button helloButton;

    @FXML
    private void initialize(){
        helloButton.setDisable(true);
    }

    @FXML                           // @FXML is used to link/associate/connect code files with FXML files :D
    private void printHello(){

        // We'll write a handler that keeps the buttons disabled till there is input in the nameField,
        // So no worries about the below line being executed with an empty name :D

        System.out.println("Hello, " + this.nameField.getText().trim() + "! :D");

    }

    @FXML
    private void handleKeyReleased(){

        String text = this.nameField.getText();

        boolean disableButton = text.isBlank();

        this.helloButton.setDisable(disableButton);

    }

}
