package com.praktikum.users;

import java.util.Scanner;

public abstract class User {
    private String name;
    private String studentId;

    public User(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public abstract boolean login(Scanner scanner);
    public abstract void displayAppMenu(Scanner scanner);
}
