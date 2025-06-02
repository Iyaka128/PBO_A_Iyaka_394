package users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private StringProperty name;
    private StringProperty nim;

    public Student(String name, String nim) {
        this.name = new SimpleStringProperty(name);
        this.nim = new SimpleStringProperty(nim);
    }

    public String getName() {
        return name.get();
    }

    public String getNim() {
        return nim.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setNim(String nim) {
        this.nim.set(nim);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty nimProperty() {
        return nim;
    }
}
