package com.scene_craft.Finall;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class HomePageLowerBox {

    public VBox categorySingleBox(String imagePath, String activityText, Runnable action) {
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
        vbox.setPrefSize(370, 370);

        Button activityButton = new Button();
        activityButton.setGraphic(vbox);
        activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; "
                + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px;");
        
        // Add action handler to execute the provided action when clicked
        activityButton.setOnAction(event -> action.run());

        activityButton.setOnMouseEntered(e -> {
            activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; "
                    + "-fx-border-color: #005A9E; -fx-border-width: 4px; -fx-border-radius: 10px;");
            activityButton.setScaleX(1.1);
            activityButton.setScaleY(1.1);
            activityLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #005A9E;");
        });
        activityButton.setOnMouseExited(e -> {
            activityButton.setStyle("-fx-background-color: transparent; -fx-padding: 10; -fx-cursor: hand; "
                    + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px;");
            activityButton.setScaleX(1);
            activityButton.setScaleY(1);
            activityLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        });

        VBox buttonBox = new VBox(activityButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    public VBox lowerHomePageLayout() {
        VBox box1 = categorySingleBox("file:images/Training.png", "Training", () -> System.out.println("Training clicked"));
        VBox box2 = categorySingleBox("file:images/Workshop.png", "Workshop", () -> System.out.println("Workshop clicked"));
        VBox box3 = categorySingleBox("file:images/Event.png", "Events", () -> System.out.println("Events clicked"));
        VBox box4 = categorySingleBox("file:images/Seminar.png", "Seminar", () -> System.out.println("Seminar clicked"));

        Label activityLabel = new Label("Current Activities...");
        activityLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold; -fx-text-fill: #005A9E; -fx-padding: 0 0 30 0;");

        HBox categoryHBox = new HBox(30, box1, box2, box3, box4);
        categoryHBox.setAlignment(Pos.CENTER);
        categoryHBox.setMaxWidth(1600);
        categoryHBox.setPadding(new Insets(0, 0, 0, 0));

        VBox mainVBox = new VBox(0, activityLabel, categoryHBox);
        mainVBox.setPadding(new Insets(25, 0, 0, 0));
        mainVBox.setAlignment(Pos.CENTER);
        return mainVBox;
    }
}
