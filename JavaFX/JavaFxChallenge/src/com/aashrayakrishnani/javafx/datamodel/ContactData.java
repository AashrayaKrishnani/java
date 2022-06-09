package com.aashrayakrishnani.javafx.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ContactData {

    private static ContactData instance = new ContactData();
    private static String filePath = "/home/kali/code/JAVA/JavaFX/JavaFxChallenge/src/com/aashrayakrishnani/javafx/Contacts.txt";

    // An ObservableList automatically updates its data-bind content in the UI as it gets modified :D
    private ObservableList<Contact> contactsList;

    public static ContactData getInstance(){
        return instance;
    }

    public ObservableList<Contact> getContactsList() {
        return contactsList;
    }

    public void addContact(Contact contact){
        this.contactsList.add(contact);
    }

    public void setContactsList(ObservableList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    private ContactData(){}

    public void loadContacts() throws IOException{

        contactsList = FXCollections.observableArrayList();
        Path path = Paths.get(filePath);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{

            while((input= br.readLine()) != null){
                String[] contactDetails = input.split("\t");

                String firstName = contactDetails[0];
                String lastName = contactDetails[1];
                String phNum = contactDetails[2];
                String notes = contactDetails[3];

                Contact contact = new Contact(firstName, lastName, phNum, notes);
                contactsList.add(contact);
            }

        } finally {
            if( br != null )
                br.close();
        }

    }

    public void storeContacts() throws IOException{

        Path path = Paths.get(filePath);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{

            Iterator<Contact> iterator = contactsList.iterator();
            while(iterator.hasNext()){
                Contact contact = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getPhNum(),
                        contact.getNotes()));
                bw.newLine();
            }

        } finally {
            if(bw != null)
                bw.close();
        }
    }

    public void deleteContact(Contact contact) {
        this.contactsList.remove(contact);
    }
}
