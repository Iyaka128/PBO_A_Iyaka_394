package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import java.util.Scanner;

public class Student extends User implements MahasiswaActions {
    public Student(String name, String studentId) {
        super(name, studentId);
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan Nama Mahasiswa: ");
        String inputName = scanner.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String inputId = scanner.nextLine();
        return inputName.equals(getName()) && inputId.equals(getStudentId());
    }

    @Override
    public void displayAppMenu(Scanner scanner) {
        String choice;
        do {
            System.out.println("\n=== Mahasiswa Menu ===");
            System.out.println("1. Report Found/Lost Items");
            System.out.println("2. View Report List");
            System.out.println("0. Logout");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    reportItem();
                    break;
                case "2":
                    viewReportedItems();
                    break;
                case "0":
                    System.out.println("Logout berhasil.");
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        } while (!choice.equals("0"));
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Barang: ");
        String itemName = scanner.nextLine();
        System.out.print("Deskripsi Barang: ");
        String itemDesc = scanner.nextLine();
        System.out.print("Lokasi Terakhir / Ditemukan: ");
        String location = scanner.nextLine();
        System.out.println("Barang berhasil dilaporkan: " + itemName);
    }

    @Override
    public void viewReportedItems() {
        System.out.println(">> View Report Feature Not Available <<");
    }
}