package com.tabledemo.Others;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tabledemo.DataService_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class demomain2 extends Application {

    private GridPane gridPane;
    private int influencerCount = 0; // Counter for the number of influencers
    private final int maxRows = 2;   // Fixed number of rows to 2
    private File selectedImageFile;  // Store the selected image file
    private DataService_2 dataService = new DataService_2();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Influencer Details");
        // Create a GridPane for the layout
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Add initial influencer detail boxes
        try {
            refreshDataAndDisplayInfluencers();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Create a headline and back button
        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");

        ImageView backIcon = new ImageView(new Image("assets/images/backbutton.png")); // Replace with your icon path
        backIcon.setFitWidth(50);
        backIcon.setFitHeight(50);

        // Button for back page
        Button backButton = new Button();
        backButton.setGraphic(backIcon);
        backButton.setPrefWidth(30);
        backButton.setStyle("-fx-background-color:transparent; -fx-cursor:hand");

        // Button to open the form for adding a new influencer
        Button addButton = new Button("Add Influencer");
        addButton.setOnAction(e -> openAddInfluencerForm(primaryStage));

        // Create an HBox for the input fields and button
        HBox inputBox = new HBox(10, backButton, categoryLabel, addButton);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setPadding(new Insets(0, 20, 20, 0));

        // VBox for the GridPane and the admin input
        VBox V1 = new VBox(gridPane);

        // Create a ScrollPane and add the VBox to it
        ScrollPane scrollPane = new ScrollPane(V1);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Create the main layout
        VBox mainLayout = new VBox(10, inputBox, scrollPane);
        mainLayout.setPadding(new Insets(100, 20, 20, 220));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle(
                "-fx-background-color: #f0f0f0;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 14px;"
        );

        // Create the scene and set the stage
        Scene scene = new Scene(mainLayout, 1919, 990); // Set the size explicitly
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openAddInfluencerForm(Stage owner) {
        Stage formStage = new Stage();
        formStage.initModality(Modality.WINDOW_MODAL);
        formStage.initOwner(owner);
        formStage.setTitle("Add Influencer");

        // Create input fields for the form
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        TextField bioInput = new TextField();
        bioInput.setPromptText("Bio");
        bioInput.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        categoryField.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        TextField experienceTextField = new TextField();
        experienceTextField.setPromptText("Experience");
        experienceTextField.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        TextField previousField = new TextField();
        previousField.setPromptText("Previous Show");
        previousField.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        TextField sociaTextField = new TextField();
        sociaTextField.setPromptText("Previous Show");
        sociaTextField.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-padding: 5px;");
        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-pref-width: 120px; " +
                "-fx-pref-height: 30px; " +
                "-fx-cursor: hand; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-color: transparent;"
        );

        // Create an ImageView to display the selected image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Add action to the upload button to open the FileChooser and display the selected image
        uploadButton.setOnAction(e -> {
            selectedImageFile = fileChooser.showOpenDialog(formStage);
            if (selectedImageFile != null) {
                imageView.setImage(new Image(selectedImageFile.toURI().toString()));
            }
        });

        Button submitButton = new Button("Submit");
        submitButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-pref-width: 120px; " +
                "-fx-pref-height: 30px; " +
                "-fx-cursor: hand; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px; " +
                "-fx-border-color: transparent;"
        );
        submitButton.setOnAction(e -> {
            String name = nameInput.getText();
            String bio = bioInput.getText();
            String category = categoryField.getText();
            String experience = experienceTextField.getText();
            String previous = previousField.getText();
            String social = sociaTextField.getText();

            if (selectedImageFile != null && !name.isEmpty() && !bio.isEmpty()) {
                addInfluencerToFirebase(name, bio, category, experience, previous, social, selectedImageFile.toURI().toString());
                formStage.close();
                try {
                    refreshDataAndDisplayInfluencers();
                } catch (ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Create a GridPane for the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add labels and text fields to the GridPane
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameInput, 1, 0);
        gridPane.add(new Label("Bio:"), 0, 1);
        gridPane.add(bioInput, 1, 1);
        gridPane.add(new Label("Category:"), 0, 2);
        gridPane.add(categoryField, 1, 2);
        gridPane.add(new Label("Experience:"), 0, 3);
        gridPane.add(experienceTextField, 1, 3);
        gridPane.add(new Label("Previous Show:"), 0, 4);
        gridPane.add( previousField, 1, 4);
        gridPane.add(new Label("Social Media"), 0, 5);
        gridPane.add( sociaTextField, 1, 5);

        // Add buttons to the GridPane
        gridPane.add(uploadButton, 0, 6);
        gridPane.add(submitButton, 1, 6);

        ScrollPane scrollPanev1 = new ScrollPane(gridPane);
        scrollPanev1.setFitToHeight(true);
        scrollPanev1.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPanev1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Create a VBox for the form layout and add the ImageView and GridPane
        VBox formLayout = new VBox(10, imageView, scrollPanev1);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        Scene formScene = new Scene(formLayout, 400, 450);
        formStage.setScene(formScene);
        formStage.show();
    }

    private void refreshDataAndDisplayInfluencers() throws ExecutionException, InterruptedException {
        // Clear existing data
        gridPane.getChildren().clear();
        influencerCount = 0;

        // Refresh data from Firebase
        List<Map<String, Object>> documents = dataService.getAllDocuments("users");

        // Add influencers to the gridPane
        for (Map<String, Object> document : documents) {
            String name = (String) document.get("name");
            String email = (String) document.get("email");
            String accountType = (String) document.get("accountUse");
            String password = (String) document.get("password");

            // Assuming you have a method to get the image path for an influencer
            String imagePath = "images/icons8-forgot-password-50.png"; // Replace with actual logic to get image path
            addInfluencerBox(name, email, imagePath);
        }
    }

    public void addInfluencerBox(String name, String bio, String imagePath) {
        // Check if the influencer already exists
        boolean influencerExists = false;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof VBox) {
                VBox vBox = (VBox) node;
                Label nameLabel = (Label) vBox.getChildren().get(1);
                if (nameLabel.getText().equals(name)) {
                    influencerExists = true;
                    break;
                }
            }
        }

        if (!influencerExists) {
            // Add new influencer box
            VBox vBox = createInfluencerBox(name, bio, imagePath);
            int row = influencerCount % maxRows;
            int column = influencerCount / maxRows;
            gridPane.add(vBox, column, row);
            influencerCount++;
        }
    }

    private VBox createInfluencerBox(String name, String bio, String imagePath) {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-background-color: #f0f0f0; -fx-cursor: hand;");
        vBox.setPrefSize(410, 410);

        // Load and display the image
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        // Clip the image into a circle
        final Circle clip = new Circle(50, 50, 50);
        imageView.setClip(clip);

        // Add influencer name and bio
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold;");
        Label bioLabel = new Label(bio);
        bioLabel.setWrapText(true);

        // Add components to the VBox
        vBox.getChildren().addAll(imageView, nameLabel, bioLabel);

        vBox.setOnMouseClicked(event -> {
            // Handle click event
            System.out.println(name + " clicked!");
        });

        return vBox;
    }

    private void addInfluencerToFirebase(String name, String bio, String category, String experience, String previous, String social, String imageUri) {
        // Implement Firebase logic here to add influencer
        // For now, assume it's not implemented
        System.out.println("Adding influencer to Firebase: " + name);
        // You can add Firebase logic here to store the influencer details
    }

    public static void main(String[] args) {
        launch(args);
    }
}
