package Entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private String ID = UUID.randomUUID().toString();
    @Expose
    private String name;
    @Expose
    private String surname;
    @Expose
    private String email;
    @Expose
    private List<Message>  messages;

    public UserProfile(String name, String surname, String email, List<Message> messages) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.messages = messages;
    }

    public UserProfile() {}

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Entities.UserProfile{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + (email != null ? email : "N/A") + '\'' +
                ", messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
