package com.aashrayakrishnani.javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// We know that using an eventHandler that has some processing to do and will take some time, is not nice
// as it freezes the app by keeping the UI thread occupied :p
//
// However we can workaround this problem by letting that specific eventHandler start its own thread
// instead of running on the UI thread, and then free the UI thread so that the app doesn't freeze ;D


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
    private Label ourLabel;

    private int debugCount = 0 ;

    @FXML
    private void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }


    // Creating our own thread so that the UI thread can be returned to its original state to keep the app running :D

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

        // We Use the 'Runnable' class to make a TASK that we want to run in the Background :D

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {

                    // If we try to access components of the JavaFX scene, i.e., buttons, labels, etc.
                    // through any other thread than the UI thread, as in this case!
                    //
                    // Then JavaFX pulls up and exception as any other process modifying the app could
                    // lead to vulnerability issues for the app :D

                    // So the way to do it is like below, so the code is actually forced to run on
                    // the UI thread rather than our other thread :D

                    boolean debug = Platform.isFxApplicationThread();
                    if( ++debugCount == 1)
                        System.out.println("[+] Inside 'task', output of Platform.isFxApplicationThread() = " +
                            debug);

                    String originalText = "Label To Tell Current State of our Thread in onButtonClick() :D" ;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            boolean debug = Platform.isFxApplicationThread();
                            if( ++debugCount == 2)
                                System.out.println("[+] Inside 'Platform.runLater', output of Platform.isFxApplicationThread() = " +
                                        debug);
                            ourLabel.setText("Our Thread went to sleep! XD");
                        }
                    });


                    Thread.sleep(10000);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ourLabel.setText("Our Thread woke up! ;p");
                        }
                    });

                    Thread.sleep(1000);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ourLabel.setText(originalText);
                        }
                    });


                } catch (InterruptedException exception){
                    // We don't care to do anything XDXD
                    // Similar to using 'pass' in python ;)
                }
            }
        };

        // Now We invoke/call the above declared 'task' into a new thread as so :-

        new Thread(task).start();

        // Now The UI will still be functional,
        // and our time taking Thread Processing task will also be carried out smoothly :D



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
