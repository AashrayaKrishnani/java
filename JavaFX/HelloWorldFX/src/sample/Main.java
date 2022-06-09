package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Adding Colored Greeting using Commands Rather than the FXML file :)

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        root.setHgap(10);
//        root.setVgap(10);
//
//        Label greeting = new Label("Welcome to JavaFx!!! :D");
//        greeting.setTextFill(Color.DARKGOLDENROD);
//        greeting.setFont(Font.font("Impact", FontWeight.THIN, 57));
//
//        root.getChildren().add(greeting);

        primaryStage.setTitle("Hello JavaFX! ;D");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
