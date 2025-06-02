package users;

import javafx.beans.value.ObservableValue;

public abstract class User {
    protected String name;
    public String getName() { return name; }

    public abstract ObservableValue<String> nameProperty();

    public abstract ObservableValue<String> nimProperty();
}