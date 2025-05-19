package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;

import java.util.Iterator;
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
        String user = scanner.nextLine();
        System.out.print("Masukkan Password Admin: ");
        String pass = scanner.nextLine();
        return username.equals(user) && password.equals(pass);
    }

    @Override
    public void displayAppMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Lihat semua laporan");
            System.out.println("2. Tandai barang sebagai 'Claimed'");
            System.out.println("3. Tambah Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> manageItems(scanner);
                    case 2 -> manageItems(scanner);
                    case 3 -> manageUsers(scanner);
                    case 4 -> manageUsers(scanner);
                    case 0 -> System.out.println("Logout berhasil.");
                    default -> System.out.println("Opsi tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                choice = -1;
            }

        } while (choice != 0);
    }

    @Override
    public void manageItems(Scanner scanner) {
        System.out.println("\n--- Laporan Barang ---");
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan.");
            return;
        }
        for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
            Item item = LoginSystem.reportedItems.get(i);
            System.out.println(i + ". " + item.getItemName() + " - " + item.getDescription() +
                    " - Lokasi: " + item.getLocation() + " - Status: " + item.getStatus());
        }

        System.out.print("Masukkan indeks barang yang ingin ditandai sebagai 'Claimed': ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            Item item = LoginSystem.reportedItems.get(index);
            item.setStatus("Claimed");
            System.out.println("Barang berhasil ditandai sebagai 'Claimed'.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid!");
        }
    }

    @Override
    public void manageUsers(Scanner scanner) {
        System.out.println("\n1. Tambah Mahasiswa\n2. Hapus Mahasiswa");
        String opt = scanner.nextLine();
        if (opt.equals("1")) {
            System.out.print("Nama Mahasiswa: ");
            String name = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            LoginSystem.userList.add(new Mahasiswa(name, nim));
            System.out.println("Mahasiswa ditambahkan.");
        } else if (opt.equals("2")) {
            System.out.print("Masukkan NIM Mahasiswa yang ingin dihapus: ");
            String targetNim = scanner.nextLine();

            boolean found = false;
            Iterator<User> it = LoginSystem.userList.iterator();

            while (it.hasNext()) {
                User u = it.next();
                if (u instanceof Mahasiswa && u.getStudentId().equalsIgnoreCase(targetNim)) {
                    it.remove();
                    System.out.println("Mahasiswa dengan NIM " + targetNim + " berhasil dihapus.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Mahasiswa tidak ditemukan.");
            }

        }
    }
}
