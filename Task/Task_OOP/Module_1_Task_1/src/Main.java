import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String validStudentName = "Azril Kucai";
        String validStudentID = "202310370311069";
        String lastThreeDigits = validStudentID.substring(validStudentID.length() - 3);

        String validAdminUsername = "Admin" + lastThreeDigits;
        String validAdminPassword = "Password" + lastThreeDigits;

        System.out.println("Select Login Type:");
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: // Untuk Admin
                System.out.print("Enter Admin Username: ");
                String adminUsername = scanner.nextLine();
                System.out.print("Enter Admin Password: ");
                String adminPassword = scanner.nextLine();

                if (adminUsername.equals(validAdminUsername) && adminPassword.equals(validAdminPassword)) {
                    System.out.println("\nAdmin login successful!");
                } else {
                    System.out.println("\nLogin failed! Wrong username or password.");
                }
                break;

            case 2: // Untuk si Kucai
                System.out.print("Enter Name: ");
                String studentName = scanner.nextLine();
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();

                if (studentName.equals(validStudentName) && studentID.equals(validStudentID)) {
                    System.out.println("\nStudent login successful!");
                    System.out.println("Name: " + studentName);
                    System.out.println("Student ID: " + studentID);
                } else {
                    System.out.println("\nLogin failed! Wrong name or student ID.");
                }
                break;

            default:
                System.out.println("\nInvalid choice.");
                break;
        }

        scanner.close();
    }
}