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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BookingsTableView {

    private TableView<Event> table = new TableView<>();
    private ObservableList<Event> data = FXCollections.observableArrayList();
    private HBox hb = new HBox();
    private VBox eventsTableVBox;
    private DataService_2 dataService = new DataService_2(); // Instantiate your data service

    public VBox eventsTableVBox() throws ExecutionException, InterruptedException {
        Label label = new Label("Bookings Info");
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-padding: 0px; -fx-text-fill: #005A9E;");

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(800);

        TableColumn<Event, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue()));
        nameCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setMinWidth(150);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setCategory(t.getNewValue()));
        categoryCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> aboutCol = new TableColumn<>("About");
        aboutCol.setMinWidth(150);
        aboutCol.setCellValueFactory(new PropertyValueFactory<>("about"));
        aboutCol.setCellFactory(TextFieldTableCell.forTableColumn());
        aboutCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setAbout(t.getNewValue()));
        aboutCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> locationCol = new TableColumn<>("Location");
        locationCol.setMinWidth(150);
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        locationCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setLocation(t.getNewValue()));
        locationCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(150);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDate(t.getNewValue()));
        dateCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> participantsCountCol = new TableColumn<>("Participants Count");
        participantsCountCol.setMinWidth(150);
        participantsCountCol.setCellValueFactory(new PropertyValueFactory<>("participantsCount"));
        participantsCountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        participantsCountCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setParticipantsCount(t.getNewValue()));
        participantsCountCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> durationCol = new TableColumn<>("Duration");
        durationCol.setMinWidth(150);
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        durationCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setDuration(t.getNewValue()));
        durationCol.setStyle("-fx-font-size: 20px;");

        TableColumn<Event, String> bookingForCol = new TableColumn<>("Booking For");
        bookingForCol.setMinWidth(150);
        bookingForCol.setCellValueFactory(new PropertyValueFactory<>("bookingFor"));
        bookingForCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bookingForCol.setOnEditCommit(t -> t.getTableView().getItems().get(t.getTablePosition().getRow()).setBookingFor(t.getNewValue()));
        bookingForCol.setStyle("-fx-font-size: 20px;");

        table.setItems(data);
        table.getColumns().addAll(nameCol, categoryCol, aboutCol, locationCol, dateCol, participantsCountCol, durationCol, bookingForCol);

        refreshData();

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            try {
                refreshData();
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        refreshButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: white; -fx-font-size: 16px; -fx-cursor: HAND");

        HBox.setMargin(refreshButton, new Insets(0, 0, 0, 1500));

        hb.getChildren().addAll(label, refreshButton);
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setStyle("-fx-background-color: #f1f1f1;");

        eventsTableVBox = new VBox();
        eventsTableVBox.setSpacing(5);
        eventsTableVBox.setPadding(new Insets(10, 10, 10, 10));
        eventsTableVBox.getChildren().addAll(hb, table);

        return eventsTableVBox;
    }

    private void refreshData() throws ExecutionException, InterruptedException {
        data.clear();
        List<Map<String, Object>> documents = dataService.getAllDocuments("SlotsBook"); // Replace "Events" with your collection name

        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String category = (String) document.get("category");
            String about = (String) document.get("aboutProgram");
            String location = (String) document.get("location");
            String date = (String) document.get("date");
            String participantsCount = (String) document.get("participentsCount");
            String duration = (String) document.get("duration");
            String bookingFor = (String) document.get("bookingFor");

            data.add(new Event(name, category, about, location, date, participantsCount, duration, bookingFor));
        }
    }

    public static class Event {

        private SimpleStringProperty name;
        private SimpleStringProperty category;
        private SimpleStringProperty about;
        private SimpleStringProperty location;
        private SimpleStringProperty date;
        private SimpleStringProperty participantsCount;
        private SimpleStringProperty duration;
        private SimpleStringProperty bookingFor;

        private Event(String Name, String Category, String About, String Location, String Date, String ParticipantsCount, String Duration, String BookingFor) {
            this.name = new SimpleStringProperty(Name);
            this.category = new SimpleStringProperty(Category);
            this.about = new SimpleStringProperty(About);
            this.location = new SimpleStringProperty(Location);
            this.date = new SimpleStringProperty(Date);
            this.participantsCount = new SimpleStringProperty(ParticipantsCount);
            this.duration = new SimpleStringProperty(Duration);
            this.bookingFor = new SimpleStringProperty(BookingFor);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String Name) {
            name.set(Name);
        }

        public String getCategory() {
            return category.get();
        }

        public void setCategory(String Category) {
            category.set(Category);
        }

        public String getAbout() {
            return about.get();
        }

        public void setAbout(String About) {
            about.set(About);
        }

        public String getLocation() {
            return location.get();
        }

        public void setLocation(String Location) {
            location.set(Location);
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String Date) {
            date.set(Date);
        }

        public String getParticipantsCount() {
            return participantsCount.get();
        }

        public void setParticipantsCount(String ParticipantsCount) {
            participantsCount.set(ParticipantsCount);
        }

        public String getDuration() {
            return duration.get();
        }

        public void setDuration(String Duration) {
            duration.set(Duration);
        }

        public String getBookingFor() {
            return bookingFor.get();
        }

        public void setBookingFor(String BookingFor) {
            bookingFor.set(BookingFor);
        }
    }
}

