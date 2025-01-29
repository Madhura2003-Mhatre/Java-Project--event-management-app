package com.tabledemo.Main;
// package com.tabledemo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AppUsersTableView extends Application {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    HBox hb = new HBox();
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("App Users");
        stage.setWidth(600);
        stage.setHeight(600);

        Label label = new Label("App Users");
        label.setFont(new Font("Arial", 30)); // Increased font size

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.prefWidthProperty().bind(scene.widthProperty());
        table.prefHeightProperty().bind(scene.heightProperty().subtract(100));

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

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        refreshButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: HAND");

        hb.getChildren().addAll(refreshButton);
        hb.setSpacing(3);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.prefWidthProperty().bind(scene.widthProperty());
        vbox.prefHeightProperty().bind(scene.heightProperty());
        vbox.getChildren().addAll(label, table, hb);

        root.getChildren().add(vbox);

        stage.setScene(scene);
        stage.show();
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
