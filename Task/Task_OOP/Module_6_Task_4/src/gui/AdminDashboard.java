package gui;

import data.DataStore;
import data.Items;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import users.Student;

public class AdminDashboard extends VBox {
    private MainApp mainApp;
    private String username;

    private ObservableList<Items> laporanItems = FXCollections.observableArrayList();
    private ObservableList<Student> mahasiswaList = FXCollections.observableArrayList();

    public AdminDashboard(String username, MainApp mainApp) {
        this.mainApp = mainApp;
        this.username = username;

        Label greeting = new Label("Selamat datang, Admin " + username + "!");
        greeting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Table laporan barang
        TableView<Items> laporanTable = new TableView<>();
        TableColumn<Items, String> nameCol = new TableColumn<>("Nama");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Items, String> locCol = new TableColumn<>("Lokasi");
        locCol.setCellValueFactory(data -> data.getValue().locationProperty());

        TableColumn<Items, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());

        laporanTable.getColumns().addAll(nameCol, locCol, statusCol);
        laporanTable.setItems(laporanItems);

        // Dummy item
        laporanItems.add(new Items("Dompet", "Coklat kulit", "ICT", "Reported"));

        Button statusButton = new Button("Tandai Claimed");
        statusButton.setOnAction(e -> {
            Items selected = laporanTable.getSelectionModel().getSelectedItem();
            if (selected != null) selected.setStatus("Claimed");
        });

        // Table data mahasiswa
        TableView<Student> studentTable = new TableView<>();
        TableColumn<Student, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Student, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(data -> data.getValue().nimProperty());

        studentTable.getColumns().addAll(namaCol, nimCol);
        mahasiswaList.addAll(DataStore.getStudents());
        studentTable.setItems(mahasiswaList);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama");

        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(e -> {
            Student s = new Student(namaField.getText(), nimField.getText());
            DataStore.addStudent(s);
            mahasiswaList.add(s);
            namaField.clear();
            nimField.clear();
        });

        Button hapusButton = new Button("Hapus");
        hapusButton.setOnAction(e -> {
            Student selected = studentTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.removeStudent(selected);
                mahasiswaList.remove(selected);
            }
        });

        HBox inputBox = new HBox(10, namaField, nimField, tambahButton, hapusButton);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.showLoginPane());

        setPadding(new Insets(15));
        setSpacing(20);
        getChildren().addAll(greeting, laporanTable, statusButton, new Label("Data Mahasiswa:"), studentTable, inputBox, logoutButton);
    }
}
