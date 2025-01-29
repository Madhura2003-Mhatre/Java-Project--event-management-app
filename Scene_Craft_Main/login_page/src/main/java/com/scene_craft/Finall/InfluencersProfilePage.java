package com.scene_craft.Finall;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InfluencersProfilePage extends Application {

        public Scene profilePageScene;
        public VBox mainContentBox;
        private static String influencerName;
        private Stage bookMeStage;

        @Override
        public void start(Stage primaryStage) {
                primaryStage.setTitle("Influencers Profile Page");
                profilePageScene = new Scene(profilePage(), 900, 550);
                primaryStage.setScene(profilePageScene);
                primaryStage.show();
        }

        public VBox profilePage() {

                // Create the back button with an icon
                Image backIcon = new Image("icons/backbutton.png");
                ImageView backIconView = new ImageView(backIcon);
                backIconView.setFitWidth(45);
                backIconView.setFitHeight(45);
                Button backButton = new Button("", backIconView);
                backButton.setStyle("-fx-background-color: transparent;");

                // Create the Personal Info label
                Label personalInfoLabel = new Label("Personal Info");
                personalInfoLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold;");

                // Create an HBox for the back button and label
                HBox topHBox = new HBox(15, backButton, personalInfoLabel);
                topHBox.setAlignment(Pos.CENTER_LEFT);
                topHBox.setPadding(new Insets(-10, 0, 0, 10));
                // topHBox.setStyle("-fx-background-color: lavender;");

                // Create an HBox to hold multiple profile cards
                HBox profilesHBox = new HBox(40);
                profilesHBox.setAlignment(Pos.CENTER);
                profilesHBox.setPadding(new Insets(100, 0, 0, 0));
                // profilesHBox.setStyle("-fx-background-color: lavender;");

                // Add multiple profile cards to the HBox
                profilesHBox.getChildren().addAll(
                                createProfileCard("images/SandipMaheshwari.jpg", "Sandip Maheshwari",
                                                "Public Speaker, YouTuber", "7 years", "Mumbai, India",
                                                "Real Life, Inspirational",
                                                "Sandeep Maheshwari is a name among millions who struggled, failed & surged ahead in pursuit of success. Rowing through ups and downs, it was time that taught him the true meaning of his life.",
                                                "Previous Shows : Videos on Youtube"),
                                createProfileCard("images/VivekBindra.jpg", "Dr. Vivek Bindra", "Author, Trainer",
                                                "15 years", "Gujrat, India",
                                                "Author, International Corporate Trainer",
                                                "CEO, and founder of Bada Business is one of the World's Greatest Influencers, an International Motivational Speaker, Leadership Consultant, Corporate trainer & Inspirational Business coach who has created 12 World Records.",
                                                "Previous shows: Business Coaching"),
                                createProfileCard("images/jaspreetbindra.jpg", "Jaspreet Bindra",
                                                "Digital Transformation & Technology Expert", "22 years",
                                                "Delli, India",
                                                "Technology Strategy, P/L Management, Marketing and Sales",
                                                "A professional shooter and businessman, Abhinav talks about mastering the art of precision through perseverance. A young idol to many, Abhinav's journey motivates people to work hard and persist to achieve their goals..",
                                                "Previous productions: Not Known"));

                // Create pagination buttons with icons
                Image prevIcon = new Image("icons/icons8-previous-50.png");
                Image nextIcon = new Image("icons/icons8-next-50.png");

                ImageView prevIconView = new ImageView(prevIcon);
                prevIconView.setFitWidth(45);
                prevIconView.setFitHeight(45);
                Button previousPage = new Button("", prevIconView);

                ImageView nextIconView = new ImageView(nextIcon);
                nextIconView.setFitWidth(45);
                nextIconView.setFitHeight(45);
                Button nextPage = new Button("", nextIconView);

                // Create pagination buttons
                Button firstPage = new Button("1");
                Button secondPage = new Button("2");
                Button thirdPage = new Button("3");
                Button fourthPage = new Button("4");
                Button fifthPage = new Button("5");

                // Style the pagination buttons
                String buttonStyle = "-fx-background-color: #d3ced6; -fx-text-fill: black; -fx-font-size: 30px; "
                                + "-fx-padding: 4px 25px; -fx-border-radius: 5px; -fx-background-radius: 5px; "
                                + "-fx-border-color: #005bb5; -fx-border-width: 1px; -fx-cursor: hand";

                previousPage.setStyle(buttonStyle);
                firstPage.setStyle(buttonStyle);
                secondPage.setStyle(buttonStyle);
                thirdPage.setStyle(buttonStyle);
                fourthPage.setStyle(buttonStyle);
                fifthPage.setStyle(buttonStyle);
                nextPage.setStyle(buttonStyle);

                // Create an HBox for the pagination buttons
                HBox pageNavigationHBox = new HBox(10, previousPage, firstPage, secondPage, thirdPage, fourthPage,
                                fifthPage, nextPage);
                pageNavigationHBox.setAlignment(Pos.CENTER);
                pageNavigationHBox.setPadding(new Insets(15, 0, 0, 0));

                // Main layout VBox
                mainContentBox = new VBox(30, topHBox, profilesHBox, pageNavigationHBox);
                mainContentBox.setPrefHeight(700);
                mainContentBox.setMaxHeight(700);

                return mainContentBox;
        }

        private VBox createProfileCard(String imagePath, String name, String category, String experience,
                        String location, String speakingTopics, String bio, String previousShows) {
                Image profileImage = new Image(imagePath);
                ImageView profileImageView = new ImageView(profileImage);
                profileImageView.setFitWidth(145);
                profileImageView.setFitHeight(145);
                profileImageView.setPreserveRatio(false);
                profileImageView.setStyle(
                                "-fx-border-color: #f0f0f0; -fx-background-color: #d3ced6; -fx-border-width: 5px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0.0, 0.0);");

                Label nameLabel = new Label(name);
                nameLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
                influencerName = name;

                Label categoryLabel = new Label(category);
                categoryLabel.setStyle("-fx-font-size:22px; -fx-font-weight: bold;");

                VBox profileInfoBox = new VBox(profileImageView, nameLabel, categoryLabel);
                profileInfoBox.setAlignment(Pos.CENTER);
                profileInfoBox.setPadding(new Insets(-95, 0, 8, 0));
                profileInfoBox.setSpacing(10);
                profileInfoBox.setStyle("-fx-background-color: #d3ced6");

                Label experienceLabel = new Label("Experience: " + experience);
                experienceLabel.setStyle("-fx-font-size: 19px;");

                Label locationLabel = new Label("Location: " + location);
                locationLabel.setStyle("-fx-font-size: 19px;");

                Label speakingTopicsLabel = new Label("Speaking Topics: " + speakingTopics);
                speakingTopicsLabel.setStyle("-fx-font-size: 19px;");

                // Use a Text node for the bio with wrapping
                Text bioLabel = new Text(bio);
                bioLabel.setStyle("-fx-font-size: 17px;");
                bioLabel.setWrappingWidth(380); // Set the desired wrapping width

                Label previousShowsLabel = new Label("Previous shows: " + previousShows);
                previousShowsLabel.setStyle("-fx-font-size: 19px;");

                VBox infoVBox = new VBox(5, experienceLabel, locationLabel, speakingTopicsLabel, bioLabel,
                                previousShowsLabel);
                infoVBox.setAlignment(Pos.TOP_LEFT);
                infoVBox.setPrefSize(400, 500);
                infoVBox.setStyle("-fx-background-color: #d3ced6");

                Button socialMediaButton = new Button("Social Media");
                socialMediaButton.setStyle(
                                "-fx-background-color: #4CAF45; -fx-text-fill: white; -fx-padding: 10 10 10 10; -fx-border-radius: 5px;");

                Button bookMeButton = new Button("Book Me");
                bookMeButton.setStyle(
                                "-fx-background-color: #4CAF45; -fx-text-fill: white; -fx-padding: 10 10 10 10; -fx-border-radius: 5px;");

                // Add action handler to bookMeButton
                bookMeButton.setOnAction(event -> {
                        System.out.println("Name: " + name); // Print the name associated with this profile card
                        VBox bookMeForm = BookMeForm();
                        bookMeStage = new Stage();
                        Scene bookMeScene = new Scene(bookMeForm, 800, 700);
                        bookMeStage.setScene(bookMeScene);
                        bookMeStage.setTitle("Book Me - " + name);
                        bookMeStage.show();
                });

                VBox buttonBox = new VBox(5, socialMediaButton, bookMeButton);
                buttonBox.setAlignment(Pos.BOTTOM_CENTER);
                buttonBox.setPadding(new Insets(0, 0, 0, 0));
                buttonBox.setStyle("-fx-background-color: #d3ced6");

                VBox profileVBox = new VBox(10, profileInfoBox, infoVBox, buttonBox);
                profileVBox.setMaxWidth(450);
                profileVBox.setMaxHeight(550);
                profileVBox.setMinWidth(450);
                profileVBox.setMinHeight(550);
                profileVBox.setPadding(new Insets(10));
                profileVBox.setStyle(
                                "-fx-background-color: #d3ced6; -fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0.0, 0.0);");

                return profileVBox;
        }

        public VBox BookMeForm() {
                // Create a GridPane
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(20));
                grid.setVgap(15);
                grid.setHgap(10);
                grid.setAlignment(Pos.CENTER);

                // Create Heading
                Label heading = new Label("Reserve Your Spot");
                heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                heading.setTextFill(Color.web("#007bff"));

                // Create Labels
                Label nameLabel = new Label("Company/Institute Name:");
                Label categoryLabel = new Label("Category of the Event:");
                Label mottoLabel = new Label("About Program:");
                Label locationLabel = new Label("Location:");
                Label dateLabel = new Label("Date:");
                Label durationLabel = new Label("Duration:");
                Label participantsLabel = new Label("Number of Participants:");

                // Style Labels
                String labelStyle = "-fx-font-size: 15; -fx-text-fill: black; -fx-font-weight: bold;";
                nameLabel.setStyle(labelStyle);
                categoryLabel.setStyle(labelStyle);
                mottoLabel.setStyle(labelStyle);
                locationLabel.setStyle(labelStyle);
                dateLabel.setStyle(labelStyle);
                durationLabel.setStyle(labelStyle);
                participantsLabel.setStyle(labelStyle);

                // Create TextFields
                TextField nameField = new TextField();
                TextField locationField = new TextField();
                TextField durationField = new TextField();
                TextField participantsField = new TextField();
                TextField mottoField = new TextField();

                // Style TextFields
                String fieldStyle = "-fx-padding: 10; -fx-border-width: 1; -fx-border-color: #87CEFA; -fx-border-radius: 5; -fx-background-radius: 5; -fx-background-color: #E6F2FF;";
                nameField.setStyle(fieldStyle);
                locationField.setStyle(fieldStyle);
                durationField.setStyle(fieldStyle);
                participantsField.setStyle(fieldStyle);
                mottoField.setStyle(fieldStyle);

                // Set fixed width for TextFields
                nameField.setPrefWidth(350);
                locationField.setPrefWidth(350);
                durationField.setPrefWidth(350);
                participantsField.setPrefWidth(350);
                mottoField.setPrefWidth(350);

                // Create ComboBox for Category
                ComboBox<String> categoryComboBox = new ComboBox<>();
                categoryComboBox.getItems().addAll("Training", "Event", "Workshop", "Seminar");
                categoryComboBox.setStyle(fieldStyle);
                categoryComboBox.setPrefWidth(350);

                // Create DatePicker for Date
                DatePicker datePicker = new DatePicker();
                datePicker.setStyle(fieldStyle);
                datePicker.setPrefWidth(350);

                // Create CheckBox for terms and conditions
                CheckBox termsCheckBox = new CheckBox("I accept terms & conditions");
                termsCheckBox.setStyle("-fx-font-size: 12; -fx-text-fill: black;");

                // Create Button
                Button submitButton = new Button("Book");
                submitButton.setStyle(
                                "-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 16; -fx-border-radius: 5; -fx-background-radius: 5;");
                submitButton.setPrefWidth(250);

                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                handleSubmit(nameField.getText(), locationField.getText(), durationField.getText(),
                                                participantsField.getText(), mottoField.getText(), categoryComboBox.getValue(),
                                                datePicker.getValue().toString(), influencerName);
                        }

                });

                // Add Labels and to the GridPane
                grid.add(nameLabel, 0, 1);
                grid.add(nameField, 1, 1);
                grid.add(categoryLabel, 0, 2);
                grid.add(categoryComboBox, 1, 2);
                grid.add(mottoLabel, 0, 3);
                grid.add(mottoField, 1, 3);
                grid.add(locationLabel, 0, 4);
                grid.add(locationField, 1, 4);
                grid.add(dateLabel, 0, 5);
                grid.add(datePicker, 1, 5);
                grid.add(durationLabel, 0, 6);
                grid.add(durationField, 1, 6);
                grid.add(participantsLabel, 0, 7);
                grid.add(participantsField, 1, 7);
                grid.add(termsCheckBox, 1, 8);

                // Create a VBox for the whole layout
                VBox vbox = new VBox(20, heading, grid, submitButton);
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(20));

                // Load and add background image
                try {
                        Image backgroundImage = new Image(
                                        getClass().getResource("/images/corporate2.jpg").toExternalForm());
                        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                        Background background = new Background(bgImage);

                        // Create a semi-transparent overlay
                        Pane overlay = new Pane();
                        overlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");

                        // Create a StackPane to hold the background and overlay
                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(new Region() {
                                {
                                        setBackground(background);
                                }
                        }, overlay, vbox);

                        return new VBox(stackPane);

                } catch (Exception e) {
                        System.out.println("Background image not found: " + e.getMessage());
                        return vbox;
                }
        }

        private void handleSubmit(String text, String text2, String text3, String text4, String text5, String selectedCategory, String selectedDate, String influencerName) {
                if (text != null && text2 != null && text3 != null && text4 != null && text5 != null && selectedCategory != null && selectedDate != null) {
                        DataService dataService;
                        try {
                                dataService = new DataService();
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", text);
                                data.put("location", text2);
                                data.put("duration", text3);
                                data.put("participentsCount", text4);
                                data.put("aboutProgram", text5);
                                data.put("category", selectedCategory);
                                data.put("date", selectedDate);
                                data.put("bookingFor", influencerName);
                                dataService.addData("SlotsBook", text, data);
                                text = null;
                                System.out.println("User registered successfully");
                                Alert submitAlert = new Alert(AlertType.INFORMATION);
                                submitAlert.setTitle("Booking");
                                submitAlert.setHeaderText("Slot is Booked");
                                submitAlert.setContentText(
                                                "Your Slot has been Taken !!!\nWill make Confirmation Soon on Registred Email");
                                submitAlert.showAndWait();
                                bookMeStage.close();


                                // loginPageController.showLoginScene();
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                } else{
                        /*Alert sihnUpError = new Alert(AlertType.INFORMATION);
                        sihnUpError.setTitle("Error");
                        sihnUpError.setHeaderText("Unsatisfied Inputs");
                        sihnUpError.setContentText("Please fill all the fields");
                        sihnUpError.showAndWait();*/
                }
        }

        public Scene getInfluencersProfileScene() {
                profilePageScene = new Scene(mainContentBox);
                return profilePageScene;
        }
}

