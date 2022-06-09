package com.aashrayakrishnani.javafx;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.aashrayakrishnani.javafx.datamodel.ToDoData;
import com.aashrayakrishnani.javafx.datamodel.ToDoItem;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class Controller {

    private List<ToDoItem> toDoItems;
    @FXML
    private ListView<ToDoItem> todoListView;
    @FXML
    private TextArea textArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<ToDoItem> filteredList;

    public void initialize(){

        contextMenu = new ContextMenu();

        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ToDoItem toDoItem = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(toDoItem);
            }
        });

        contextMenu.getItems().add(deleteMenuItem);

        toDoItems = new ArrayList<>();

//        ToDoItem demoItem = new ToDoItem("Sample Task",
//                "Do this Do that, Remember this, Forget that. Bla Bla Bla ;p",
//                LocalDate.of(2021, Month.APRIL, 15));
//
//        toDoItems.add(demoItem);

        filteredList = new FilteredList<ToDoItem>(ToDoData.getInstance().getToDoItemList(),
                new Predicate<ToDoItem>() {
                    @Override
                    public boolean test(ToDoItem toDoItem) {
                        return true;
                    }
                });

        SortedList<ToDoItem> sortedList = new SortedList<ToDoItem>(filteredList,
                new Comparator<ToDoItem>() {
                    @Override
                    public int compare(ToDoItem o1, ToDoItem o2) {
                        return o2.getDeadLine().compareTo(o1.getDeadLine());
                    }
                });

        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//        ToDoData.getInstance().setToDoItemList(toDoItems);

        // Adding a listener to update the UI on every change that is made to the ListView,
        // a click, selection or anything :D
        // Better than the fxml ListView 'onMouseClick' option which only updates on a mouse click and
        // not on a change in selection in the ListView :D

        todoListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                handleClickListView();
            }
        });

        todoListView.getSelectionModel().selectFirst();
        todoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> toDoItemListView) {

                ListCell<ToDoItem> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(ToDoItem toDoItem, boolean empty) {
                        super.updateItem(toDoItem, empty);

                        if(empty){
                            setText(null);
                            setContextMenu(null);
                        }
                        else {
                            setText(toDoItem.getShortDescription());
                            if (toDoItem.getDeadLine().isEqual(LocalDate.now())){
                                setTextFill(Color.RED);
                                setFont(Font.font(getFont().getName(), FontWeight.SEMI_BOLD, getFont().getSize()));
                            }
                            else {
                                if(toDoItem.getDeadLine().isBefore(LocalDate.now())) {
                                    setTextFill(Color.DARKRED);
                                    setFont(Font.font(getFont().getName(), FontWeight.BOLD, getFont().getSize()));
                                }
                                else if (toDoItem.getDeadLine().isEqual(LocalDate.now().plusDays(1)))
                                    setTextFill(Color.BLUEVIOLET);

                            }
                            setContextMenu(contextMenu);
                        }


                    }
                };
                cell.setPrefHeight(37);

                return cell;
            }
        });
    }



    @FXML
    public void handleClickListView(){

        ToDoItem item = (ToDoItem) todoListView.getSelectionModel().getSelectedItem();

        // Below is the use of a DateTimeFormatter :D
        // Check out the link below to have more information about the DateTimeFormatter class in Java :D
        // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        if(item != null) {
            textArea.setText(item.getLongDescription());
            deadlineLabel.setText(dtf.format(item.getDeadLine()));
        }
        else{
            textArea.setText("Empty Selection :)");
            deadlineLabel.setText("Never lol XD");
        }
        /*
                System.out.println("Selected Item is --> " + item);


                StringBuilder sb = new StringBuilder(item.getLongDescription());
                sb.append("\n\n\n");
                sb.append("Due: ");
                sb.append(item.getDeadLine());


                textArea.setText(sb.toString());
        */

    }

    @FXML
    public void handleKeyPressed(KeyEvent event){
        ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();

        if(selectedItem != null){

            if(event.getCode().equals(KeyCode.DELETE))
                deleteItem(selectedItem);

        }
    }

    @FXML
    public void handleFilterButton(){

        boolean buttonPressed = false;

        ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();

        if(filterToggleButton.isSelected()){
            buttonPressed = true;
        }

        boolean finalButtonPressed = buttonPressed;
        filteredList.setPredicate(new Predicate<ToDoItem>() {
            @Override
            public boolean test(ToDoItem toDoItem) {
                return finalButtonPressed ? toDoItem.getDeadLine().isEqual(LocalDate.now()) : true ;
            }
        });

        if (buttonPressed){
            if(filteredList.contains(selectedItem))
                todoListView.getSelectionModel().select(selectedItem);
            else if(filteredList.isEmpty()){
                textArea.setText("No Tasks Due Today :D \nEnjoy bud ;p");
                deadlineLabel.setText("Theoretically Never lol XD");
            }
            else
                todoListView.getSelectionModel().selectFirst();
        }
        else {
            if(selectedItem == null)
                todoListView.getSelectionModel().selectFirst();
            else
                todoListView.getSelectionModel().select(selectedItem);
        }

    }

    @FXML
    private void showNewItemDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New ToDoList Task :)");
        dialog.setHeaderText("Create A New TodoList Task by Entering Below Values :D");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try {

            dialog.getDialogPane().setPrefSize(750,450);
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException exception){
            System.out.println("Couldn't load DialogPane Window :p");
            exception.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        DialogController dialogController = fxmlLoader.getController();

        if(result.isPresent() && result.get()== ButtonType.OK){
            ToDoItem newItem = dialogController.processResults();

            // todoListView.getItems().setAll( ToDoData.getInstance().getToDoItemList() );
            // Above code isn't required as we have an ObservableList which automatically handles this for us :D

            todoListView.getSelectionModel().select(newItem);

            System.out.println("OK Pressed :D");
        }
        else if(result.isPresent() && result.get()== ButtonType.CLOSE)
        {
            System.out.println("CLOSE Pressed :D");
        }

    }

    private void deleteItem(ToDoItem toDoItem) {

        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);

        alertDialog.setTitle("You Sure Wanna Delete?");
        alertDialog.setHeaderText("Delete ---> " + toDoItem.getShortDescription());
        alertDialog.setContentText("Press 'OK' to Confirm, or 'Cancel' to back out! :)");

        Optional<ButtonType> result = alertDialog.showAndWait();  // shows Dialog and waits for input :D

        if(result.isPresent() && result.get()==ButtonType.OK)
            ToDoData.getInstance().deleteToDoItem(toDoItem);

    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }
}
