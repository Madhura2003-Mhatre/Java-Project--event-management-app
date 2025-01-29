package com.tabledemo;

import javafx.application.Application;
import javafx.application.HostServices;
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
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InfluencersRegistration {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    HBox hb = new HBox();
    private VBox influencersTableVBox;
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service
    private static HostServices hostServices;

    public VBox influencersTableVBox() throws ExecutionException, InterruptedException {
        //Group root = new Group();

        Label label = new Label("New Influencers");
        //label.setFont(new Font("Arial", 30)); // Increased font size
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-padding: 0px; -fx-text-fill: #005A9E;");

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(800);
        //table.prefWidthProperty().bind(scene.widthProperty());
        //table.prefHeightProperty().bind(scene.heightProperty().subtract(100));

        TableColumn<Person, String> name = new TableColumn<>("Name");
        name.setMinWidth(150);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
        name.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> bio = new TableColumn<>("Bio");
        bio.setMinWidth(150);
        bio.setCellValueFactory(new PropertyValueFactory<>("bio"));
        bio.setCellFactory(TextFieldTableCell.forTableColumn());
        bio.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setBio(t.getNewValue()));
        bio.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> category = new TableColumn<>("Category");
        category.setMinWidth(150);
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        category.setCellFactory(TextFieldTableCell.forTableColumn());
        category.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCategory(t.getNewValue()));
        category.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> experience = new TableColumn<>("Experience");
        experience.setMinWidth(150);
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        experience.setCellFactory(TextFieldTableCell.forTableColumn());
        experience.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setExperience(t.getNewValue()));
        experience.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> previousShows = new TableColumn<>("Previous Shows");
        previousShows.setMinWidth(150);
        previousShows.setCellValueFactory(new PropertyValueFactory<>("previous"));
        previousShows.setCellFactory(TextFieldTableCell.forTableColumn());
        previousShows.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrevious(t.getNewValue()));
        previousShows.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> socialMedia = new TableColumn<>("Social Media");
        socialMedia.setMinWidth(150);
        socialMedia.setCellValueFactory(new PropertyValueFactory<>("socialMedia"));
        socialMedia.setCellFactory(TextFieldTableCell.forTableColumn());
        socialMedia.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSocialMedia(t.getNewValue()));
        socialMedia.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, Void> emailColumn = new TableColumn<>("Verification");
        emailColumn.setMinWidth(150);
        emailColumn.setCellFactory(col -> new TableCell<Person, Void>() {
            private final Button emailButton = new Button("Verify");

            {
                emailButton.setOnAction(e -> {
                    Person person = getTableView().getItems().get(getIndex());
                    String email = person.getSocialMedia(); // Assuming the social media field holds the email
                    if (email != null && !email.isEmpty()) {
                        getHostServices().showDocument("mailto:" + email);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(emailButton);
                }
            }
        });

        table.setItems(data);
        table.getColumns().addAll(name, bio, category, experience, previousShows, socialMedia, emailColumn);

        refreshData();

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
                System.out.println("New Registred Data Fetching");
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        refreshButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: HAND");

        HBox.setMargin(refreshButton, new Insets(0, 0, 0, 1450));

        hb.getChildren().addAll(label, refreshButton);
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setStyle("-fx-background-color: #f1f1f1;");

        influencersTableVBox = new VBox();
        influencersTableVBox.setSpacing(5);
        influencersTableVBox.setPadding(new Insets(10, 10, 10, 10));
        //vbox.prefWidthProperty().bind(scene.widthProperty());
        //vbox.prefHeightProperty().bind(scene.heightProperty());
        influencersTableVBox.getChildren().addAll(hb, table);

        //root.getChildren().add(vbox);

        return influencersTableVBox;
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("NewInfluencers"); // Replace "users" with your collection name

        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String bio = (String) document.get("bio");
            String category = (String) document.get("category");
            String experience = (String) document.get("experience");
            String previous = (String) document.get("previous");
            String socialMedia = (String) document.get("social");

            data.add(new Person(name, bio, category, experience, previous, socialMedia));
        }
    }

    public static class Person {

        private SimpleStringProperty name;
        private SimpleStringProperty bio;
        private SimpleStringProperty category;
        private SimpleStringProperty experience;
        private SimpleStringProperty previous;
        private SimpleStringProperty socialMedia;

        private Person(String Name, String Bio, String Category, String Experience, String Previous, String socialMedia) {
            this.name = new SimpleStringProperty(Name);
            this.bio = new SimpleStringProperty(Bio);
            this.category = new SimpleStringProperty(Category);
            this.experience = new SimpleStringProperty(Experience);
            this.previous = new SimpleStringProperty(Previous);
            this.socialMedia = new SimpleStringProperty(socialMedia);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String Name) {
            name.set(Name);
        }

        public String getBio() {
            return bio.get();
        }

        public void setBio(String Bio) {
            bio.set(Bio);
        }

        public String getCategory() {
            return category.get();
        }

        public void setCategory(String Category) {
            category.set(Category);
        }

        public String getExperience() {
            return experience.get();
        }

        public void setExperience(String Experience) {
            experience.set(Experience);
        }

        public String getPrevious() {
            return previous.get();
        }

        public void setPrevious(String Previous) {
            previous.set(Previous);
        }

        public String getSocialMedia() {
            return socialMedia.get();
        }

        public void setSocialMedia(String socialMedia) {
            this.socialMedia.set(socialMedia);
        }
    }

    public static void setHostServices(HostServices hostServices) {
        InfluencersRegistration.hostServices = hostServices;
    }

    public static HostServices getHostServices() {
        return hostServices;
    }
}
