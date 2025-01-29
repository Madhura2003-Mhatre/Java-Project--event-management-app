package com.scene_craft.Finall;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AdminPanelLoginPage {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene userScene;
    private Scene adminScene;
    private DataService dataService;
    public static String key;
    public static String userNameKey;
    public static String userEmailKey;
    public static String passwordKey;
    // public static String genderKey;
    public static String UserName;
    // public static String randomUserID;
    public static Label verifying;

    public AdminPanelLoginPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        dataService = new DataService();
        initScenes();
    }

    public void setPrimaryStage(Scene scene) {
        if (primaryStage != null) {
            primaryStage.setScene(scene);
        } else {
            System.err.println("Primary stage is not set. Cannot set scene.");
        }
    }

    private void initScenes() {
        initLoginScene();
    }

    private void initLoginScene() {

        ImageView companyLogoImageView = new ImageView(new Image("/icons/CompanyLogo.png"));
        // companyLogoImageView.setFitWidth(450);
        companyLogoImageView.setFitHeight(200);
        companyLogoImageView.setPreserveRatio(true);
        companyLogoImageView.setSmooth(true);

        ImageView firstImageView = new ImageView(new Image("images/Companyimg.jpg"));
        firstImageView.setFitWidth(500);
        firstImageView.setFitHeight(450);

        VBox imageVBox = new VBox(firstImageView);
        imageVBox.setAlignment(Pos.CENTER);

        ImageView loginIcon = new ImageView(new Image("/icons/login.png"));
        loginIcon.setFitHeight(50);
        loginIcon.setFitWidth(50);
        Label loginLabel = new Label("Login");
        loginLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        loginLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView registrationIcon = new ImageView(new Image("/icons/registration.png"));
        registrationIcon.setFitHeight(50);
        registrationIcon.setFitWidth(50);
        Label registrationLabel = new Label("Registration");
        registrationLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        registrationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView forgotPassIcon = new ImageView(new Image("/icons/forgotpassword.png"));
        forgotPassIcon.setFitHeight(50);
        forgotPassIcon.setFitWidth(50);
        Label forgotPassLabel = new Label("Forgot Password");
        forgotPassLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        forgotPassLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        VBox loginVBox = new VBox(10, loginIcon, loginLabel);
        loginVBox.setPrefSize(200, 150);
        loginVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        loginVBox.setAlignment(Pos.CENTER);
        VBox registrationVBox = new VBox(10, registrationIcon, registrationLabel);
        registrationVBox.setPrefSize(200, 150);
        registrationVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        registrationVBox.setAlignment(Pos.CENTER);
        VBox forgotPassVBox = new VBox(10, forgotPassIcon, forgotPassLabel);
        forgotPassVBox.setPrefSize(200, 150);
        forgotPassVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        forgotPassVBox.setAlignment(Pos.CENTER);

        Label loginTxtLabel = new Label("User Login");
        loginTxtLabel.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 50px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 5px;");

        //                
        

        Label username = new Label("Email : ");
        username.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 5px;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-prompt-text-fill:white ; -fx-weight:bold; -fx-font-family: Roboto Mono;");
        
        usernameField.setStyle(
            "-fx-padding: 2;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;");
        usernameField.setEffect(new BoxBlur(0, 1, 1));
        

        Label password = new Label("Password: ");
        password.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 5px;");
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle(
            "-fx-padding: 2;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;");
        passwordField.setEffect(new BoxBlur(1, 1, 1));
        passwordField.setPromptText("password");
        
        password.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 5px;");
        Button loginButton = new Button("Login");
        loginButton.setStyle(
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 25;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, " +
            "#00ff00, #32ff32, #64ff64, #96ff96, #c8ffc8, #eaffea);" + // Green rainbow gradient
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" + "-fx-cursor: HAND"
        );
        loginButton.setPrefWidth(200);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(usernameField.getText(), passwordField.getText());
                usernameField.clear();
                passwordField.clear();
            }
        });

        Label registerTxtLabel = new Label("User Registration");
        registerTxtLabel.setStyle(
            "-fx-font-family: 'Georgia';" +
                    "-fx-font-size: 50px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;" +
                    "-fx-text-fill: White;" + // Changed text color to blue
                    "-fx-padding: 10px;" +
                    "-fx-background-radius: 5px;");

        ToggleGroup accountTypeGroup = new ToggleGroup();
        RadioButton personalRadio = new RadioButton("Personal");
        personalRadio.setToggleGroup(accountTypeGroup);
        RadioButton companyRadio = new RadioButton("Company");
        companyRadio.setToggleGroup(accountTypeGroup);
        companyRadio.setSelected(true);

        HBox accountTypeBox = new HBox(10, personalRadio, companyRadio);

        Label name = new Label("Name:");
        name.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 3px;" +
                        "-fx-background-radius: 5px;");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        //
        nameField.setStyle(
            "-fx-padding: 2;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;"+
            "-fx-prompt-text-fill:;");
        nameField.setEffect(new BoxBlur(1, 1, 1));
        
        Label email = new Label("Email:");
        email.setStyle(
            "-fx-font-family: 'Georgia';" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;" +
                    "-fx-text-fill: White;" + // Changed text color to blue
                    "-fx-padding: 3px;" +
                    "-fx-background-radius: 5px;");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle(
            "-fx-padding: 3px;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: black;");
        emailField.setEffect(new BoxBlur(1, 1, 1));
        Label newPassword = new Label("Password:");
        newPassword.setStyle(
            "-fx-font-family: 'Georgia';" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;" +
                    "-fx-text-fill: black;" + // Changed text color to blue
                    "-fx-padding: 3px;" +
                    "-fx-background-radius: 5px;");
                    
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("password");
        newPasswordField.setStyle(
            "-fx-padding: 2px;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;" // White text color
        );
        newPasswordField.setEffect(new BoxBlur(1, 1, 1));
        // ToggleGroup genderGroup = new ToggleGroup();
        // RadioButton maleRadio = new RadioButton("Male");
        // maleRadio.setToggleGroup(genderGroup);
        // //maleRadio.setSelected(true);
        // RadioButton femaleRadio = new RadioButton("Female");
        // femaleRadio.setToggleGroup(genderGroup);
        // HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        Button submitButton = new Button("Submit");
        submitButton.setStyle(
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 25;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, " +
            "#00ff00, #32ff32, #64ff64, #96ff96, #c8ffc8, #eaffea);" + // Green rainbow gradient
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;"
        );
        submitButton.setPrefWidth(200);

        // RandomUserIDGenerator userID = new RandomUserIDGenerator();
        // randomUserID = RandomUserIDGenerator.generateRandomUserID();

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String randomUserID = RandomUserIDGenerator.generateRandomUserID();
                handleSubmit(nameField.getText(), emailField.getText(), newPasswordField.getText(),
                        ((RadioButton) accountTypeGroup.getSelectedToggle()).getText());
                Alert submitAlert = new Alert(AlertType.INFORMATION);
                submitAlert.setTitle("Registration Successful");
                submitAlert.setHeaderText("Registration Successful");
                submitAlert.setContentText("You had Registred Successfully !!!\nPlease Click on Login");
                submitAlert.showAndWait();
                nameField.clear();
                emailField.clear();
                newPasswordField.clear();

            }
        });

        VBox secondVBox = new VBox(loginVBox, registrationVBox, forgotPassVBox);
        secondVBox.setAlignment(Pos.CENTER);

        Label forgotPassTxtLabel = new Label("Forgot Password");
        forgotPassTxtLabel.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 50px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-text-fill: White;" + // Changed text color to blue
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 5px;");

        Label emailForgot = new Label("E-mail:");
        emailForgot.setStyle(
            "-fx-font-family: 'Georgia';" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;" +
                    "-fx-text-fill: White;" + // Changed text color to blue
                    "-fx-padding: 10px;" +
                    "-fx-background-radius: 5px;");
        TextField emailForgotField = new TextField();
        emailForgotField.setPromptText("Email");
        emailForgotField.setStyle(
            "-fx-padding: 2px;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;" // White text color
        );
        emailForgotField.setEffect(new BoxBlur(1, 1, 1));
        Button sendOTPButton = new Button("Send OTP");
        sendOTPButton.setStyle(
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 25;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, " +
            "#00ff00, #32ff32, #64ff64, #96ff96, #c8ffc8, #eaffea);" + // Green rainbow gradient
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;"
        );
        sendOTPButton.setPrefWidth(200);
        
        Label verifyOTP = new Label("Enter OTP");
        verifyOTP.setStyle(
            "-fx-font-family: 'Georgia';" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-radius: 20;" +
                    "-fx-background-radius: 20;" +
                    "-fx-text-fill: White;" + // Changed text color to blue
                    "-fx-padding: 10px;" +
                    "-fx-background-radius: 5px;");
        TextField verifyOTPTextField = new TextField();
        verifyOTPTextField.setPromptText("Verified Number");
        verifyOTPTextField.setStyle(
            "-fx-padding: 2px;" +
            "-fx-border-color: rgba(255, 255, 255, 0.5);" + // Semi-transparent white border
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-background-color: rgba(255, 255, 255, 0.2);" + // Semi-transparent white background
            "-fx-font-size: 16px;" +
            "-fx-text-fill: white;" // White text color
        );
        verifyOTPTextField.setEffect(new BoxBlur(1, 1, 1));

        VBox thirdVBox = new VBox(10, loginTxtLabel, username, usernameField, password, passwordField, loginButton);
        thirdVBox.setAlignment(Pos.CENTER);
        thirdVBox.setStyle("-fx-background-color : green; -fx-border-color: green");
        thirdVBox.setPadding(new Insets(30,30,30,30));
        thirdVBox.setMinHeight(450);
        thirdVBox.setMinWidth(700);

        loginVBox.setOnMouseClicked(E -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(loginTxtLabel, username, usernameField, password, passwordField,
                    loginButton);
            loginVBox.setStyle("-fx-background-color : #005A9E");
        });
        loginVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : lightgreen");
            registrationVBox.setStyle("-fx-background-color : #ffffff");
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });
        loginVBox.setOnMouseExited(E -> {
            registrationVBox.setStyle("-fx-background-color : #ffffff");
        });

        registrationVBox.setOnMouseClicked(e -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(registerTxtLabel, accountTypeBox, email, emailField, name, nameField,
                    newPassword, newPasswordField, submitButton);
            registrationVBox.setStyle("-fx-background-color : #005A9E");
        });
        registrationVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : #ffffff");
            registrationVBox.setStyle("-fx-background-color : lightgreen");
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });
        registrationVBox.setOnMouseExited(E -> {
            registrationVBox.setStyle("-fx-background-color : #ffffff");
        });

        forgotPassVBox.setOnMouseClicked(e -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(forgotPassTxtLabel, emailForgot, emailForgotField, sendOTPButton, verifyOTP,
                    verifyOTPTextField);
            forgotPassVBox.setStyle("-fx-background-color : #005A9E");
        });
        forgotPassVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : #ffffff");
            registrationVBox.setStyle("-fx-background-color : #ffffff");
            forgotPassVBox.setStyle("-fx-background-color : lightgreen");
        });
        forgotPassVBox.setOnMouseExited(E -> {
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });

        HBox middleHBox = new HBox(0, imageVBox, secondVBox, thirdVBox);
        middleHBox.setAlignment(Pos.CENTER);

        VBox mainVBox = new VBox(40, companyLogoImageView, middleHBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setMaxSize(1919, 990);
        mainVBox.setMinSize(1919, 990);

        ImageView backgroundImage = new ImageView(new Image("images/backgroundImage.jpg"));
        backgroundImage.setOpacity(0.25);
        backgroundImage.setFitWidth(1919);
        backgroundImage.setFitHeight(990);

        StackPane stackPane = new StackPane(backgroundImage, mainVBox);
        stackPane.setAlignment(Pos.CENTER);

        Group grp = new Group(stackPane);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(usernameField.getText(), passwordField.getText());
                usernameField.clear();
                passwordField.clear();
            }
        });

        loginScene = new Scene(grp, 1919, 990);
    }

    private void initUserScene() {
        UserPage userPage = new UserPage(dataService);
        userScene = new Scene(userPage.createUserScene(this::handleLogout), 1919, 990);
    }

    // private void initAdminScene() {
    // AdminPage adminPage = new AdminPage(this, dataService);
    // adminScene = new Scene(adminPage.createAdminDashboard(this::handleLogout),
    // 700, 700);
    // }

    public Scene getLoginScene() {
        return loginScene;
    }

    private void handleLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password)) {
                key = username;
                UserName = username;
                System.out.println("Login Pressed...");
                verifying = new Label("Veryfing...Just a Second");
                verifying.setStyle("-fx-font-size:20; -fx-text-fill:RED");
                initUserScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("User Dashboard");
            } else {
                System.out.println("Invalid Credentials");
                Alert incorrectInputAlert = new Alert(AlertType.INFORMATION);
                incorrectInputAlert.setTitle("Incorrect Input");
                incorrectInputAlert.setHeaderText("Invalid Credentials");
                incorrectInputAlert.setContentText("Please enter valid credentials");
                incorrectInputAlert.showAndWait();
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleSubmit(String nameField, String email, String newPasswordField, String accountUse) {
        if (nameField != null && email != null && newPasswordField != null && accountUse != null) {
            DataService dataService;
            try {
                dataService = new DataService();
                Map<String, Object> data = new HashMap<>();
                data.put("name", nameField);
                data.put("email", email);
                data.put("password", newPasswordField);
                data.put("accountUse", accountUse);
                // data.put("gender", gender);
                dataService.addData("users", email, data);
                nameField = null;
                System.out.println("User registered successfully");

                // loginPageController.showLoginScene();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert sihnUpError = new Alert(AlertType.INFORMATION);
            sihnUpError.setTitle("Error");
            sihnUpError.setHeaderText("Invalid Input");
            sihnUpError.setContentText("Please fill all the fields");
            sihnUpError.showAndWait();
        }
    }

    private void handleLogout() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
    }

    public void returnToAdminView() {
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Dashboard");
    }
}




