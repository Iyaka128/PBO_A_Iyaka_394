package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.util.Optional;

public class LoginPage {
    private LibraryManager manager;
    private Stage stage;

    public LoginPage(LibraryManager manager, Stage stage) {
        this.manager = manager;
        this.stage = stage;
    }

    public Pane getView() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f7fafc;");

        Label title = new Label("📚 Welcome to MyLibrary");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2d3748;");

        TextField userField = new TextField();
        userField.setPromptText("Enter Member ID");
        userField.setMaxWidth(250);

        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter Password");
        passField.setMaxWidth(250);

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold;");
        loginBtn.setMaxWidth(250);

        Button registerBtn = new Button("Register");
        registerBtn.setStyle("-fx-background-color: #38b2ac; -fx-text-fill: white; -fx-font-weight: bold;");
        registerBtn.setMaxWidth(250);

        // 🟢 Login Logic
        loginBtn.setOnAction(e -> {
            String id = userField.getText().trim();
            String pass = passField.getText().trim();

            // Cek admin
            if (id.equals("admin") && pass.equals("admin123")) {
                AdminPage adminPage = new AdminPage(manager, stage);
                stage.setScene(new Scene(adminPage.getView(), 800, 600));
                return;
            }

            // Cek member
            Optional<Member> match = manager.getMembers().stream()
                    .filter(m -> m.getId().equals(id) && m.getPassword().equals(pass))
                    .findFirst();

            if (match.isPresent()) {
                MahasiswaPage mhs = new MahasiswaPage(manager, stage, match.get());
                stage.setScene(new Scene(mhs.getView(), 800, 600));
            } else {
                showAlert("Login Failed", "Invalid ID or Password.");
            }
        });

        registerBtn.setOnAction(e -> showRegisterForm());

        root.getChildren().addAll(title, userField, passField, loginBtn, registerBtn);
        root.setSpacing(12);
        root.setPrefSize(400, 300);
        root.setStyle("-fx-alignment: center;");

        return root;
    }

    private void showRegisterForm() {
        Stage regStage = new Stage();
        regStage.setTitle("Register New Member");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField majorField = new TextField();
        majorField.setPromptText("Major");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Button submitBtn = new Button("Register");
        submitBtn.setStyle("-fx-background-color: #2b6cb0; -fx-text-fill: white; -fx-font-weight: bold;");

        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String major = majorField.getText().trim();
            String email = emailField.getText().trim();
            String pass = passField.getText().trim();

            if (name.isEmpty() || major.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            String newId = "M" + String.format("%03d", manager.getMembers().size() + 1);
            Member newMember = new Member(newId, name, major, email, pass);
            manager.addMember(newMember);
            CSVHelper.writeMembersToCSV("members.csv", manager.getMembers());
            showAlert("Success", "Registration successful!\nYour ID: " + newId);
            regStage.close();
        });

        layout.getChildren().addAll(
                new Label("Register New Member"),
                nameField, majorField, emailField, passField, submitBtn
        );

        Scene regScene = new Scene(layout, 300, 300);
        regStage.setScene(regScene);
        regStage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
