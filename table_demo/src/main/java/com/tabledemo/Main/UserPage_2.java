package com.tabledemo.Main;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserPage_2 {

    private DataService_2 dataService; // DataService for Firestore operations
    private Label dataMsg; // Label to display status messages
    private TableView<ProjectData> tableView; // TableView to display data
    private ObservableList<ProjectData> dataList; // ObservableList for TableView data

    public UserPage_2(DataService_2 dataService) {
        this.dataService = dataService;
        this.dataList = FXCollections.observableArrayList();
    }

    // Method to create and return the user interface VBox for project data entry
    public VBox createUserScene(Runnable logoutHandler) {
        // Initialize dataMsg for displaying status messages
        dataMsg = new Label();

        // UI elements for entering project details
        Label groupLabel = new Label("Enter group name:");
        groupLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField groupName = new TextField();
        groupName.setPromptText("Group name");
        VBox groupBox = new VBox(10, groupLabel, groupName);
        groupBox.setMaxSize(300, 20);

        Label projectLabel = new Label("Enter project name:");
        projectLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField projectName = new TextField();
        projectName.setPromptText("Project name");
        VBox projectBox = new VBox(10, projectLabel, projectName);
        projectBox.setMaxSize(300, 20);

        Label mobileNumber = new Label("Enter mobile number:");
        mobileNumber.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField mobTextField = new TextField();
        mobTextField.setPromptText("Mobile number");
        VBox mobBox = new VBox(10, mobileNumber, mobTextField);
        mobBox.setMaxSize(300, 20);

        Label leaderLabel = new Label("Enter leader name:");
        leaderLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField leaderName = new TextField();
        leaderName.setPromptText("Leader name");
        VBox leaderNameBox = new VBox(10, leaderLabel, leaderName);
        leaderNameBox.setMaxSize(300, 20);

        Label memberLabel1 = new Label("Enter member 1 name:");
        memberLabel1.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField member1 = new TextField();
        member1.setPromptText("Member 1 name");
        VBox mem1Box = new VBox(10, memberLabel1, member1);
        mem1Box.setMaxSize(300, 20);

        Label memberLabel2 = new Label("Enter member 2 name:");
        memberLabel2.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField member2 = new TextField();
        member2.setPromptText("Member 2 name");
        VBox mem2Box = new VBox(10, memberLabel2, member2);
        mem2Box.setMaxSize(300, 20);

        Label memberLabel3 = new Label("Enter member 3 name:");
        memberLabel3.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField member3 = new TextField();
        member3.setPromptText("Member 3 name");
        VBox mem3Box = new VBox(10, memberLabel3, member3);
        mem3Box.setMaxSize(300, 20);

        // TableView setup
        tableView = new TableView<>();
        TableColumn<ProjectData, String> groupNameColumn = new TableColumn<>("Group Name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<ProjectData, String> projectNameColumn = new TableColumn<>("Project Name");
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));

        TableColumn<ProjectData, String> leaderNameColumn = new TableColumn<>("Leader Name");
        leaderNameColumn.setCellValueFactory(new PropertyValueFactory<>("leaderName"));

        TableColumn<ProjectData, String> mobileNumberColumn = new TableColumn<>("Mobile Number");
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

        TableColumn<ProjectData, String> member1Column = new TableColumn<>("Member 1");
        member1Column.setCellValueFactory(new PropertyValueFactory<>("member1"));

        TableColumn<ProjectData, String> member2Column = new TableColumn<>("Member 2");
        member2Column.setCellValueFactory(new PropertyValueFactory<>("member2"));

        TableColumn<ProjectData, String> member3Column = new TableColumn<>("Member 3");
        member3Column.setCellValueFactory(new PropertyValueFactory<>("member3"));

        tableView.getColumns().add(groupNameColumn);
        tableView.getColumns().add(projectNameColumn);
        tableView.getColumns().add(leaderNameColumn);
        tableView.getColumns().add(mobileNumberColumn);
        tableView.getColumns().add(member1Column);
        tableView.getColumns().add(member2Column);
        tableView.getColumns().add(member3Column);
        tableView.setItems(dataList);

        // Button to add project data
        Button addButton = new Button("Add Data");
        addButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        HBox buttonBox = new HBox(addButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Button to load data into TableView
        Button loadDataButton = new Button("Load Data");
        loadDataButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        HBox loadButtonBox = new HBox(loadDataButton);
        loadButtonBox.setAlignment(Pos.CENTER);

        // Button to logout
        Button logoutButton = new Button("Logout");

        // Action handler for addButton
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Create a map with project data
                Map<String, Object> data = new HashMap<>();
                data.put("groupName", groupName.getText());
                data.put("projectName", projectName.getText());
                data.put("leaderName", leaderName.getText());
                data.put("mobileNum", mobTextField.getText());
                data.put("member1", member1.getText());
                data.put("member2", member2.getText());
                data.put("member3", member3.getText());
                data.put("timestamp", LocalDateTime.now());

                try {
                    // Attempt to add data to Firestore
                    dataService.
                    addData("collectionName", leaderName.getText(), data);
                    dataMsg.setText("Added successfully"); // Update status message
                    // Clear input fields after successful addition
                    groupName.clear();
                    projectName.clear();
                    leaderName.clear();
                    mobTextField.clear();
                    member1.clear();
                    member2.clear();
                    member3.clear();
                } catch (ExecutionException | InterruptedException ex) {
                    dataMsg.setText("Something went wrong"); // Update status message
                    ex.printStackTrace();
                }
            }
        });

        // Action handler for loadDataButton
        loadDataButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loadData();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // Action handler for logoutButton
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run(); // Execute logout handler
            }
        });

        // Get the logged-in username
        //Text message = getTheLoginUsername();
        VBox vBox = new VBox(10, groupBox, projectBox, leaderNameBox, mobBox, mem1Box, mem2Box, mem3Box, buttonBox, loadButtonBox, tableView, dataMsg, logoutButton);
        vBox.setAlignment(Pos.CENTER); // Center align the VBox
        return vBox;
    }

    // Method to load data from Firestore into TableView
    private void loadData() throws ExecutionException, InterruptedException {
        List<DocumentSnapshot> documents = (List<DocumentSnapshot>) dataService.getData("collectionName", null);
        dataList.clear(); // Clear existing data in the list
        for (DocumentSnapshot doc : documents) {
            if (doc.exists()) {
                // Create ProjectData object from Firestore document
                ProjectData projectData = new ProjectData(
                        doc.getString("groupName"),
                        doc.getString("projectName"),
                        doc.getString("leaderName"),
                        doc.getString("mobileNum"),
                        doc.getString("member1"),
                        doc.getString("member2"),
                        doc.getString("member3")
                );
                dataList.add(projectData); // Add to the observable list
            }
        }
    }

    // Method to get the logged-in username
    // private Text getTheLoginUsername() {
    //     LoginController loginController = new LoginController();
    //     String username = loginController.getLoginUserName();
    //     return new Text("Welcome, " + username);
    // }

    // Inner class representing project data for TableView
    public static class ProjectData {
        private String groupName;
        private String projectName;
        private String leaderName;
        private String mobileNumber;
        private String member1;
        private String member2;
        private String member3;

        public ProjectData(String groupName, String projectName, String leaderName, String mobileNumber, String member1, String member2, String member3) {
            this.groupName = groupName;
            this.projectName = projectName;
            this.leaderName = leaderName;
            this.mobileNumber = mobileNumber;
            this.member1 = member1;
            this.member2 = member2;
            this.member3 = member3;
        }

        // Getters and setters for each field
        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getLeaderName() {
            return leaderName;
        }

        public void setLeaderName(String leaderName) {
            this.leaderName = leaderName;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getMember1() {
            return member1;
        }

        public void setMember1(String member1) {
            this.member1 = member1;
        }

        public String getMember2() {
            return member2;
        }

        public void setMember2(String member2) {
            this.member2 = member2;
        }

        public String getMember3() {
            return member3;
        }

        public void setMember3(String member3) {
            this.member3 = member3;
        }
    }
}
