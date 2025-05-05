package com.praktikum.main;

import com.praktikum.users.Admin;
import com.praktikum.users.Student;
import com.praktikum.users.User;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("Maharani", "202310370311129", "admin", "admin123");
        Student student = new Student("Amelia", "202310370311307");

        System.out.println("=== Sistem Login ===");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Mahasiswa");
        System.out.print("Pilih opsi (1/2): ");
        String choice = scanner.nextLine();

        User currentUser = null;
        boolean loginSuccess = false;

        switch (choice) {
            case "1":
                loginSuccess = admin.login(scanner);
                if (loginSuccess) {
                    currentUser = admin;
                    System.out.println("Login Admin berhasil.");
                } else {
                    System.out.println("Login Admin gagal. Username atau password salah.");
                }
                break;
            case "2":
                loginSuccess = student.login(scanner);
                if (loginSuccess) {
                    currentUser = student;
                    System.out.println("Login Mahasiswa berhasil.");
                } else {
                    System.out.println("Login Mahasiswa gagal. Nama atau NIM salah.");
                }
                break;
            default:
                System.out.println("Opsi tidak valid.");
        }

        if (loginSuccess && currentUser != null) {
            currentUser.displayAppMenu(scanner);
        }

        scanner.close();
    }
}