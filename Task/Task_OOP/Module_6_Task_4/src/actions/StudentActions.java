package actions;

import data.DataStore;
import users.Student;

public class StudentActions {
    public static boolean validateStudent(String name, String nim) {
        return DataStore.findStudent(name, nim) != null;
    }

    public static void addStudent(String name, String nim) {
        DataStore.addStudent(new Student(name, nim));
    }
}
