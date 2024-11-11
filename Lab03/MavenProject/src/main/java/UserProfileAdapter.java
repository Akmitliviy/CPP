import Entities.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserProfileAdapter extends TypeAdapter<UserProfile> {

    @Override
    public void write(JsonWriter out, UserProfile userProfile) throws IOException {
        out.beginObject();

        out.name("ID").value(userProfile.getID());
        out.name("name").value(userProfile.getName());
        out.name("surname").value(userProfile.getSurname());

        // Explicitly serialize transient fields
        if (userProfile.getEmail() != null) {
            out.name("email").value(userProfile.getEmail());
        }else {
            out.name("email").nullValue();
        }
        if (userProfile.getMessages() != null) {
            out.name("messagesCount").value(userProfile.getMessages().size());
            out.name("messages").beginArray();
            for (Message message : userProfile.getMessages()) {
                out.beginObject();
                out.name("topic").value(message.getTopic());
                out.name("message").value(message.getMessage());
                out.name("date").value(message.getDate().toString());
                out.endObject();
            }
            out.endArray();
        } else {
            out.name("messages").nullValue();
        }

        out.endObject();
    }

    @Override
    public UserProfile read(JsonReader in) throws IOException {
        // Basic deserialization logic (skipping the `email` field)
        in.beginObject();

        String name = null;
        String surname = null;
        String ID = null;
        List<Message> messages = null;
        int messagesCount = 0;

        while (in.hasNext()) {
            String fieldName = in.nextName();
            switch (fieldName) {
                case "ID":
                    ID = in.nextString();
                    break;
                case "name":
                    name = in.nextString();
                    break;
                case "surname":
                    surname = in.nextString();
                    break;
                case "messagesCount":
                    messagesCount = in.nextInt();
                    break;
                case "messages":
                    messages = readMessages(in, messagesCount);  // Helper method to read messages array
                    break;
                default:
                    in.skipValue();  // Skip any fields not handled
                    break;
            }
        }

        in.endObject();
        return new UserProfile(name, surname, null, messages);  // `email` is not deserialized
    }

    private List<Message> readMessages(JsonReader in, int messagesCount) throws IOException {
        in.beginArray();
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < messagesCount; i++) {
            in.beginObject();
            String topic = null;
            String message = null;
            LocalDateTime date = null;

            while(topic == null || message == null || date == null) {
                switch (in.nextName()) {
                    case "topic":
                        topic = in.nextString();
                        break;
                    case "message":
                        message = in.nextString();
                        break;
                    case "date":
                        date = LocalDateTime.parse(in.nextString());
                        break;
                    default:
                        in.skipValue();
                }
            }
            in.endObject();

            messages.add(new Message(topic, message, date));
        }
        in.endArray();

        return messages;
    }
}
