package com.aashrayakrishnani.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

// The UI thread is the process that runs in the background and listens to user input.
// It is what finds corresponding event handlers for those inputs and then makes them work!
//
// However, when the UI thread is making one event handler work, it cannot listen to any user input
// as it's already busy executing one event handler, this leads to the application 'freezing' :)
//
// Example below in void onButtonClick() :)


public class Controller {

    @FXML
    private TextField nameField;    // @FXML is used to link/associate/connect code files with FXML files :D
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox checkBox1;


    @FXML
    private void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }


    // Example below in void onButtonClick() for :- UI thread being busy --> application 'freezing' :)

    @FXML                           // @FXML is used to link/associate/connect code files with FXML files :D
    private void onButtonClick(ActionEvent event){

        // We'll write a handler that keeps the buttons disabled till there is input in the nameField,
        // So no worries about the below line being executed with an empty name :D

        String message ;

        message = ", " + this.nameField.getText().trim() + "! ";

        if(event.getSource().equals(helloButton))
            message = "Hello" + message + ":D";
        else if(event.getSource().equals(byeButton))
            message = "Bye" + message + ":)";

        System.out.println(message);

        // After message is print, let's put UI thread to sleep for 10 sec.
        // The UI will freeze, try it out to see ;p
        // Moral - Always program your eventHandlers to return control supeeeer quick or work in bg lolXDXD
        try {
            Thread.sleep(10000);
        } catch (InterruptedException exception){
            // We don't care to do anything XDXD
            // Similar to using 'pass' in python ;)
        }

        if( checkBox1.isSelected() ){
            nameField.clear();

            // To disable the helloButton again as the nameField is emptied by us and not by a keyPress by the user. :)
            this.handleKeyReleased();
        }

    }

    @FXML                           // @FXML is used to link/associate/connect code files with FXML files :D
    private void handleKeyReleased(){

        String text = this.nameField.getText();

        boolean disableButton = text.isBlank();

        this.helloButton.setDisable(disableButton);
        this.byeButton.setDisable(disableButton);

    }

}
