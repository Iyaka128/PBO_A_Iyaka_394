class Animal {
    // Atribut kelas hewan
    String nama;
    String tipe;
    String suara;

    // Konstruktor = Digunakan untuk menginisialisasi objek saat dibuat.
    Animal(String nama, String tipe, String suara) {
        this.nama = nama;
        this.tipe = tipe;
        this.suara = suara;
    }

    // Metode menampilkan informasi hewan
    void displayInfo() { // DisplayInfo tidak dapat mengembalikan nilai
        System.out.println("Nama: " + nama); // Tambah line baru setelah output
        System.out.println("Jenis: " + tipe);
        System.out.println("Suara: " + suara);
        System.out.println(); // Tidak menambahkan line baru
    }
}

public class Main { // Fungsi utama
    public static void main(String[] args) { // titik masuk tanpa nilai pengembalian
        // Buat dua objek Hewan
        Animal hewan1 = new Animal("Kucing", "Mamalia", "Nyann~~");
        Animal hewan2 = new Animal("Anjing", "Mamalia", "Woof-Woof!!");

        // Panggil metode displayInfo() untuk kedua objek
        hewan1.displayInfo(); //print rincian objek hewan.
        hewan2.displayInfo();
    }
}

// Iyaka Samanda Caysar
// 202410370110394