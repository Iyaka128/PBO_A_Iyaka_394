package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import java.util.Optional;

public class AdminPage {
    private BorderPane root;
    private LibraryManager manager;
    private Stage stage;
    private TableView<Book> bookTable;
    private TableView<Transaction> txTable;

    public AdminPage(LibraryManager manager, Stage stage) {
        this.manager = manager;
        this.stage = stage;
        createUI();
    }

    public BorderPane getView() {
        return root;
    }

    private void createUI() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #fffde7, #e0f7fa);");

        // Header
        Label header = new Label("Admin Dashboard");
        header.setFont(new Font("Arial Rounded MT Bold", 26));
        header.setPadding(new Insets(20));
        root.setTop(header);

        // Center - Book Table + Buttons
        VBox center = new VBox(15);
        center.setPadding(new Insets(15));

        Label bookLabel = new Label("Book List");
        bookLabel.setFont(Font.font(16));

        bookTable = new TableView<>();
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTable.setPrefHeight(200);

        bookTable.getColumns().addAll(
                col("ISBN", b -> b.getIsbn()),
                col("Title", b -> b.getTitle()),
                col("Author", b -> b.getAuthor()),
                col("Qty", b -> String.valueOf(b.getQuantity()))
        );

        refreshBookTable();

        HBox bookButtons = new HBox(10);
        bookButtons.setAlignment(Pos.CENTER);
        Button editBtn = new Button("Edit Book");
        Button deleteBtn = new Button("Delete Book");
        Button exportBooksBtn = new Button("Export Books");

        editBtn.setOnAction(e -> editBook());
        deleteBtn.setOnAction(e -> deleteBook());
        exportBooksBtn.setOnAction(e -> {
            CSVHelper.writeBooksToCSV("books.csv", manager.getBooks());
            alert("Export Success", "Books exported to books.csv.");
        });

        bookButtons.getChildren().addAll(editBtn, deleteBtn, exportBooksBtn);

        // Transaction Table
        Label txLabel = new Label("Transactions");
        txLabel.setFont(Font.font(16));

        txTable = new TableView<>();
        txTable.setPrefHeight(200);
        txTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        txTable.getColumns().addAll(
                col("ID", t -> t.getId()),
                col("Member", t -> t.getMemberId()),
                col("ISBN", t -> t.getIsbn()),
                col("Borrow Date", t -> t.getBorrowDate()),
                col("Return Date", t -> t.getReturnDate()),
                col("Status", t -> t.getStatus())
        );

        refreshTransactionTable();

        HBox txButtons = new HBox(10);
        txButtons.setAlignment(Pos.CENTER);
        Button markReturnedBtn = new Button("Mark Returned");
        Button exportTxBtn = new Button("Export Transactions");

        markReturnedBtn.setOnAction(e -> markTransactionReturned());
        exportTxBtn.setOnAction(e -> {
            CSVHelper.writeTransactionsToCSV("transactions.csv", manager.getTransactions());
            alert("Export Success", "Transactions exported to transactions.csv.");
        });

        txButtons.getChildren().addAll(markReturnedBtn, exportTxBtn);

        center.getChildren().addAll(bookLabel, bookTable, bookButtons, new Separator(), txLabel, txTable, txButtons);
        root.setCenter(center);

        // Footer - Logout
        Button logoutBtn = new Button("Logout");
        logoutBtn.setFont(Font.font(14));
        logoutBtn.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white;");
        logoutBtn.setOnAction(e -> {
            LoginPage login = new LoginPage(manager, stage);
            stage.setScene(new Scene(login.getView(), 600, 400));
        });

        HBox footer = new HBox(logoutBtn);
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.setPadding(new Insets(10));
        root.setBottom(footer);
    }

    // Helpers
    private <T> TableColumn<T, String> col(String title, java.util.function.Function<T, String> mapper) {
        TableColumn<T, String> col = new TableColumn<>(title);
        col.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(mapper.apply(data.getValue())));
        return col;
    }

    private void refreshBookTable() {
        ObservableList<Book> list = FXCollections.observableArrayList(manager.getBooks());
        bookTable.setItems(list);
    }

    private void refreshTransactionTable() {
        ObservableList<Transaction> list = FXCollections.observableArrayList(manager.getTransactions());
        txTable.setItems(list);
    }

    private void alert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void editBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("No Selection", "Please select a book.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(String.valueOf(selected.getQuantity()));
        dialog.setTitle("Edit Book Quantity");
        dialog.setHeaderText("Editing: " + selected.getTitle());
        dialog.setContentText("New Quantity:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(qtyStr -> {
            try {
                int newQty = Integer.parseInt(qtyStr);
                selected.setQuantity(newQty);
                refreshBookTable();
            } catch (NumberFormatException e) {
                alert("Error", "Invalid number.");
            }
        });
    }

    private void deleteBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("No Selection", "Please select a book.");
            return;
        }

        manager.getBooks().remove(selected);
        refreshBookTable();
    }

    private void markTransactionReturned() {
        Transaction tx = txTable.getSelectionModel().getSelectedItem();
        if (tx == null) {
            alert("No Selection", "Please select a transaction.");
            return;
        }

        tx.setStatus("Returned");
        refreshTransactionTable();
    }
}
