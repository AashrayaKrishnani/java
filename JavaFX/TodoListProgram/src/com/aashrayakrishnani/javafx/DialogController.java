package com.aashrayakrishnani.javafx;

import com.aashrayakrishnani.javafx.datamodel.ToDoData;
import com.aashrayakrishnani.javafx.datamodel.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionTextField;
    @FXML
    private TextArea longDescriptionTextArea;
    @FXML
    private DatePicker deadlinePicker;

    public ToDoItem processResults(){

        String shortDescription = this.shortDescriptionTextField.getText().trim();
        String longDescription = this.longDescriptionTextArea.getText().trim();
        LocalDate deadline = this.deadlinePicker.getValue();

        ToDoItem newItem = new ToDoItem(shortDescription, longDescription, deadline);

        ToDoData.getInstance().addToDoItem(newItem);

        return newItem;
    }

}
