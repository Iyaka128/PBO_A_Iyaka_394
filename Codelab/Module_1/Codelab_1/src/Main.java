import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String firstname;
        int age;
        Scanner scanner = new Scanner(System.in);


        System.out.print("Masukkan nama: ");
        firstname = scanner.nextLine();
        System.out.print("Masukkan Jenis Kelamin (P/L): ");
        char jenisKelamin = scanner.next().charAt(0);
        System.out.print("Masukkan Tahun Lahir: ");
        int tahunLahir = scanner.nextInt();


        int tahunSekarang = LocalDate.now().getYear();
        int umur = tahunSekarang - tahunLahir;


        String jenisKelaminStr;
        if (jenisKelamin == 'L' || jenisKelamin == 'l') {
            jenisKelaminStr = "Laki-Laki";
        } else if (jenisKelamin == 'P' || jenisKelamin == 'p') {
            jenisKelaminStr = "Perempuan";
        } else {
            jenisKelaminStr = "Tidak Diketahui";
        }

        System.out.println("\nData Diri:");
        System.out.println("Nama              : " + firstname);
        System.out.println("Jenis Kelamin     : " + jenisKelaminStr);
        System.out.println("Umur              : " + umur + " Tahun");
    }
}

// NAMA : Iyaka Samanda Caysar
// NIM  : 202410370110394
