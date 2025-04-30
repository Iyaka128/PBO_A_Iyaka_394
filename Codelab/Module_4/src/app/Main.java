package app;

import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        NonFiksi buku1 = new NonFiksi("Madilog", "Tan Malaka", "Sejarah & Ilmu Pengetahuan");
        Fiksi buku2 = new Fiksi("Hainuwele: Sang Putri Kelapa", "Lilis Hu", "Dongeng");

        // Tampilkan info buku
        buku1.tampilkanInfo();
        buku2.tampilkanInfo();
        System.out.println();

        // Anggota
        Anggota anggota1 = new Anggota("Iyaka", "B394");
        Anggota anggota2 = new Anggota("Fatah", "A393");

        anggota1.tampilkanInfo();
        anggota2.tampilkanInfo();
        System.out.println();

        // Peminjaman
        anggota1.pinjamBuku("Madilog");
        anggota2.pinjamBuku("Hainuwele: Sang Putri Kelapa", 7);
        System.out.println();

        // Pengembalian
        anggota1.kembalikanBuku("Madilog");
        anggota2.kembalikanBuku("Hainuwele: Sang Putri Kelapa");
    }
}
