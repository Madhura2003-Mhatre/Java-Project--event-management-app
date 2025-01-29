package com.tabledemo;

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

public class AppUsersTableView {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private HBox hb = new HBox();
    private VBox userTableVBox;
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service

    public VBox createUserTable() throws ExecutionException, InterruptedException {
        Label label = new Label("App Users");
        //label.setFont(new Font("Arial", 30)); // Increased font size
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-padding: 0px; -fx-text-fill: #005A9E;");

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(800); // Set preferred height to 400

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
        nameCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue()));
        emailCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> accountTypeCol = new TableColumn<>("Account Type");
        accountTypeCol.setMinWidth(150);
        accountTypeCol.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accountTypeCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAccountType(t.getNewValue()));
        accountTypeCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setMinWidth(150);
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPassword(t.getNewValue()));
        passwordCol.setStyle("-fx-font-size: 20px;");

        table.setItems(data);
        table.getColumns().addAll(nameCol, emailCol, accountTypeCol, passwordCol);

        refreshData();

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
                System.out.println("Users Data Fetching");
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        refreshButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: HAND");

        HBox.setMargin(refreshButton, new Insets(0, 0, 0, 1550)); // Add margin to the right of the refresh button

        hb.getChildren().addAll(label, refreshButton);
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setStyle("-fx-background-color: #f1f1f1;"); // Optional: Add background color

        userTableVBox = new VBox();
        userTableVBox.setSpacing(5);
        userTableVBox.setPadding(new Insets(10, 10, 10, 10));
        userTableVBox.getChildren().addAll(hb, table);

        return userTableVBox;
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("users"); // Replace "users" with your collection name

        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String email = (String) document.get("email");
            String accountType = (String) document.get("accountUse");
            String password = (String) document.get("password");

            data.add(new Person(name, email, accountType, password));
        }
    }

    public static class Person {

        private SimpleStringProperty name;
        private SimpleStringProperty email;
        private SimpleStringProperty accountType;
        private SimpleStringProperty password;

        private Person(String name, String email, String accountType, String password) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.accountType = new SimpleStringProperty(accountType);
            this.password = new SimpleStringProperty(password);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String Name) {
            name.set(Name);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String Email) {
            email.set(Email);
        }

        public String getAccountType() {
            return accountType.get();
        }

        public void setAccountType(String AccountType) {
            accountType.set(AccountType);
        }

        public String getPassword() {
            return password.get();
        }

        public void setPassword(String Password) {
            password.set(Password);
        }
    }
}
