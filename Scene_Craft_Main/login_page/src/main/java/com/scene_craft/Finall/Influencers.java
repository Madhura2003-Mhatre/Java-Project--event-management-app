package com.scene_craft.Finall;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class Influencers {

    private GridPane gridPane;
    private int influencerCount = 0; // Counter for the number of influencers
    private final int maxRows = 2; // Fixed number of rows to 2
    private File selectedImageFile; // Store the selected image file
    private DataService dataService;
    public Scene formStage;
    public Stage primaryStage;
    private VBox mainLayout;
    private ScrollPane influencersScrollpane;
    public static BorderPane influencBorderPane = UserPage.mainLayout;

    public VBox cerateInfluencerVBox(){

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 0, 10, 0));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Add initial influencer detail boxes
        //addInitialInfluencers();
        addInfluencerBox("Sandip Maheshwari", "Public Speaker, YouTuber", "images/SandipMaheshwari.jpg");
        addInfluencerBox("Dr. Vivek Bindra", "Author, Trainer", "images/VivekBindra.jpg");
        addInfluencerBox("Jaspreet Bindra", "Digital Transformation & Technology Expert", "images/jaspreetbindra.jpg");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");
        // addInfluencerBox("Shivam Khule", "Java Developer", "icons/backbutton.png");

        // Create a headline and back button
        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");

        ImageView backIcon = new ImageView(new Image("/icons/backbutton.png")); // Replace with your icon path
        backIcon.setFitWidth(50);
        backIcon.setFitHeight(50);

        // Button for back page
        Button backButton = new Button();
        backButton.setGraphic(backIcon);
        backButton.setPrefWidth(30);
        backButton.setStyle("-fx-background-color:transparent; -fx-cursor:hand");
        // backButton.setOnAction(e -> {
        //     otherpage otherPage = new otherpage();
        //     try {
        //         otherPage.start(primaryStage);
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });

        // Button to open the form for adding a new influencer
        Button addButton = new Button("Add Influencer");
        addButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-pref-width: 150px; " +
                        "-fx-pref-height: 30px; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: transparent;" +
                        "-fx-font-weight: bold;");
        addButton.setMaxSize(400, 200);
        addButton.setOnAction(e -> openAddInfluencerForm(primaryStage));

        // Create a Region to push the Add Button to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Create an HBox for the input fields and button
        HBox inputBox = new HBox(10, backButton, categoryLabel, spacer, addButton);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setFillHeight(true);
        inputBox.setPadding(new Insets(20, 20, 20, 0));

        // VBox for the GridPane and the admin input
        VBox V1 = new VBox(gridPane);

        // Create a ScrollPane and add the VBox to it
        ScrollPane scrollPane = new ScrollPane(V1);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPadding(new Insets(0, 10, 0, 10));

        // Create the main layout
        mainLayout = new VBox(10, inputBox, scrollPane);
        mainLayout.setPadding(new Insets(30, 60, 0, 60));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #f0f0f0;" + "-fx-font-family: 'Arial';" + "-fx-font-size: 14px;");

        return mainLayout;
    }

    // Rest of your code
    private void openAddInfluencerForm(Stage owner) {
        Stage formStage = new Stage();
        formStage.initModality(Modality.WINDOW_MODAL);
        formStage.initOwner(owner);
        formStage.setTitle("Add Influencer");

        // Create input fields for the form
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setStyle("-fx-pref-width: 200px; -fx-font-size: 16px; -fx-padding: 5px;");
        TextField bioInput = new TextField();
        bioInput.setPromptText("Bio");
        bioInput.setStyle("-fx-pref-width: 200px; -fx-font-size: 16px; -fx-padding: 5px; -fx-weight:bold;");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        categoryField.setStyle("-fx-pref-width: 200px; -fx-font-size: 16px; -fx-padding: 5px;");
        TextField experienceTextField = new TextField();
        experienceTextField.setPromptText("Experience");
        experienceTextField.setStyle("-fx-pref-width: 200px; -fx-font-size: 16px; -fx-padding: 5px;");
        TextField previousField = new TextField();
        previousField.setPromptText("Previous Show");
        previousField.setStyle("-fx-pref-width: 900px; -fx-font-size: 16px; -fx-padding: 5px;");
        TextField sociaTextField = new TextField();
        sociaTextField.setPromptText("Social Media");
        sociaTextField.setStyle("-fx-pref-width: 200px; -fx-font-size: 16px; -fx-padding: 5px;");
        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-pref-width: 120px; " +
                        "-fx-pref-height: 30px; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: transparent;");

        // Create an ImageView to display the selected image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        // Create a circular clip
        Circle clip = new Circle(500);
        imageView.setClip(clip);

        // Set default profile image

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        // Add action to the upload button to open the FileChooser and display the
        // selected image
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
                        "-fx-font-size: 16px; " +
                        "-fx-pref-width: 120px; " +
                        "-fx-pref-height: 30px; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: transparent;");
        submitButton.setOnAction(e -> {
            // handleSignup(nameField.getText(), emailField.getText(),
            // newPasswordField.getText());
            String name = nameInput.getText();
            String bio = bioInput.getText();
            String category = categoryField.getText();
            String experience = experienceTextField.getText();
            String previous = previousField.getText();
            String social = sociaTextField.getText();
            handleSignup(name, bio, category, experience, previous, social);

            if (selectedImageFile != null && !name.isEmpty() && !bio.isEmpty() && !category.isEmpty()) {
                String imagePath = selectedImageFile.toURI().toString();
                addInfluencerBox(name, category, imagePath); // Change from bio to category
                formStage.close();
            }
        });

        // Create a GridPane for the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPrefHeight(550);
        gridPane.setAlignment(Pos.TOP_CENTER);

        // Add labels and text fields to the GridPane
        Label lfromname = new Label("Name:");
        lfromname.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfromname, 0, 0);
        gridPane.add(nameInput, 1, 0);
        Label lfrombio = new Label("Bio");
        lfrombio.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfrombio, 0, 1);
        gridPane.add(bioInput, 1, 1);
        Label lfromcateg = new Label("Category");
        lfromcateg.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfromcateg, 0, 2);
        gridPane.add(categoryField, 1, 2);
        Label lfromexp = new Label("Experience");
        lfromexp.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfromexp, 0, 3);
        gridPane.add(experienceTextField, 1, 3);
        Label lfromepre = new Label("Previous Shows");
        lfromepre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfromepre, 0, 4);
        gridPane.add(previousField, 1, 4);
        Label lfromesocial = new Label("Social Media");
        lfromesocial.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(lfromesocial, 0, 5);
        gridPane.add(sociaTextField, 1, 5);

        // Add buttons to the GridPane
        gridPane.add(uploadButton, 0, 6);
        gridPane.add(submitButton, 1, 6);

        ScrollPane scrollPanev1 = new ScrollPane(gridPane);
        scrollPanev1.setFitToHeight(true);
        // scrollPanev1.setMaxHeight(1000); // Set maximum height to 1000 pixels
        // scrollPanev1.setPrefHeight(800); // Set preferred height to 800 pixels or any
        // other value you want
        scrollPanev1.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPanev1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Create a VBox for the form layout and add the ImageView and GridPane
        VBox formLayout = new VBox(10, imageView, scrollPanev1);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        Scene formScene = new Scene(formLayout, 1200, 900);
        formStage.setScene(formScene);
        formStage.show();
    }

    private void addInitialInfluencers() {
        addInfluencerBox("carryminati", "Gaming", "assets/images/images.jpeg");
        // addInfluencerBox("Dhoni", "Cricket", "assets/images/images.jpeg");
        // addInfluencerBox("xyz", "Music", "assets/images/images.jpeg");
        // addInfluencerBox("Influencer 4", "Acting", "assets/images/images.jpeg");
        // addInfluencerBox("carryminati2", "Gaming", "assets/images/images.jpeg");
        // addInfluencerBox("mayank", "Photography", "assets/images/images.jpeg");
        // addInfluencerBox("xyz", "Music", "assets/images/images.jpeg");
    }

    public void addInfluencerBox(String name, String category, String imagePath) {
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
            VBox vBox = createInfluencerBox(name, category, imagePath);
            int row = influencerCount % maxRows;
            int column = influencerCount / maxRows;
            gridPane.add(vBox, column, row);
            influencerCount++;
        }
    }

    InfluencersProfilePage influencersProfilePage = new InfluencersProfilePage();
    VBox influencersProfileVBox = influencersProfilePage.profilePage();

    private VBox createInfluencerBox(String name, String category, String imagePath) {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-border-width: 2px; " + "-fx-background-color: #d3ced6; " + "-fx-cursor: hand; -fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px;");
        vBox.setPrefSize(410, 410);

        // ProfilePage profilrPage = new ProfilePage();
        // Scene profilrVBox = profilrPage.getProfileScene();

        // Load and display the image
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        // Clip the image into a circle
        final Circle clip = new Circle(100, 100, 100);
        imageView.setClip(clip);

        // Add influencer name and category
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size:35");
        Label categoryLabel = new Label(category);
        categoryLabel.setWrapText(true);
        categoryLabel.setStyle("-fx-font-weight: bold; -fx-font-size:20");

        // Add components to the VBox
        vBox.getChildren().addAll(imageView, nameLabel, categoryLabel);

        vBox.setOnMouseClicked(event -> {
            // Handle click event
            System.out.println(name + " clicked!");
            influencBorderPane.setCenter(influencersProfileVBox);
        });

        return vBox;
    }

    private void handleSignup(String name, String bio, String category, String experience, String previous, String social) {
        DataService dataService;
        try {
            dataService = new DataService();
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("bio", bio);
            data.put("category", category);
            data.put("experience", experience);
            data.put("previous", previous);
            data.put("social", social);
            dataService.addData("NewInfluencers", name, data);
            // System.out.println("data going sucessfull");
            Alert submitAlert = new Alert(Alert.AlertType.INFORMATION);
            submitAlert.setTitle("Confirmation alert");
            submitAlert.setHeaderText(null);
            submitAlert.setContentText("Your had Registred Sucessfully.\nYou will be Notified Soon...");
            submitAlert.showAndWait();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
