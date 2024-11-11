import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private String topic;
    @Expose
    private String message;
    @Expose
    private LocalDateTime date;

    public Message(String topic, String message, LocalDateTime date) {
        this.topic = topic;
        this.message = message;
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return topic + ":\n\n" + message + "\n\ndate: " + date.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(topic, message1.topic) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(date, message1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, message, date);
    }
}
