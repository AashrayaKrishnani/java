package com.aashrayakrishnani.javafx.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ToDoData {

    private static ToDoData instance = new ToDoData();
    private static String filename = "ToDoListItems.txt";

    // An ObservableList automatically updates its data-bound content in the UI as it gets modified :D
    private ObservableList<ToDoItem> toDoItemList;
    private DateTimeFormatter dtf;

    public static ToDoData getInstance(){
        return instance;
    }

    public ObservableList<ToDoItem> getToDoItemList() {
        return toDoItemList;
    }

    public void addToDoItem(ToDoItem toDoItem){
        this.toDoItemList.add(toDoItem);
    }

//    public void setToDoItemList(List<ToDoItem> toDoItemList) {
//        this.toDoItemList = toDoItemList;
//    }

    private ToDoData(){
        dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void loadToDoItems() throws IOException{

        toDoItemList = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{

            while((input= br.readLine()) != null){
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String longDescription = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, dtf);
                ToDoItem toDoItem = new ToDoItem(shortDescription, longDescription, date);
                toDoItemList.add(toDoItem);
            }

        } finally {
            if( br != null )
                br.close();
        }

    }

    public void storeToDoItems() throws IOException{

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{

            Iterator<ToDoItem> iterator = toDoItemList.iterator();
            while(iterator.hasNext()){
                ToDoItem toDoItem = iterator.next();
                bw.write(String.format("%s\t%s\t%s",
                        toDoItem.getShortDescription(),
                        toDoItem.getLongDescription(),
                        toDoItem.getDeadLine().format(dtf)));
                bw.newLine();
            }

        } finally {
            if(bw != null)
                bw.close();
        }
    }

    public void deleteToDoItem(ToDoItem toDoItem) {
        this.toDoItemList.remove(toDoItem);
    }
}
