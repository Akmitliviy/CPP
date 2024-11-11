import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserProfileAdapter extends TypeAdapter<UserProfile> {

    @Override
    public void write(JsonWriter out, UserProfile userProfile) throws IOException {
        out.beginObject();

        out.name("ID").value(userProfile.getID().toString());
        out.name("name").value(userProfile.getName());
        out.name("surname").value(userProfile.getSurname());

        // Explicitly serialize transient fields
        if (userProfile.getEmail() != null) {
            out.name("email").value(userProfile.getEmail());
        }else {
            out.name("email").nullValue();
        }
        if (userProfile.getMessages() != null) {
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
        UUID ID = null;
        List<Message> messages = null;

        while (in.hasNext()) {
            String fieldName = in.nextName();
            switch (fieldName) {
                case "ID":
                    ID = UUID.fromString(in.nextString());
                    break;
                case "name":
                    name = in.nextString();
                    break;
                case "surname":
                    surname = in.nextString();
                    break;
//                case "messages":
//                    messages = readMessages(in);  // Helper method to read messages array
//                    break;
                default:
                    in.skipValue();  // Skip any fields not handled
                    break;
            }
        }

        in.endObject();
        return new UserProfile(name, surname, null, messages);  // `email` is not deserialized
    }

    private List<Message> readMessages(JsonReader in) throws IOException {
        in.beginArray();
        List<Message> messages = new ArrayList<>();


        return null;  // Replace with actual deserialization logic for messages
    }
}
