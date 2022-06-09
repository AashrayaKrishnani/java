package com.aashrayakrishnani.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.aashrayakrishnani.javafx.datamodel.*;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Main.class.getResource("mainwindow.fxml"));

        primaryStage.setTitle("My Contact List");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try{
            ContactData.getInstance().storeContacts();
        } catch(IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try{
            ContactData.getInstance().loadContacts();
        } catch(IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
