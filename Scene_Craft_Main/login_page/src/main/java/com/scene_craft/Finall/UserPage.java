package com.scene_craft.Finall;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.google.cloud.firestore.DocumentSnapshot;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UserPage {

    private DataService dataService;
    private boolean isSidebarVisible = true;
    private boolean isProfileSidebarVisible = false; // Initially set to false
    private boolean isNotificationSidebarVisible = false;
    private static VBox firstPageMainVBox;
    public static String loginUserName = AdminPanelLoginPage.UserName;
    public static BorderPane mainLayout;

    public UserPage(DataService dataService) {
        this.dataService = dataService;
    }

    // Method to create and return the user interface VBox for project data entry
    public VBox createUserScene(Runnable logoutHandler) {

        HBox topSection = new HBox(10);
        topSection.setPadding(new Insets(10));
        topSection.setAlignment(Pos.CENTER_RIGHT);
        topSection.setId("topSection");
        topSection.setStyle(
                "-fx-background-color: #F3F3F3; -fx-border-color: #CCCCCC; -fx-border-width: 0 0 1 0; -fx-padding: 15;");

        HBox topLeftSection = new HBox(5);
        topLeftSection.setAlignment(Pos.CENTER_LEFT);
        Image companyLogo = new Image("logo_icons/CompanyLogo.png");
        ImageView companyLogoView = new ImageView(companyLogo);
        companyLogoView.setFitHeight(90);
        companyLogoView.setFitWidth(340);
        HBox.setMargin(companyLogoView, new Insets(0, 0, 0, 100));
        HBox logoHBox = new HBox(companyLogoView);

        Button menuButton = new Button("☰ Menu");
        menuButton.setId("menuButton");
        menuButton.setStyle("-fx-background-color: transparent; -fx-padding:0 0 0 25; -fx-text-fill: #0078D7; -fx-font-size: 25px; -fx-cursor: hand;");
        menuButton.setOnMouseEntered(e -> {
            menuButton.setStyle("-fx-background-color: transparent; -fx-padding:0 0 0 25; -fx-text-fill: #005A9E; -fx-font-size: 25px; -fx-cursor: hand;");

        });
        menuButton.setOnMouseExited(e -> {
            menuButton.setStyle("-fx-background-color: transparent; -fx-padding:0 0 0 25; -fx-text-fill: #0078D7; -fx-font-size: 25px; -fx-cursor: hand;");

        });

        topLeftSection.getChildren().addAll(menuButton, logoHBox);

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search...");
        searchBox.setPrefHeight(45);
        searchBox.setPrefWidth(350);
        searchBox.setFont(new Font(20));
        searchBox.getStyleClass().add("text-field");
        searchBox.setOnMouseEntered(e -> {
            searchBox.setStyle("-fx-border-color: #005A9E");
        });
        searchBox.setOnMouseExited(e -> {
            searchBox.setStyle("-fx-border-color: transparent");
        });

        ImageView searchImageView = new ImageView(new Image("logo_icons/SimpleIcons/search.png"));
        Button searchButton = new Button();
        searchButton.setGraphic(searchImageView);
        searchButton.setMaxWidth(50);
        searchButton.setMaxHeight(50);

        searchButton.setStyle("-fx-background-color: transparent");

        searchButton.setOnAction(e -> {
            // String searchText = searchBox.getText();
            // Perform search action if needed
            // SecondScene secondScene = new SecondScene();
            // secondScene.start(primaryStage);
        });

        ImageView notificationImageView = new ImageView(new Image("logo_icons/SimpleIcons/notification.png"));
        Button notificationButton = new Button();
        notificationButton.setGraphic(notificationImageView);
        notificationButton.setMaxWidth(50);
        notificationButton.setMaxHeight(50);
        notificationButton.setStyle("-fx-background-color: transparent");

        ImageView profileImageView = new ImageView(new Image("images/profileIcon.png"));
        profileImageView.setFitWidth(50);
        profileImageView.setFitHeight(50);
        final Circle clip = new Circle(25, 25, 25);
        profileImageView.setClip(clip);

        Button profileButton = new Button();
        profileButton.setGraphic(profileImageView);
        profileButton.setMaxWidth(40);
        profileButton.setMaxHeight(40);
        profileButton.setStyle("-fx-background-color: transparent");

        HBox rightAlignBox = new HBox(5);
        rightAlignBox.setAlignment(Pos.CENTER_RIGHT);
        rightAlignBox.getChildren().addAll(searchBox, searchButton, notificationButton, profileButton);

        topSection.getChildren().addAll(topLeftSection, rightAlignBox);
        HBox.setHgrow(rightAlignBox, Priority.ALWAYS);

        VBox leftMenu = new VBox(15);
        leftMenu.setPadding(new Insets(20, 20, 0, 20));
        leftMenu.setId("leftMenu");
        leftMenu.setPrefSize(200, 900);
        leftMenu.setStyle("-fx-background-color: #2C3E50; -fx-padding: 15;");

        VBox profileRightMenu = new VBox(15);
        profileRightMenu.setPadding(new Insets(10, 0, 10, 10));
        profileRightMenu.setId("profileRightMenu"); // Set ID for CSS styling
        Profilepage profilePage = new Profilepage();
        VBox profileVBox = profilePage.profileVbox(logoutHandler);
        profileVBox.setStyle(
                "-fx-border-radius: 20 0 0 20; -fx-background-radius: 20 0 0 20; -fx-border-color: #ccc; -fx-border-width: 2px;");
        profileRightMenu.getChildren().addAll(profileVBox);

        VBox notificationRightMenu = new VBox(15);
        notificationRightMenu.setPadding(new Insets(10, 0, 10, 10));
        notificationRightMenu.setId("notificationRightMenu"); // Set ID for CSS styling
        NotificationPage notificationPage = new NotificationPage();
        VBox notificationVBox = notificationPage.profileVbox();
        notificationVBox.setStyle(
                "-fx-border-radius: 20 0 0 20; -fx-background-radius: 20 0 0 20; -fx-border-color: #ccc; -fx-border-width: 4px;");
        notificationVBox.setPadding(new Insets(5, 0, 0, 5));
        notificationRightMenu.getChildren().addAll(notificationVBox);

        VBox topButtons = new VBox(15);
        ImageView dashboardImageView = new ImageView(new Image("logo_icons/SimpleIcons/dashboard.png"));
        Button dashboardButton = new Button("   Dashboard");
        dashboardButton.setGraphic(dashboardImageView);
        dashboardButton.setOnAction(e -> {
            dashboardButton.setStyle("-fx-background-color: #34495E;");
        });

        ImageView eventsImageView = new ImageView(new Image("logo_icons/SimpleIcons/events.png"));
        Button eventsButton = new Button("   Events");
        eventsButton.setGraphic(eventsImageView);

        ImageView aboutUsImageView = new ImageView(new Image("logo_icons/SimpleIcons/aboutus.png"));
        Button aboutUsButton = new Button("   About Us");
        aboutUsButton.setGraphic(aboutUsImageView);

        ImageView contactUsImageView = new ImageView(new Image("logo_icons/SimpleIcons/contact.png"));
        Button contactUsButton = new Button("   Contact");
        contactUsButton.setGraphic(contactUsImageView);

        ImageView helpCenterImageView = new ImageView(new Image("logo_icons/SimpleIcons/helpcenter.png"));
        Button helpCenterButton = new Button("   Help Center");
        helpCenterButton.setGraphic(helpCenterImageView);

        ImageView settingsImageView = new ImageView(new Image("logo_icons/SimpleIcons/settings.png"));
        Button settingsButton = new Button("   Settings");
        settingsButton.setGraphic(settingsImageView);

        topButtons.getChildren().addAll(dashboardButton, eventsButton, aboutUsButton, contactUsButton);

        VBox bottomButtons = new VBox(15);
        bottomButtons.getChildren().addAll(helpCenterButton, settingsButton);

        Pane spacer = new Pane();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        leftMenu.getChildren().addAll(topButtons, spacer, bottomButtons);

        String buttonCSS1 = new String(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 8 15; -fx-cursor: hand; -fx-font-size: 18px; -fx-text-align: left; -fx-border: none");
        String buttonCSS2 = new String(
                "-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 8 15; -fx-cursor: hand; -fx-font-size: 18px; -fx-text-align: left; -fx-border: white;");


        // Style and event handlers for dashboardButton
        dashboardButton.setStyle(buttonCSS1);
        dashboardButton.setOnMouseEntered(e -> {
            dashboardButton.setStyle(buttonCSS2);
        });
        dashboardButton.setOnMouseExited(e -> {
            dashboardButton.setStyle(buttonCSS1);
        });

        // Style and event handlers for aboutUsButton
        aboutUsButton.setStyle(buttonCSS1);
        aboutUsButton.setOnMouseEntered(e -> {
            aboutUsButton.setStyle(buttonCSS2);
        });
        aboutUsButton.setOnMouseExited(e -> {
            aboutUsButton.setStyle(buttonCSS1);
        });

        // Style and event handlers for eventsButton
        eventsButton.setStyle(buttonCSS1);
        eventsButton.setOnMouseEntered(e -> {
            eventsButton.setStyle(buttonCSS2);
        });
        eventsButton.setOnMouseExited(e -> {
            eventsButton.setStyle(buttonCSS1);
        });

        // Style and event handlers for helpCenterButton
        helpCenterButton.setStyle(buttonCSS1);
        helpCenterButton.setOnMouseEntered(e -> {
            helpCenterButton.setStyle(buttonCSS2);
        });
        helpCenterButton.setOnMouseExited(e -> {
            helpCenterButton.setStyle(buttonCSS1);
        });

        // Style and event handlers for contactUsButton
        contactUsButton.setStyle(buttonCSS1);
        contactUsButton.setOnMouseEntered(e -> {
            contactUsButton.setStyle(buttonCSS2);
        });
        contactUsButton.setOnMouseExited(e -> {
            contactUsButton.setStyle(buttonCSS1);
        });

        // Style and event handlers for settingsButton
        settingsButton.setStyle(buttonCSS1);
        settingsButton.setOnMouseEntered(e -> {
            settingsButton.setStyle(buttonCSS2);
        });
        settingsButton.setOnMouseExited(e -> {
            settingsButton.setStyle(buttonCSS1);
        });

        DashboardHome dashboardHome = new DashboardHome();
        ScrollPane dashboardHomeLayout = dashboardHome.getDashboardView();

        // Set the main layout
        mainLayout = new BorderPane();
        mainLayout.setTop(topSection);
        mainLayout.setLeft(leftMenu);
        mainLayout.setCenter(dashboardHomeLayout); // Add the scrollPane to the center of the BorderPane
        mainLayout.setId("mainLayout"); // Set the ID for the CSS rule

        menuButton.setOnAction(e -> {
            TranslateTransition transition = new TranslateTransition(Duration.millis(200), leftMenu);
            if (isSidebarVisible) {
                transition.setToX(-leftMenu.getWidth());
                transition.setOnFinished(event -> mainLayout.setLeft(null));
            } else {
                mainLayout.setLeft(leftMenu);
                transition.setFromX(-leftMenu.getWidth());
                transition.setToX(0);
            }
            isSidebarVisible = !isSidebarVisible;
            transition.play();
        });

        profileButton.setOnAction(e -> {
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), profileRightMenu);
            if (isProfileSidebarVisible) {
                transition.setToX(profileRightMenu.getWidth());
                transition.setOnFinished(event -> mainLayout.setRight(null));
            } else {
                mainLayout.setRight(profileRightMenu);
                transition.setFromX(profileRightMenu.getWidth());
                transition.setToX(0);
            }
            isProfileSidebarVisible = !isProfileSidebarVisible;
            transition.play();
        });

        notificationButton.setOnAction(e -> {
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), notificationRightMenu);
            if (isNotificationSidebarVisible) {
                transition.setToX(notificationRightMenu.getWidth());
                transition.setOnFinished(event -> mainLayout.setRight(null));
            } else {
                mainLayout.setRight(notificationRightMenu);
                transition.setFromX(notificationRightMenu.getWidth());
                transition.setToX(0);
            }
            isNotificationSidebarVisible = !isNotificationSidebarVisible;
            transition.play();
        });

        dashboardButton.setOnAction(e -> {
            mainLayout.setCenter(dashboardHomeLayout);
        });

        Influencers influencers = new Influencers();
        VBox influencersLayout = influencers.cerateInfluencerVBox();
        influencersLayout.setPadding(new Insets(0, 0, 0, 0));

        eventsButton.setOnAction(e -> {
            mainLayout.setCenter(influencersLayout);
            // Handle events button click
            // System.out.println("Events button clicked");
            // EventsScene.display(); // Open the Events scene
        });

        aboutUsButton.setOnAction(e -> {
            // Handle about us button click
            System.out.println("About Us button clicked");
            // You can add more functionality here
        });

        contactUsButton.setOnAction(e -> {
            // Handle contact us button click
            System.out.println("Contact button clicked");
            // You can add more functionality here
        });

        helpCenterButton.setOnAction(e -> {
            // Handle help center button click
            System.out.println("Help Center button clicked");
            // You can add more functionality here
        });

        settingsButton.setOnAction(e -> {
            // Handle settings button click
            System.out.println("Settings button clicked");
            // You can add more functionality here
        });
        firstPageMainVBox = new VBox(0, mainLayout);

        // VBox dataBox = new VBox(25, vButton, message, groupBox, projectBox, mobBox, leaderNameBox, mem1Box, mem2Box, mem3Box, buttonBox, dataMsg);
        // dataBox.setAlignment(Pos.CENTER);
        return firstPageMainVBox;
    }

    // Method to fetch and display the logged-in user's name
    public Text getTheLoginName() {
        Label dataLabel = new Label();
        try {
            String key = AdminPanelLoginPage.key;
            System.out.println("Value of key: " + key);
            DocumentSnapshot dataObject = dataService.getData("users", key);
            String userName = dataObject.getString("username");
            System.out.println("Username fetched: " + userName);
            dataLabel.setText(userName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Text("Welcome " + dataLabel.getText());
    }
}
