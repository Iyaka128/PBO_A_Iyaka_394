import java.util.Scanner;

class User { 
    private String name;
    private String studentId;

    public User(String name, String studentId) { // Construct nya
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean login(Scanner scanner) {
        System.out.println("Login method from User");
        return false;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
    }
}

class Admin extends User { // Si Admin
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

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        System.out.println("Selamat datang, Admin " + getName());
    }
}

// Untuk Student
class Student extends User {
    public Student(String name, String studentId) {
        super(name, studentId);
    }

    @Override
    public boolean login(Scanner scanner) {
        System.out.print("Masukkan Nama Mahasiswa: ");
        String inputName = scanner.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String inputId = scanner.nextLine();

        if (inputName.equals(getName()) && inputId.equals(getStudentId())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        System.out.println("Nama: " + getName());
        System.out.println("NIM: " + getStudentId());
    }
}



// Main class
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("Maharani", "202310370311129", "admin", "admin123");
        Student student = new Student("Amelia", "202310370311307");

        System.out.println("=== Sistem Login ===");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Mahasiswa");
        System.out.print("Pilih opsi (1/2): ");
        String choice = scanner.nextLine();

        boolean loginSuccess = false;

        switch (choice) {
            case "1":
                loginSuccess = admin.login(scanner);
                if (loginSuccess) {
                    admin.displayInfo();
                } else {
                    System.out.println("Login Admin gagal. Username atau password salah.");
                }
                break;

            case "2":
                loginSuccess = student.login(scanner);
                if (loginSuccess) {
                    student.displayInfo();
                } else {
                    System.out.println("Login Mahasiswa gagal. Nama atau NIM salah.");
                }
                break;

            default:
                System.out.println("Opsi tidak valid.");
                break;
        }

        scanner.close();
    }
}
