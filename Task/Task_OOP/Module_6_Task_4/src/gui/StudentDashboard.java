package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import users.Student;
import data.Items;

public class StudentDashboard extends VBox {
    private Student student;
    private MainApp mainApp;

    private TextField nameField, descField, locationField;
    private TableView<Items> table;
    private ObservableList<Items> laporanItems;

    public StudentDashboard(Student student, MainApp mainApp) {
        this.student = student;
        this.mainApp = mainApp;
        this.laporanItems = FXCollections.observableArrayList();

        Label greeting = new Label("Halo, " + student.getName() + "!");
        greeting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        nameField = new TextField();
        nameField.setPromptText("Nama Barang");

        descField = new TextField();
        descField.setPromptText("Deskripsi Barang");

        locationField = new TextField();
        locationField.setPromptText("Lokasi Ditemukan");

        TextField contactNumberField = new TextField();
        contactNumberField.setPromptText("Nomor Kontak");

        Button confirmButton = new Button("Konfirmasi");
        confirmButton.setOnAction(e -> addItem());

        HBox inputBox = new HBox(10, nameField, descField, locationField, confirmButton);
        inputBox.setPadding(new Insets(10));

        // Table setup
        table = new TableView<>();
        TableColumn<Items, String> nameCol = new TableColumn<>("Nama");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Items, String> descCol = new TableColumn<>("Deskripsi");
        descCol.setCellValueFactory(data -> data.getValue().descProperty());

        TableColumn<Items, String> locCol = new TableColumn<>("Lokasi");
        locCol.setCellValueFactory(data -> data.getValue().locationProperty());

        table.getColumns().addAll(nameCol, descCol, locCol);
        table.setItems(laporanItems);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.showLoginPane());

        setPadding(new Insets(20));
        setSpacing(15);
        getChildren().addAll(greeting, inputBox, table, logoutButton);
    }

    private void addItem() {
        String name = nameField.getText();
        String desc = descField.getText();
        String location = locationField.getText();
        if (!name.isEmpty() && !desc.isEmpty() && !location.isEmpty()) {
            laporanItems.add(new Items(name, desc, location));
            nameField.clear();
            descField.clear();
            locationField.clear();
        }
    }
}
