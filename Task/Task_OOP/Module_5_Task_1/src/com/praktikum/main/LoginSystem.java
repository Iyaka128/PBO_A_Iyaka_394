package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        userList.add(new Admin("Admin1", "0001", "admin", "admin123"));
        userList.add(new Mahasiswa("Yaka", "394"));

        System.out.println("=== Sistem Login ===");
        System.out.print("Login sebagai (1. Admin / 2. Mahasiswa): ");
        String option = scanner.nextLine();

        User loggedIn = null;

        for (User u : userList) {
            if (("1".equals(option) && u instanceof Admin) || ("2".equals(option) && u instanceof Mahasiswa)) {
                if (u.login(scanner)) {
                    loggedIn = u;
                    break;
                }
            }
        }

        if (loggedIn != null) {
            System.out.println("Login berhasil: " + loggedIn.getName());
            loggedIn.displayAppMenu(scanner);
        } else {
            System.out.println("Login gagal!");
        }

        scanner.close();
    }
}
