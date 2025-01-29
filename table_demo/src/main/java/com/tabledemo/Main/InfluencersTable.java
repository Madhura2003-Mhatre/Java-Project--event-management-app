package com.tabledemo.Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InfluencersTable {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    HBox hb = new HBox();
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service

    public VBox InfluencersTable() {

        Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setFirstName(t.getNewValue()));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastName(t.getNewValue()));

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue()));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            data.add(new Person(addFirstName.getText(), addLastName.getText(), addEmail.getText()));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton, refreshButton);
        hb.setSpacing(3);

        VBox influencersVBox = new VBox();
        influencersVBox.setSpacing(5);
        influencersVBox.setPadding(new Insets(10, 0, 0, 10));
        influencersVBox.getChildren().addAll(label, table, hb);


        return influencersVBox;
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("users"); // Replace "users" with your collection name

        for (Map<String, Object> document : documents) {
            String firstName = (String) document.get("name");
            String lastName = (String) document.get("password");
            String email = (String) document.get("email");

            data.add(new Person(firstName, lastName, email));
        }
    }

    public static class Person {

        private SimpleStringProperty firstName;
        private SimpleStringProperty lastName;
        private SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}
