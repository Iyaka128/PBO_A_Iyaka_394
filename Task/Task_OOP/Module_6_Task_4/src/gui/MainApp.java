package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Student;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        showLoginPane();
        primaryStage.setTitle("Sistem Login");
        primaryStage.show();
    }

    public void setSceneContent(javafx.scene.Parent root) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(root, 800, 500);
            primaryStage.setScene(scene);
        } else {
            scene.setRoot(root);
        }
    }

    public void showLoginPane() {
        LoginPane loginPane = new LoginPane(this);
        setSceneContent(loginPane);
    }

    public void showStudentDashboard(Student student) {
        StudentDashboard studentDashboard = new StudentDashboard(student, this);
        setSceneContent(studentDashboard);
    }

    public void showAdminDashboard(String username) {
        AdminDashboard adminDashboard = new AdminDashboard(username, this);
        setSceneContent(adminDashboard);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
