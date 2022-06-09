package com.aashrayakrishnani.javafx;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Controller {

    @FXML
    private GridPane mainGridPane;
    @FXML
    private Button fileChooseButton;
    @FXML
    private Label filePathLabel;
    @FXML
    private Button fileSaveButton;
    @FXML
    private Label fileExtensionLabel;
    @FXML
    private TextField urlTextField;
    @FXML
    private WebView webView;
    @FXML
    private Label linkLabel;

    private File chosenFile;
    private File savedFile;

    @FXML
    public void initialize(){
        this.fileChooseButton.setStyle("-fx-background-color: goldenrod; -fx-text-fill: black;");
        this.fileChooseButton.setEffect(new Bloom());
        this.fileSaveButton.setStyle("-fx-background-color: goldenrod; -fx-text-fill: white;");
        this.fileSaveButton.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnterPathLabel(){

        this.filePathLabel.setScaleX(1.1);
        this.filePathLabel.setScaleY(1.1);

    }

    @FXML
    public void handleMouseEnterExtLabel(){

        this.fileExtensionLabel.setScaleX(1.1);
        this.fileExtensionLabel.setScaleY(1.1);

    }

    @FXML
    public void handleMouseExitPathLabel(){

        this.filePathLabel.setScaleX(1);
        this.filePathLabel.setScaleY(1);

    }

    @FXML
    public void handleMouseExitExtLabel(){

        this.fileExtensionLabel.setScaleX(1);
        this.fileExtensionLabel.setScaleY(1);

    }

    @FXML
    public void handleChooseFileButtonPress(){

        FileChooser chooser = new FileChooser();  // Alternatively 'DirectoryChooser' can also be used :)

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("Images", "*.img", "*.jpeg", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*" ));

        chosenFile = chooser.showOpenDialog(this.mainGridPane.getScene().getWindow()) ;

        try {
            this.filePathLabel.setText(chosenFile.getPath());
        } catch (NullPointerException exception){
            this.filePathLabel.setText("Nothing Selected :)");
        }

    }

    @FXML
    public void handleSaveFileButtonPress(){

        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("Images", "*.img", "*.jpeg", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*" ));

        savedFile = chooser.showSaveDialog(this.mainGridPane.getScene().getWindow()) ;

        try {
            this.fileExtensionLabel.setText(savedFile.getPath());
        } catch (NullPointerException exception){
            this.fileExtensionLabel.setText("Nothing Saved :)");
        }

    }

    @FXML
    public void handleLinkClick(){
        System.out.println("Is URI Opening supported? : " +
                Desktop.getDesktop().isSupported(Desktop.Action.APP_OPEN_URI)
                + "\nCheck @handleLinkClick() for more info. :)");

        // The Below code Would work for Desktops/OS that support APP_OPEN_URI :)

//        try {
//            Desktop.getDesktop().browse(
//                    new URI("https://www.typingclub.com") );
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        linkLabel.setText("Check Console Log :)");

        Runnable task = new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(linkLabel.getText().isBlank() == false)
                            linkLabel.setText("");
                    }
                });
            }
        };

        new Thread(task).start();

    }

    @FXML
    public void clearTextField(){
        this.urlTextField.clear();
    }

    @FXML
    public void openWebView() throws IOException {

        if( this.urlTextField.getText().isBlank() ){
            this.urlTextField.setText("Enter Website URL.");
        }
        else{
            this.webView.getEngine().load( this.urlTextField.getText());
        }

    }

}
