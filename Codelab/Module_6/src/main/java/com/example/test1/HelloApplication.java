package com.example.test1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {
    private int targetNumber;
    private int attempts;
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Tebak Angka 1–100");
        titleLabel.setFont(new Font("Arial", 30));
        titleLabel.setTextFill(Color.DARKBLUE);


        Label feedbackLabel = new Label("Silakan tebak angka dari 1 hingga 100.");
        feedbackLabel.setFont(new Font("Arial", 16));

        TextField guessField = new TextField();
        guessField.setPromptText("Masukkan Nomor Tebakan");

        Button guessButton = new Button("Coba Tebak!");
        guessButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        HBox inputBox = new HBox(10, guessField, guessButton);
        inputBox.setStyle("-fx-alignment: center;");

        Label attemptLabel = new Label("Jumlah Percobaan: 0");

        // Layout Utama
        VBox root = new VBox(15, titleLabel, feedbackLabel, inputBox, attemptLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #ADD8E6;");


        // Mulai
        startNewGame(guessField, feedbackLabel, attemptLabel, guessButton);

        guessButton.setOnAction(e -> {
            if (guessButton.getText().equals("Coba Lagi!")) {
                startNewGame(guessField, feedbackLabel, attemptLabel, guessButton);
            } else {
                String input = guessField.getText();
                try {
                    int guess = Integer.parseInt(input);
                    attempts++;
                    attemptLabel.setText("Jumlah Percobaan: " + attempts);

                    if (guess < targetNumber) {
                        feedbackLabel.setText("Terlalu kecil!");
                        feedbackLabel.setTextFill(Color.YELLOW);
                    } else if (guess > targetNumber) {
                        feedbackLabel.setText("Terlalu besar!");
                        feedbackLabel.setTextFill(Color.YELLOW);
                    } else {
                        feedbackLabel.setText("Tebakan benar!");
                        feedbackLabel.setTextFill(Color.GREEN);
                        guessButton.setText("Play Again");
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Masukkan angka yang valid!");
                    feedbackLabel.setTextFill(Color.RED);
                }
            }
        });

        // Tampilkan jendela
        primaryStage.setTitle("Tebak Angka");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.show();
    }

    private void startNewGame(TextField guessField, Label feedbackLabel, Label attemptLabel, Button guessButton) {
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        attemptLabel.setText("Jumlah Percobaan: 0");
        feedbackLabel.setText("Silakan tebak angka dari 1 hingga 100.");
        feedbackLabel.setTextFill(Color.BLACK);
        guessField.clear();
        guessButton.setText("Coba Tebak!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
