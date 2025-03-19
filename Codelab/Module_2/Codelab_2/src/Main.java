class BankAccount {
    String accountNumber; // Nomor rekening
    String ownerName; // Nama pemilik rekening
    double balance; // Saldo

    // Konstruktor untuk menginisialisasi atribut akun bank
    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber; // Mengatur nomor rekening
        this.ownerName = ownerName; // Mengatur nama pemilik rekening
        this.balance = balance; // Mengatur saldo awal
    }

    // Metode untuk menampilkan informasi rekening
    public void displayInfo() {
        System.out.println("Nomor Rekening: " + accountNumber); // Menampilkan nomor rekening
        System.out.println("Nama Pemilik: " + ownerName); // Menampilkan nama pemilik rekening
        System.out.println("Saldo: Rp" + balance); // Menampilkan saldo saat ini
        System.out.println(); // Pemisah
    }

    // Menyetor uang ke rekening
    public void depositMoney(double amount) {
        balance += amount; // Menambahkan jumlah yang disetor ke saldo
        System.out.println(ownerName + " menyetor Rp" + amount + ". Saldo Sekarang: Rp" + balance); // Info setoran dan saldo terbaru

    }

    // Menarik uang dari rekening
    public void withdrawMoney(double amount) {
        if (balance >= amount) { // Cek saldo cukup atau tidak
            balance -= amount; // Mengurangi saldo
            System.out.println(ownerName + " menarik Rp" + amount + ". (Berhasil) Saldo Sekarang: Rp" + balance); // Menampilkan berhasil
        } else {
            System.out.println(ownerName + " menarik Rp" + amount + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini : Rp" + balance); // Menampilkan gagal
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Membuat dua objek BankAccount
        BankAccount account1 = new BankAccount("202310370311129", "Maharani", 500000.0); // Akun pertama
        BankAccount account2 = new BankAccount("202310370311307", "Amelia", 1000000.0); // Akun Kedua

        // Menampilkan informasi awal akun
        account1.displayInfo();
        account2.displayInfo();

        // Melakukan transaksi penyetoran
        account1.depositMoney(200000.0);
        account2.depositMoney(500000.0);

        System.out.println();

        // Melakukan transaksi penarikan
        account1.withdrawMoney(8000000.0); // Gagal
        account2.withdrawMoney(300000.0); // Berhasil

        // Menampilkan informasi akhir akun setelah transaksi
        System.out.println();
        account1.displayInfo();
        account2.displayInfo();
    }
}

// Iyaka Samanda Caysar
// 202410370110394
