package com.scene_craft.Finall;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NotificationPage {

    private VBox profilePageVbox;

    public VBox profileVbox() {
        profilePageVbox = new VBox(20);
        profilePageVbox.setPrefSize(300, 900);
        profilePageVbox.setAlignment(Pos.TOP_CENTER); // Start from the top

        // Add multiple cards to the VBox
        profilePageVbox.getChildren().addAll(
            createCard("Title 1", "Subtitle 1", "Description 1"),
            createCard("Title 2", "Subtitle 2", "Description 2"),
            createCard("Title 3", "Subtitle 3", "Description 3")
        );

        return profilePageVbox;
    }

    public VBox createCard(String text1, String text2, String text3) {
        // Create labels for the card
        Label label1 = new Label(text1);
        label1.setFont(new Font(20)); // Largest text

        Label label2 = new Label(text2);
        label2.setFont(new Font(16)); // Medium text

        Label label3 = new Label(text3);
        label3.setFont(new Font(12)); // Smallest text

        // Create a VBox for the card and add the labels
        VBox card = new VBox(10, label1, label2, label3);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 20 0 0 20; -fx-background-radius: 20 0 0 20; -fx-padding: 10; -fx-background-color: white;");

        return card;
    }
}
