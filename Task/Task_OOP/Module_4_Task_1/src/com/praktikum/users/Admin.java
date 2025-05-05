package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String name, String studentId, String username, String password) {
        super(name, studentId);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan Username Admin: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Masukkan Password Admin: ");
        String inputPassword = scanner.nextLine();
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    @Override
    public void displayAppMenu(Scanner scanner) {
        String choice;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Manage Item Reports");
            System.out.println("2. Manage Student Data");
            System.out.println("0. Logout");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageItem();
                    break;
                case "2":
                    manageUsers();
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
    public void manageItem() {
        System.out.println(">> Manage Items feature is not available <<");
    }

    @Override
    public void manageUsers() {
        System.out.println(">> Manage Students feature is not available <<");
    }
}