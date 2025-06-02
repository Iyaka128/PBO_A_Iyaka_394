package users;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static List<Student> sampleStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Budi", "12345"));
        return list;
    }

    public static List<Admin> sampleAdmins() {
        List<Admin> list = new ArrayList<>();
        list.add(new Admin("admin", "admin123"));
        return list;
    }
}
