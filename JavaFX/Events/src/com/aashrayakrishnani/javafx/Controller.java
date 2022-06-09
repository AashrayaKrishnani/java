package com.aashrayakrishnani.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;    // @FXML is used to link/associate/connect code files with FXML files :D

    @FXML                           // @FXML is used to link/associate/connect code files with FXML files :D
    private void printHello(){

        if( this.nameField.getText().isBlank() ){
            System.out.println("Hello, Random Person! :D");
        }
        else{
            System.out.println("Hello, " + this.nameField.getText() + "! :D");
        }

    }


}
