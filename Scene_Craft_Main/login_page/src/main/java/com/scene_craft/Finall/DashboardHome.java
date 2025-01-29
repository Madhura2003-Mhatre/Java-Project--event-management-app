package com.scene_craft.Finall;

// import com.scene_craft.Navigation.MainApp;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DashboardHome {
    // private MainApp app;
    private HBox homePageFirstHB;
    private Timeline timeline;
    private VBox dashboardVBox;
    private ScrollPane dashboardScrollpane;
    
    public VBox createSingleBox(String companyName, String slogan, String playButtonText, String moreInfoButtonText, String imagePath, String videoPath) {

        Image homePageImage = new Image(imagePath);
        ImageView homePageimageView = new ImageView(homePageImage);
        homePageimageView.setFitWidth(1600);
        homePageimageView.setFitHeight(500);
        homePageimageView.setOpacity(0.7);
        homePageimageView.setPreserveRatio(false);

        Label cName = new Label(companyName);
        cName.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 60));
        cName.setTextFill(Color.BLACK);
        Label cSlogan = new Label(slogan);
        cSlogan.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        cSlogan.setTextFill(Color.BLACK);
        cSlogan.setPadding(new Insets(130, 0, 0, 0));

        Button playVideo = new Button(playButtonText);
        playVideo.setPrefSize(120, 50);
        playVideo.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        playVideo.setOnMouseEntered(e -> {
            playVideo.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
        });
        playVideo.setOnMouseExited(e -> {
            playVideo.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        });
        Button moreInfoButton = new Button(moreInfoButtonText);
        moreInfoButton.setPrefSize(120, 50);
        moreInfoButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        moreInfoButton.setOnMouseEntered(e -> {
            moreInfoButton.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
        });
        moreInfoButton.setOnMouseExited(e -> {
            moreInfoButton.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
        });

        HBox homeButtonHB = new HBox(20, playVideo, moreInfoButton);
        homeButtonHB.setAlignment(Pos.CENTER);
        homeButtonHB.setPadding(new Insets(250, 0, 0, 0));

        VBox vb = new VBox(cName, cSlogan, homeButtonHB);
        vb.setAlignment(Pos.CENTER);

        StackPane homePagestackPane1 = new StackPane();
        homePagestackPane1.setAlignment(Pos.CENTER);
        homePagestackPane1.getChildren().addAll(homePageimageView, cName, cSlogan, homeButtonHB);

        playVideo.setOnAction(event -> {

            Media media = new Media(getClass().getResource(videoPath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setFitWidth(650);
            mediaView.setFitHeight(400);
            mediaView.setPreserveRatio(false);

            Button stopVideo = new Button("Stop");
            stopVideo.setOnMouseEntered(e -> {
                stopVideo.setStyle("-fx-background-color: black; -fx-text-fill:#ffffff; -fx-border-color: #ffffff; -fx-border-width: 2px;");
            });
            stopVideo.setOnMouseExited(e -> {
                stopVideo.setStyle("-fx-background-color: #005A9E; -fx-text-fill: #ffffff");
            });
            HBox stopButtonHBox = new HBox(stopVideo);
            stopButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
            stopButtonHBox.setPadding(new Insets(10));
            homePagestackPane1.getChildren().addAll(mediaView, stopButtonHBox);
            mediaPlayer.play();

            mediaPlayer.setOnEndOfMedia(() -> {
                homePagestackPane1.getChildren().removeAll(mediaView, stopButtonHBox);
                resumeTimeline();
            });

            mediaPlayer.setOnStopped(() -> {
                homePagestackPane1.getChildren().removeAll(mediaView, stopButtonHBox);
                resumeTimeline();
            });

            stopVideo.setOnAction(e -> {
                mediaPlayer.stop();
                homePagestackPane1.getChildren().removeAll(mediaView, stopButtonHBox);
                resumeTimeline();
            });
        });

        VBox newVB = new VBox(homePagestackPane1);
        return newVB;
    }

    public VBox createHomePageLayout() {

        VBox box1 = createSingleBox(
                "Corporate Connect",
                "Connecting Vision to Success",
                "Play",
                "More Info",
                "icons/backgroundImage.jpg",
                "/videos/Businesss_Video.mp4");

        homePageFirstHB = new HBox(0, box1);
        homePageFirstHB.setAlignment(Pos.CENTER);
        homePageFirstHB.setPadding(new Insets(25, 0, 0, 0));

        VBox finalLayout = new VBox(homePageFirstHB);
        finalLayout.setAlignment(Pos.CENTER);

        return finalLayout;
    }

    private void resumeTimeline() {
        if (timeline != null && timeline.getStatus() == Timeline.Status.PAUSED) {
            timeline.play();
        }
    }

    public void stopTimeline() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public VBox categorySingleBox(String imagePath, String activityText) {
        Image activityImage = new Image(imagePath);
        ImageView activityImageView = new ImageView(activityImage);
        activityImageView.setFitWidth(250);
        activityImageView.setFitHeight(250);

        Circle clip = new Circle(120, 120, 120);
        activityImageView.setClip(clip);

        Label activityLabel = new Label(activityText);
        activityLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        VBox vbox = new VBox(10, activityImageView, activityLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(380, 380);

        Button activityButton = new Button();
        activityButton.setGraphic(vbox);
        activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; " + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px;");
        activityButton.setOnMouseEntered(e -> {
            activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; " + "-fx-border-color: #005A9E; -fx-border-width: 4px; -fx-border-radius: 10px;");
            activityButton.setScaleX(1.1);
            activityButton.setScaleY(1.1);
            activityLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #005A9E;");
        });
        activityButton.setOnMouseExited(e -> {
            activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; " + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px;");
            activityButton.setScaleX(1);
            activityButton.setScaleY(1);
            activityLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        });

        VBox buttonBox = new VBox(activityButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    public VBox lowerHomePageLayout() {
        VBox box1 = categorySingleBox("images/Training.png", "Training");
        box1.setStyle("-fx-background-color: #DBAEFF");
        VBox box2 = categorySingleBox("images/Workshop.png", "Workshop");
        box2.setStyle("-fx-background-color: #DBAEFF");
        VBox box3 = categorySingleBox("images/Event.png", "Events");
        box3.setStyle("-fx-background-color: #DBAEFF");
        VBox box4 = categorySingleBox("images/Seminar.png", "Seminar");
        box4.setStyle("-fx-background-color: #DBAEFF");

        Label activityLabel = new Label("What would you Like Prefer...");
        activityLabel.setStyle("-fx-font-size: 60;" + "-fx-font-weight: bold;" + "-fx-text-fill: #005A9E;" + "-fx-padding: 0 0 30 0;");

        HBox categoryHBox = new HBox(30, box1, box2, box3, box4);
        categoryHBox.setAlignment(Pos.CENTER);
        categoryHBox.setMaxWidth(1600);
        categoryHBox.setMinHeight(400);
        categoryHBox.setPadding(new Insets(0, 0, 0, 0));

        VBox secondLayout = new VBox(5, activityLabel, categoryHBox);
        secondLayout.setPadding(new Insets(25, 0, 0, 0));
        secondLayout.setAlignment(Pos.CENTER);
        return secondLayout;
    }

    public VBox createSpacer() {
        VBox spacer = new VBox();
        spacer.setMinHeight(50); // Adjust height as needed
        return spacer;
    }

    public VBox createAdditionalBlock() {
        VBox additionalBlock = new VBox();
        additionalBlock.setMinHeight(400);
        additionalBlock.setAlignment(Pos.CENTER);
        additionalBlock.setStyle("-fx-background-color: #f0f0f0;");
        Label additionalLabel = new Label("Future Scope");
        additionalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        additionalBlock.getChildren().add(additionalLabel);
        return additionalBlock;
    }

    public ScrollPane getDashboardView() {
        dashboardVBox = new VBox();
        dashboardVBox.setAlignment(Pos.TOP_CENTER);

        StackPane centeredPane = new StackPane(dashboardVBox);
        centeredPane.setAlignment(Pos.CENTER);

        dashboardScrollpane = new ScrollPane(centeredPane);
        dashboardScrollpane.setFitToWidth(true);
        dashboardScrollpane.setFitToHeight(true);

        dashboardVBox.getChildren().addAll(createHomePageLayout(), lowerHomePageLayout(), createSpacer(), createAdditionalBlock());
        return dashboardScrollpane;
    }
}
