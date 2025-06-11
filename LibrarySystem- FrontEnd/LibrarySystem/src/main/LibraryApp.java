package main;

import gui.LoginPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LibraryManager;

public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        LibraryManager manager = new LibraryManager();
        LoginPage loginPage = new LoginPage(manager, primaryStage); // Ganti SimpleView

        Scene scene = new Scene(loginPage.getView(), 600, 400);
        primaryStage.setTitle("MyLibrary - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
