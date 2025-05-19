package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
import java.util.Iterator;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String name, String studentId) {
        super(name, studentId);
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan Nama: ");
        String inputName = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String inputNim = scanner.nextLine();
        return getName().equalsIgnoreCase(inputName) && getStudentId().equals(inputNim);
    }

    @Override
    public void displayAppMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- Menu Mahasiswa ---");
            System.out.println("1. Laporkan barang");
            System.out.println("2. Lihat laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> reportItem(scanner);
                    case 2 -> viewReportedItems();
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
    public void reportItem(Scanner scanner) {
        System.out.print("Nama barang: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String desc = scanner.nextLine();
        System.out.print("Lokasi ditemukan: ");
        String loc = scanner.nextLine();

        Item item = new Item(name, desc, loc);
        LoginSystem.reportedItems.add(item);
        System.out.println("Laporan berhasil disimpan.");
    }

    @Override
    public void viewReportedItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println(">> Belum ada barang yang dilaporkan.");
            return;
        }

        System.out.println("=== Daftar Barang yang Dilaporkan ===");
        Iterator<Item> it = LoginSystem.reportedItems.iterator();

        while (it.hasNext()) {
            Item item = it.next();
            if (item.getStatus().equalsIgnoreCase("Reported")) {
                System.out.println("- Nama Barang: " + item.getItemName());
                System.out.println("  Deskripsi   : " + item.getDescription());
                System.out.println("  Lokasi      : " + item.getLocation());
                System.out.println("  Status      : " + item.getStatus());
                System.out.println("----------------------------------");
            }
        }
    }

}
