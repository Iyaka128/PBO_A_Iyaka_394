package data;

import users.Admin;
import users.Student;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static List<Student> students = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();

    static {
        // Data Mahasiswa dummy
        students.add(new Student("Budi", "123456"));
        students.add(new Student("Siti", "654321"));

        // Admin dummy
        admins.add(new Admin("admin", "admin123"));
    }

    public static Student findStudent(String name, String nim) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name) && s.getNim().equals(nim)) {
                return s;
            }
        }
        return null;
    }

    public static boolean validateAdmin(String username, String password) {
        for (Admin a : admins) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void addStudent(Student s) {
        students.add(s);
    }

    public static void removeStudent(Student s) {
        students.remove(s);
    }
}
