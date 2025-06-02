package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Items {
    private StringProperty name;
    private StringProperty desc;
    private StringProperty location;
    private StringProperty status;
    private StringProperty contact;

    public Items(String name, String desc, String location) {
        this(name, desc, location, "Reported");
    }

    public Items(String name, String desc, String location, String status) {
        this.name = new SimpleStringProperty(name);
        this.desc = new SimpleStringProperty(desc);
        this.location = new SimpleStringProperty(location);
        this.status = new SimpleStringProperty(status);
        this.contact = new SimpleStringProperty(contact);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty descProperty() { return desc; }
    public StringProperty locationProperty() { return location; }
    public StringProperty statusProperty() { return status; }

    public StringProperty contactProperty() {
        return contact;
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public void setStatus(String newStatus) {
        status.set(newStatus);
    }
}
