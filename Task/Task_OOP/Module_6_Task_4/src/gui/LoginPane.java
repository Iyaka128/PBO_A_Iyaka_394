package gui;

import data.DataStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import users.Student;

public class LoginPane extends VBox {
    private ToggleGroup roleGroup;
    private RadioButton studentRadio;
    private RadioButton adminRadio;

    private TextField nameField;
    private TextField nimField;
    private TextField usernameField;
    private PasswordField passwordField;

    private Button loginButton;
    private Label feedbackLabel;

    private MainApp mainApp;

    public LoginPane(MainApp mainApp) {
        this.mainApp = mainApp;

        // Title
        Label titleLabel = new Label("Login Sistem Lost & Found");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        // Role selection
        roleGroup = new ToggleGroup();
        studentRadio = new RadioButton("Mahasiswa");
        adminRadio = new RadioButton("Admin");
        studentRadio.setToggleGroup(roleGroup);
        adminRadio.setToggleGroup(roleGroup);
        studentRadio.setSelected(true); // default

        HBox roleBox = new HBox(10, studentRadio, adminRadio);
        roleBox.setAlignment(Pos.CENTER);

        // Input untuk mahasiswa
        nameField = new TextField();
        nameField.setPromptText("Nama");

        nimField = new TextField();
        nimField.setPromptText("NIM");

        VBox studentFields = new VBox(10, nameField, nimField);
        studentFields.setAlignment(Pos.CENTER);

        // Input untuk admin
        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        VBox adminFields = new VBox(10, usernameField, passwordField);
        adminFields.setAlignment(Pos.CENTER);
        adminFields.setVisible(false); // default disembunyikan

        // Ganti tampilan input sesuai role
        roleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            boolean isStudent = studentRadio.isSelected();
            studentFields.setVisible(isStudent);
            adminFields.setVisible(!isStudent);
        });

        loginButton = new Button("Login");
        feedbackLabel = new Label();

        loginButton.setOnAction(e -> handleLogin());

        VBox inputFields = new VBox(15, roleBox, studentFields, adminFields, loginButton, feedbackLabel);
        inputFields.setAlignment(Pos.CENTER);

        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, inputFields);
    }

    private void handleLogin() {
        if (studentRadio.isSelected()) {
            String name = nameField.getText().trim();
            String nim = nimField.getText().trim();
            if (name.isEmpty() || nim.isEmpty()) {
                feedbackLabel.setText("Nama dan NIM harus diisi!");
                return;
            }

            Student student = DataStore.findStudent(name, nim);
            if (student != null) {
                mainApp.showStudentDashboard(student);
            } else {
                feedbackLabel.setText("Login Mahasiswa Gagal!");
            }

        } else if (adminRadio.isSelected()) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            if (username.isEmpty() || password.isEmpty()) {
                feedbackLabel.setText("Username dan Password harus diisi!");
                return;
            }

            boolean isValid = DataStore.validateAdmin(username, password);
            if (isValid) {
                mainApp.showAdminDashboard(username);
            } else {
                feedbackLabel.setText("Login Admin Gagal!");
            }
        }
    }
}
