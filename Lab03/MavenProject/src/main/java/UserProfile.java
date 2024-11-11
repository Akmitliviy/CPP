import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private final UUID ID = UUID.randomUUID();
    @Expose
    private String name;
    @Expose
    private String surname;
    @Expose
    private transient String email;
    @Expose
    private transient List<Message>  messages;

    UserProfile(String name, String surname, String email, List<Message> messages) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.messages = messages;
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
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
