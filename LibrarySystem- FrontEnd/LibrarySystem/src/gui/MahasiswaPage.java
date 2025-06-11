package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;
import model.LibraryManager;
import model.Member;

public class MahasiswaPage {
    private BorderPane root;
    private LibraryManager manager;
    private Member member;
    private Stage stage;

    public MahasiswaPage(LibraryManager manager, Stage stage, Member member) {
        this.manager = manager;
        this.member = member;
        this.stage = stage;
        createUI();
    }

    public BorderPane getView() {
        return root;
    }

    private void createUI() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f4ff, #d9e7ff);");

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER_LEFT);
        Label welcome = new Label("Welcome, " + member.getName());
        welcome.setFont(new Font("Arial Rounded MT Bold", 20));
        welcome.setTextFill(Color.web("#2b6cb0"));
        header.getChildren().add(welcome);
        root.setTop(header);

        // Center - List of Books
        VBox centerContent = new VBox(10);
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.TOP_CENTER);

        Label bookLabel = new Label("Available Books");
        bookLabel.setFont(new Font("Arial", 18));
        bookLabel.setTextFill(Color.DARKBLUE);

        TableView<Book> bookTable = new TableView<>();
        bookTable.setPrefHeight(400);
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIsbn()));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));

        TableColumn<Book, String> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getQuantity())));

        bookTable.getColumns().addAll(isbnCol, titleCol, authorCol, qtyCol);

        ObservableList<Book> books = FXCollections.observableArrayList(manager.getBooks());
        bookTable.setItems(books);

        centerContent.getChildren().addAll(bookLabel, bookTable);
        root.setCenter(centerContent);

        // Footer - Logout button
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER_RIGHT);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setFont(Font.font(14));
        logoutBtn.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white; -fx-background-radius: 10;");
        logoutBtn.setOnAction(e -> {
            LoginPage loginPage = new LoginPage(manager, stage);
            stage.setScene(new Scene(loginPage.getView(), 600, 400));
        });

        footer.getChildren().add(logoutBtn);
        root.setBottom(footer);
    }
}