/*package com.scene_craft.Finall;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AdminPanelLoginPage {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene userScene;
    private Scene adminScene;
    private DataService dataService;
    public static String key;
    public static String userNameKey;
    public static String userEmailKey;
    public static String passwordKey;
    // public static String genderKey;
    public static String UserName;
    // public static String randomUserID;
    public static Label verifying;

    public AdminPanelLoginPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        dataService = new DataService();
        initScenes();
    }

    public void setPrimaryStage(Scene scene) {
        if (primaryStage != null) {
            primaryStage.setScene(scene);
        } else {
            System.err.println("Primary stage is not set. Cannot set scene.");
        }
    }

    private void initScenes() {
        initLoginScene();
    }

    private void initLoginScene() {

        ImageView companyLogoImageView = new ImageView(new Image("/icons/CompanyLogo.png"));
        // companyLogoImageView.setFitWidth(450);
        companyLogoImageView.setFitHeight(200);
        companyLogoImageView.setPreserveRatio(true);
        companyLogoImageView.setSmooth(true);

        ImageView firstImageView = new ImageView(new Image("/icons/CompanyLogo.png"));
        firstImageView.setFitWidth(300);
        firstImageView.setFitHeight(450);

        VBox imageVBox = new VBox(firstImageView);
        imageVBox.setAlignment(Pos.CENTER);

        ImageView loginIcon = new ImageView(new Image("/icons/login.png"));
        loginIcon.setFitHeight(50);
        loginIcon.setFitWidth(50);
        Label loginLabel = new Label("Login");
        loginLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        loginLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView registrationIcon = new ImageView(new Image("/icons/registration.png"));
        registrationIcon.setFitHeight(50);
        registrationIcon.setFitWidth(50);
        Label registrationLabel = new Label("Registration");
        registrationLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        registrationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView forgotPassIcon = new ImageView(new Image("/icons/forgotpassword.png"));
        forgotPassIcon.setFitHeight(50);
        forgotPassIcon.setFitWidth(50);
        Label forgotPassLabel = new Label("Forgot Password");
        forgotPassLabel.setStyle(
                "-fx-font-family: 'Verdana'; -fx-font-weight: bold; -fx-font-style: normal; -fx-font-size: 20; -fx-text-fill: #000000;");
        forgotPassLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        VBox loginVBox = new VBox(10, loginIcon, loginLabel);
        loginVBox.setPrefSize(200, 150);
        loginVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        loginVBox.setAlignment(Pos.CENTER);
        VBox registrationVBox = new VBox(10, registrationIcon, registrationLabel);
        registrationVBox.setPrefSize(200, 150);
        registrationVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        registrationVBox.setAlignment(Pos.CENTER);
        VBox forgotPassVBox = new VBox(10, forgotPassIcon, forgotPassLabel);
        forgotPassVBox.setPrefSize(200, 150);
        forgotPassVBox.setStyle("-fx-background-color : WHITE; -fx-cursor:HAND");
        forgotPassVBox.setAlignment(Pos.CENTER);

        Label loginTxtLabel = new Label("-- User Login --");

        Label username = new Label("Email : ");
        TextField usernameField = new TextField();

        Label password = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(usernameField.getText(), passwordField.getText());
                usernameField.clear();
                passwordField.clear();
            }
        });

        Label registerTxtLabel = new Label("-- User Registration --");

        ToggleGroup accountTypeGroup = new ToggleGroup();
        RadioButton personalRadio = new RadioButton("Personal");
        personalRadio.setToggleGroup(accountTypeGroup);
        RadioButton companyRadio = new RadioButton("Company");
        companyRadio.setToggleGroup(accountTypeGroup);
        companyRadio.setSelected(true);

        HBox accountTypeBox = new HBox(10, personalRadio, companyRadio);

        Label name = new Label("Name:");
        TextField nameField = new TextField();
        Label email = new Label("Email:");
        TextField emailField = new TextField();
        Label newPassword = new Label("Password:");
        PasswordField newPasswordField = new PasswordField();
        // ToggleGroup genderGroup = new ToggleGroup();
        // RadioButton maleRadio = new RadioButton("Male");
        // maleRadio.setToggleGroup(genderGroup);
        // //maleRadio.setSelected(true);
        // RadioButton femaleRadio = new RadioButton("Female");
        // femaleRadio.setToggleGroup(genderGroup);
        // HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        Button submitButton = new Button("Submit");

        // RandomUserIDGenerator userID = new RandomUserIDGenerator();
        // randomUserID = RandomUserIDGenerator.generateRandomUserID();

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String randomUserID = RandomUserIDGenerator.generateRandomUserID();
                handleSubmit(nameField.getText(), emailField.getText(), newPasswordField.getText(),
                        ((RadioButton) accountTypeGroup.getSelectedToggle()).getText());
                Alert submitAlert = new Alert(AlertType.INFORMATION);
                submitAlert.setTitle("Registration Successful");
                submitAlert.setHeaderText("Registration Successful");
                submitAlert.setContentText("You had Registred Successfully !!!\nPlease Click on Login");
                submitAlert.showAndWait();
                nameField.clear();
                emailField.clear();
                newPasswordField.clear();

            }
        });

        VBox secondVBox = new VBox(loginVBox, registrationVBox, forgotPassVBox);
        secondVBox.setAlignment(Pos.CENTER);

        Label forgotPassTxtLabel = new Label("-- Forgot Password --");

        Label emailForgot = new Label("E-mail:");
        TextField emailForgotField = new TextField();
        Button sendOTPButton = new Button("Send OTP");
        Label verifyOTP = new Label("Enter OTP");
        TextField verifyOTPTextField = new TextField();

        VBox thirdVBox = new VBox(10, loginTxtLabel, username, usernameField, password, passwordField, loginButton);
        thirdVBox.setAlignment(Pos.CENTER);
        thirdVBox.setStyle("-fx-background-color : WHITE; -fx-border-color: #c8ac97");
        thirdVBox.setMinHeight(450);
        thirdVBox.setMinWidth(700);

        loginVBox.setOnMouseClicked(E -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(loginTxtLabel, username, usernameField, password, passwordField,
                    loginButton);
            loginVBox.setStyle("-fx-background-color : #005A9E");
        });
        loginVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : #005A9E");
            registrationVBox.setStyle("-fx-background-color : #ffffff");
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });
        loginVBox.setOnMouseExited(E -> {
            registrationVBox.setStyle("-fx-background-color : #ffffff");
        });

        registrationVBox.setOnMouseClicked(e -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(registerTxtLabel, accountTypeBox, email, emailField, name, nameField,
                    newPassword, newPasswordField, submitButton);
            registrationVBox.setStyle("-fx-background-color : #005A9E");
        });
        registrationVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : #ffffff");
            registrationVBox.setStyle("-fx-background-color : #005A9E");
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });
        registrationVBox.setOnMouseExited(E -> {
            registrationVBox.setStyle("-fx-background-color : #ffffff");
        });

        forgotPassVBox.setOnMouseClicked(e -> {
            thirdVBox.getChildren().clear();
            thirdVBox.getChildren().addAll(forgotPassTxtLabel, emailForgot, emailForgotField, sendOTPButton, verifyOTP,
                    verifyOTPTextField);
            forgotPassVBox.setStyle("-fx-background-color : #005A9E");
        });
        forgotPassVBox.setOnMouseEntered(E -> {
            loginVBox.setStyle("-fx-background-color : #ffffff");
            registrationVBox.setStyle("-fx-background-color : #ffffff");
            forgotPassVBox.setStyle("-fx-background-color : #005A9E");
        });
        forgotPassVBox.setOnMouseExited(E -> {
            forgotPassVBox.setStyle("-fx-background-color : #ffffff");
        });

        HBox middleHBox = new HBox(0, imageVBox, secondVBox, thirdVBox);
        middleHBox.setAlignment(Pos.CENTER);

        VBox mainVBox = new VBox(40, companyLogoImageView, middleHBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setMaxSize(1919, 990);
        mainVBox.setMinSize(1919, 990);

        ImageView backgroundImage = new ImageView(new Image("images/backgroundImage.jpg"));
        backgroundImage.setOpacity(0.25);
        backgroundImage.setFitWidth(1919);
        backgroundImage.setFitHeight(990);

        StackPane stackPane = new StackPane(backgroundImage, mainVBox);
        stackPane.setAlignment(Pos.CENTER);

        Group grp = new Group(stackPane);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(usernameField.getText(), passwordField.getText());
                usernameField.clear();
                passwordField.clear();
            }
        });

        loginScene = new Scene(grp, 1919, 990);
    }

    private void initUserScene() {
        UserPage userPage = new UserPage(dataService);
        userScene = new Scene(userPage.createUserScene(this::handleLogout), 1919, 990);
    }

    // private void initAdminScene() {
    // AdminPage adminPage = new AdminPage(this, dataService);
    // adminScene = new Scene(adminPage.createAdminDashboard(this::handleLogout),
    // 700, 700);
    // }

    public Scene getLoginScene() {
        return loginScene;
    }

    private void handleLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password)) {
                key = username;
                UserName = username;
                System.out.println("Login Pressed...");
                verifying = new Label("Veryfing...Just a Second");
                verifying.setStyle("-fx-font-size:20; -fx-text-fill:RED");
                initUserScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("User Dashboard");
            } else {
                System.out.println("Invalid Credentials");
                Alert incorrectInputAlert = new Alert(AlertType.INFORMATION);
                incorrectInputAlert.setTitle("Incorrect Input");
                incorrectInputAlert.setHeaderText("Invalid Credentials");
                incorrectInputAlert.setContentText("Please enter valid credentials");
                incorrectInputAlert.showAndWait();
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleSubmit(String nameField, String email, String newPasswordField, String accountUse) {
        if (nameField != null && email != null && newPasswordField != null && accountUse != null) {
            DataService dataService;
            try {
                dataService = new DataService();
                Map<String, Object> data = new HashMap<>();
                data.put("name", nameField);
                data.put("email", email);
                data.put("password", newPasswordField);
                data.put("accountUse", accountUse);
                // data.put("gender", gender);
                dataService.addData("users", email, data);
                nameField = null;
                System.out.println("User registered successfully");

                // loginPageController.showLoginScene();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert sihnUpError = new Alert(AlertType.INFORMATION);
            sihnUpError.setTitle("Error");
            sihnUpError.setHeaderText("Invalid Input");
            sihnUpError.setContentText("Please fill all the fields");
            sihnUpError.showAndWait();
        }
    }

    private void handleLogout() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
    }

    public void returnToAdminView() {
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Dashboard");
    }
}
*/