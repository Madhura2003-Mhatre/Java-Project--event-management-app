package com.scene_craft.Finall;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.List;

public class Profilepage {

    private VBox profilePageVbox;
    static String userEmail;
    private DataService dataService;
    public static String userPageName = UserPage.loginUserName;
    List<String> userDetails;

    public VBox profileVbox(Runnable logoutHandler) {

        dataService = new DataService();

        Button addImageButton = new Button("Edit Image");
        addImageButton.setOnMouseEntered(e -> {
            addImageButton.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
        });
        addImageButton.setOnMouseExited(e -> {
            addImageButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        });
        Region spacer = new Region();
        spacer.setMinHeight(60);

        ImageView profileImage = new ImageView();
        profileImage.setFitHeight(250);  // Adjust as needed
        profileImage.setFitWidth(250);   // Adjust as needed
        profileImage.setPreserveRatio(true);

        // Create a circular clip
        Circle clip = new Circle(500);
        profileImage.setClip(clip);

        // Set default profile image
        String defaultImagePath = "/images/profileIcon.png";
        Image defaultImage = new Image(Profilepage.class.getResourceAsStream(defaultImagePath));
        profileImage.setImage(defaultImage);

        // Add an event handler to the addImageButton
        addImageButton.setOnAction(e -> {
            // Get the current window from the button
            Window window = addImageButton.getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(window);
            if (selectedFile != null) {
                Image selectedImage = new Image(selectedFile.toURI().toString());
                profileImage.setImage(selectedImage);
                // Update clip to match new image size
                //clip.setRadius(100); // Adjust the radius to match the new image size
            }
        });

        VBox firstVbox = new VBox(10, profileImage, addImageButton);
        firstVbox.setAlignment(Pos.CENTER);
        firstVbox.setPadding(new Insets(-80, 0, 5, 0));

        Label userNameText = new Label("Name:");
        Label accountTypeText = new Label("Account Type:");
        Label contactText = new Label("Contact Details");
        Label emailText = new Label("Email:");
        Label numberText = new Label("Contact Number:");
        String style1 = "-fx-font-size: 15; -fx-text-fill: black; -fx-font-weight: bold;";
        userNameText.setStyle(style1);
        accountTypeText.setStyle(style1);
        emailText.setStyle(style1);
        contactText.setStyle(style1);
        numberText.setStyle(style1);

        TextField numberTextField = new TextField();
        numberTextField.setPromptText("Enter new number");
        numberTextField.setVisible(false);

        String style2 = "-fx-font-size: 25; -fx-text-fill: #005A9E; -fx-font-weight: bold;";
        Label number = new Label("123-456-7890");
        Label name = new Label();
        Label accountType = new Label();
        Label email = new Label();

        Button editNumberButton = new Button("Edit Number");
        editNumberButton.setOnMouseEntered(e -> {
            editNumberButton.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
        });
        editNumberButton.setOnMouseExited(e -> {
            editNumberButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        });

        // Only allow digits in the text field
        numberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Add an event handler to the editNumberButton
        editNumberButton.setOnAction(e -> {
            numberTextField.setVisible(true);
            numberTextField.setText(number.getText());
        });

        // Add an event handler to the text field to update the label and hide the text field on Enter
        numberTextField.setOnAction(e -> {
            number.setText(numberTextField.getText());
            numberTextField.setVisible(false);
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run();
            }
        });
        logoutButton.setOnMouseEntered(e -> {
            logoutButton.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
        });
        logoutButton.setOnMouseExited(e -> {
            logoutButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        });

        try {
            //System.out.println(userPageName);
            userDetails = dataService.getDataForUser(userPageName);
            name = new Label(userDetails.get(0));
            accountType = new Label(userDetails.get(1));
            email = new Label(userDetails.get(2));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        number.setStyle(style2);
        name.setStyle(style2);
        accountType.setStyle(style2);
        email.setStyle(style2);

        VBox secondVbox = new VBox(10, userNameText, name, accountTypeText, accountType, contactText, emailText, email, editNumberButton, numberText, number, numberTextField, logoutButton);
        secondVbox.setAlignment(Pos.CENTER);

        profilePageVbox = new VBox(20, firstVbox, secondVbox);
        profilePageVbox.setPrefSize(300, 900);
        profilePageVbox.setAlignment(Pos.CENTER);

        return profilePageVbox;
    }
}