/*
 * package com.scene_craft.Finall;
 * 
 * import javafx.application.Application;
 * import javafx.geometry.Insets;
 * import javafx.geometry.Pos;
 * import javafx.scene.Scene;
 * import javafx.scene.control.Button;
 * import javafx.scene.control.Label;
 * import javafx.scene.image.Image;
 * import javafx.scene.image.ImageView;
 * import javafx.scene.layout.HBox;
 * import javafx.scene.layout.VBox;
 * import javafx.scene.text.Text;
 * import javafx.stage.Stage;
 * 
 * public class InfluencersProfilePage {
 * 
 * public Scene profilePageScene;
 * public VBox mainContentBox;
 * 
 * public VBox profilePage() {
 * 
 * // Create the back button with an icon
 * Image backIcon = new
 * Image("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\backIcon.png"
 * );
 * ImageView backIconView = new ImageView(backIcon);
 * backIconView.setFitWidth(45);
 * backIconView.setFitHeight(45);
 * Button backButton = new Button("", backIconView);
 * backButton.setStyle("-fx-background-color: transparent;");
 * 
 * // Create the Personal Info label
 * Label personalInfoLabel = new Label("Personal Info");
 * personalInfoLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
 * 
 * // Create an HBox for the back button and label
 * HBox topHBox = new HBox(20, backButton, personalInfoLabel);
 * topHBox.setAlignment(Pos.CENTER_LEFT);
 * topHBox.setPadding(new Insets(10, 10, 0, 10));
 * topHBox.setStyle("-fx-background-color: lavender;");
 * 
 * // Create an HBox to hold multiple profile cards
 * HBox profilesHBox = new HBox(20);
 * profilesHBox.setAlignment(Pos.CENTER);
 * profilesHBox.setPadding(new Insets(150, 10, 0, 10));
 * profilesHBox.setStyle("-fx-background-color: lavender;");
 * 
 * // Add multiple profile cards to the HBox
 * profilesHBox.getChildren().addAll(
 * createProfileCard("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\profilepic.png"
 * ,
 * "John Doe", "Film Director", "10 years", "New York, USA",
 * "Filmmaking, Directing",
 * "John is a renowned filmmaker known for his unique storytelling.",
 * "Previous films: XYZ, ABC"),
 * createProfileCard("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\profilepic.png"
 * ,
 * "Jane Smith", "Actor", "5 years", "Los Angeles, USA",
 * "Acting, Performing",
 * "foWFN FUWPB HUWHfup Vhuh uh aurwo hf wupofh huwhfp uwuofh ahhwuafh whwufh hwuhfeawphu hfwuhf wahuf fh whf whfopah"
 * ,
 * "Previous shows: ABC, DEF"),
 * createProfileCard("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\profilepic.png"
 * ,
 * "Emily Johnson", "Producer", "15 years", "Chicago, USA",
 * "Producing, Managing", "Emily has produced award-winning films.",
 * "Previous productions: GHI, JKL"));
 * 
 * // Create pagination buttons with icons
 * Image prevIcon = new
 * Image("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\backIcon.png"
 * );
 * Image nextIcon = new
 * Image("file:PROFILE PAGE\\profile\\src\\main\\resources\\Images\\backIcon.png"
 * );
 * 
 * ImageView prevIconView = new ImageView(prevIcon);
 * prevIconView.setFitWidth(45);
 * prevIconView.setFitHeight(45);
 * Button previousPage = new Button("", prevIconView);
 * 
 * ImageView nextIconView = new ImageView(nextIcon);
 * nextIconView.setFitWidth(45);
 * nextIconView.setFitHeight(45);
 * Button nextPage = new Button("", nextIconView);
 * 
 * // Create pagination buttons
 * Button firstPage = new Button("1");
 * Button secondPage = new Button("2");
 * Button thirdPage = new Button("3");
 * Button fourthPage = new Button("4");
 * Button fifthPage = new Button("5");
 * 
 * // Style the pagination buttons
 * String buttonStyle =
 * "-fx-background-color: #0073e6; -fx-text-fill: white; -fx-font-size: 30px; "
 * +
 * "-fx-padding: 4px 25px; -fx-border-radius: 5px; -fx-background-radius: 5px; "
 * + "-fx-border-color: #005bb5; -fx-border-width: 1px; -fx-cursor: hand";
 * 
 * previousPage.setStyle(buttonStyle);
 * firstPage.setStyle(buttonStyle);
 * secondPage.setStyle(buttonStyle);
 * thirdPage.setStyle(buttonStyle);
 * fourthPage.setStyle(buttonStyle);
 * fifthPage.setStyle(buttonStyle);
 * nextPage.setStyle(buttonStyle);
 * 
 * // Create an HBox for the pagination buttons
 * HBox pageNavigationHBox = new HBox(10, previousPage, firstPage, secondPage,
 * thirdPage, fourthPage, fifthPage,
 * nextPage);
 * pageNavigationHBox.setAlignment(Pos.CENTER);
 * pageNavigationHBox.setPadding(new Insets(50, 0, 0, 0));
 * 
 * // Main layout VBox
 * mainContentBox = new VBox(50, topHBox, profilesHBox, pageNavigationHBox);
 * 
 * return mainContentBox;
 * }
 * 
 * private VBox createProfileCard(String imagePath, String name, String
 * category, String experience, String location,
 * String speakingTopics, String bio, String previousShows) {
 * Image profileImage = new Image(imagePath);
 * ImageView profileImageView = new ImageView(profileImage);
 * profileImageView.setFitWidth(145);
 * profileImageView.setFitHeight(145);
 * profileImageView.setPreserveRatio(false);
 * profileImageView.setStyle(
 * "-fx-border-color: #f0f0f0; -fx-border-width: 5px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0.0, 0.0);"
 * );
 * 
 * Label nameLabel = new Label(name);
 * nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
 * 
 * Label categoryLabel = new Label(category);
 * categoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
 * 
 * VBox profileInfoBox = new VBox(profileImageView, nameLabel, categoryLabel);
 * profileInfoBox.setAlignment(Pos.CENTER);
 * profileInfoBox.setPadding(new Insets(-95, 0, 20, 0));
 * profileInfoBox.setSpacing(10);
 * 
 * Label experienceLabel = new Label("Experience: " + experience);
 * experienceLabel.setStyle("-fx-font-size: 14px;");
 * 
 * Label locationLabel = new Label("Location: " + location);
 * locationLabel.setStyle("-fx-font-size: 14px;");
 * 
 * Label speakingTopicsLabel = new Label("Speaking Topics: " + speakingTopics);
 * speakingTopicsLabel.setStyle("-fx-font-size: 14px;");
 * 
 * // Use a Text node for the bio with wrapping
 * Text bioLabel = new Text(bio);
 * bioLabel.setStyle("-fx-font-size: 14px;");
 * bioLabel.setWrappingWidth(380); // Set the desired wrapping width
 * 
 * Label previousShowsLabel = new Label("Previous shows: " + previousShows);
 * previousShowsLabel.setStyle("-fx-font-size: 14px;");
 * 
 * Button socialMediaButton = new Button("Social Media");
 * socialMediaButton.setStyle(
 * "-fx-background-color: #4CAF45; -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-border-radius: 5px;"
 * );
 * 
 * Button bookMeButton = new Button("Book Me");
 * bookMeButton.setStyle(
 * "-fx-background-color: #4CAF45; -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-border-radius: 5px;"
 * );
 * 
 * // Add action handler to bookMeButton
 * bookMeButton.setOnAction(event -> {
 * System.out.println("Name: " + name); // Print the name associated with this
 * profile card
 * });
 * 
 * VBox buttonBox = new VBox(10, socialMediaButton, bookMeButton);
 * buttonBox.setAlignment(Pos.CENTER);
 * buttonBox.setPadding(new Insets(20, 0, 20, 0));
 * 
 * VBox mainContentBox = new VBox(10, profileInfoBox, experienceLabel,
 * locationLabel, speakingTopicsLabel,
 * bioLabel, previousShowsLabel, buttonBox);
 * mainContentBox.setMaxWidth(450);
 * mainContentBox.setMaxHeight(550);
 * mainContentBox.setPadding(new Insets(20));
 * mainContentBox.setSpacing(10);
 * mainContentBox.setStyle(
 * "-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0.0, 0.0);"
 * );
 * 
 * return mainContentBox;
 * }
 * 
 * public Scene getInfluencersProfileScene() {
 * 
 * profilePageScene = new Scene(mainContentBox);
 * 
 * return profilePageScene;
 * }
 * }
 */