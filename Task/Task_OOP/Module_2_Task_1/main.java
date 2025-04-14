import java.util.Scanner;

public class main {
    // Admin
    static class Admin {
        private final String username = "admin";
        private final String password = "admin123";

        public boolean login(Scanner scanner) {
            System.out.print("Masukkan Username Admin: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Masukkan Password Admin: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(username) && inputPassword.equals(password)) {
                System.out.println("Login Admin berhasil!");
                return true;
            } else {
                System.out.println("Login Admin gagal. Username atau Password salah.");
                return false;
            }
        }
    }


    
    // Mahasiswa
    static class Student {
        private final String name = "John Doe";
        private final String studentId = "123456";

        public boolean login(Scanner scanner) {
            System.out.print("Masukkan Nama Mahasiswa: ");
            String inputName = scanner.nextLine();
            System.out.print("Masukkan NIM Mahasiswa: ");
            String inputId = scanner.nextLine();

            if (inputName.equals(name) && inputId.equals(studentId)) {
                System.out.println("Login Mahasiswa berhasil!");
                displayInfo();
                return true;
            } else {
                System.out.println("Login Mahasiswa gagal. Nama atau NIM salah.");
                return false;
            }
        }

        public void displayInfo() {
            System.out.println("Nama Mahasiswa: " + name);
            System.out.println("NIM: " + studentId);
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Student student = new Student();

        System.out.println("=== Sistem Login ===");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Mahasiswa");
        System.out.print("Pilih opsi (1/2): ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            admin.login(scanner);
        } else if (choice.equals("2")) {
            student.login(scanner);
        } else {
            System.out.println("Opsi tidak valid.");
        }

        scanner.close();
    }
}
