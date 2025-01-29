package com.scene_craft.Finall;

import javafx.application.Application;
import javafx.stage.Stage;

public class InitializeApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        AdminPanelLoginPage adminLoginPageController = new AdminPanelLoginPage(primaryStage);
        primaryStage.setScene(adminLoginPageController.getLoginScene());
        primaryStage.setTitle("Corporate Connect Login Page");
        primaryStage.show();
    }
    
}
