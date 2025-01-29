/*package com.tabledemo;

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

public class InfluencersTableView {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private HBox hb = new HBox();
    private VBox influencersTableVBox;
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service

    public VBox influencersTableVBox() {
        Label label = new Label("Influencers Info");
        //label.setFont(new Font("Arial", 100));
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-padding: 0px; -fx-text-fill: #005A9E;");

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(800);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
        nameCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> bioCol = new TableColumn<>("Bio");
        bioCol.setMinWidth(150);
        bioCol.setCellValueFactory(new PropertyValueFactory<>("bio"));
        bioCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bioCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setBio(t.getNewValue()));
        bioCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setMinWidth(150);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCategory(t.getNewValue()));
        categoryCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> experienceCol = new TableColumn<>("Experience");
        experienceCol.setMinWidth(150);
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
        experienceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        experienceCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setExperience(t.getNewValue()));
        experienceCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> previousCol = new TableColumn<>("Previous Shows");
        previousCol.setMinWidth(150);
        previousCol.setCellValueFactory(new PropertyValueFactory<>("previous"));
        previousCol.setCellFactory(TextFieldTableCell.forTableColumn());
        previousCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrevious(t.getNewValue()));
        previousCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> socialMediaCol = new TableColumn<>("Social Media");
        socialMediaCol.setMinWidth(150);
        socialMediaCol.setCellValueFactory(new PropertyValueFactory<>("socialMedia"));
        socialMediaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        socialMediaCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSocialMedia(t.getNewValue()));
        socialMediaCol.setStyle("-fx-font-size: 20px;");

        table.setItems(data);
        table.getColumns().addAll(nameCol, bioCol, categoryCol, experienceCol, previousCol, socialMediaCol);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
                System.out.println("Influencers Data Fetching");
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
        influencersTableVBox.getChildren().addAll(hb, table);

        return influencersTableVBox;
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("NewInfluencers"); // Replace "NewInfluencers" with your collection name

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
}
*/

package com.tabledemo;

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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InfluencersTableView {

    private TableView<Person> table = new TableView<>();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private HBox hb = new HBox();
    private VBox influencersTableVBox;
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service
    private static HostServices hostServices;

    public VBox influencersTableVBox() throws ExecutionException, InterruptedException {
        Label label = new Label("Influencers Info");
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-padding: 0px; -fx-text-fill: #005A9E;");

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(800);

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
        nameCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> bioCol = new TableColumn<>("Bio");
        bioCol.setMinWidth(150);
        bioCol.setCellValueFactory(new PropertyValueFactory<>("bio"));
        bioCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bioCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setBio(t.getNewValue()));
        bioCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setMinWidth(150);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCategory(t.getNewValue()));
        categoryCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> experienceCol = new TableColumn<>("Experience");
        experienceCol.setMinWidth(150);
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
        experienceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        experienceCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setExperience(t.getNewValue()));
        experienceCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> previousCol = new TableColumn<>("Previous Shows");
        previousCol.setMinWidth(150);
        previousCol.setCellValueFactory(new PropertyValueFactory<>("previous"));
        previousCol.setCellFactory(TextFieldTableCell.forTableColumn());
        previousCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrevious(t.getNewValue()));
        previousCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Person, String> socialMediaCol = new TableColumn<>("Social Media");
        socialMediaCol.setMinWidth(150);
        socialMediaCol.setCellValueFactory(new PropertyValueFactory<>("socialMedia"));
        socialMediaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        socialMediaCol.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setSocialMedia(t.getNewValue()));
        socialMediaCol.setStyle("-fx-font-size: 20px;");

        // Add a new column with an on-click activity
        TableColumn<Person, Void> actionCol = new TableColumn<>("isAvailable");
        actionCol.setMinWidth(150);
        actionCol.setCellFactory(col -> new TableCell<Person, Void>() {
            private final Button emailButton = new Button("Available");

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
        table.getColumns().addAll(nameCol, bioCol, categoryCol, experienceCol, previousCol, socialMediaCol, actionCol);

        refreshData();

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        refreshButton
                .setStyle("-fx-background-color: #005A9E; -fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: HAND");

        HBox.setMargin(refreshButton, new Insets(0, 0, 0, 1450));

        hb.getChildren().addAll(label, refreshButton);
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setStyle("-fx-background-color: #f1f1f1;");

        influencersTableVBox = new VBox();
        influencersTableVBox.setSpacing(5);
        influencersTableVBox.setPadding(new Insets(10, 10, 10, 10));
        influencersTableVBox.getChildren().addAll(hb, table);

        return influencersTableVBox;
    }

    protected HostServices getHostServices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHostServices'");
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("Influencers"); // Replace "NewInfluencers"
                                                                                             // with your collection
                                                                                             // name

        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String bio = (String) document.get("bio");
            String category = (String) document.get("category");
            String experience = (String) document.get("experience");
            String previous = (String) document.get("previousShows");
            String socialMedia = (String) document.get("social");

            data.add(new Person(name, bio, category, experience, previous, socialMedia));
        }
    }

    private void addPage(Person person) {
        // Logic to add a page
        System.out.println("Adding page for: " + person.getName());
    }

    public static class Person {

        private SimpleStringProperty name;
        private SimpleStringProperty bio;
        private SimpleStringProperty category;
        private SimpleStringProperty experience;
        private SimpleStringProperty previous;
        private SimpleStringProperty socialMedia;

        private Person(String Name, String Bio, String Category, String Experience, String Previous,
                String socialMedia) {
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

        public static void setHostServices(HostServices hostServices) {
            InfluencersTableView.hostServices = hostServices;
        }  

        private HostServices getHostServices() {
            
            return hostServices;
        }
    }
}
