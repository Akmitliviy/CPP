import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GsonSerializerTest {

    private UserProfile userProfile;
    private static final String FILE_PATH = "GsonFile.json";

    @BeforeEach
    void setUp() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello", "This is the first message", LocalDateTime.now()));
        messages.add(new Message("Buzzkills in New York", "I hate buzzkills. They're funny tho.", LocalDateTime.of(2022, Month.JANUARY, 4, 0, 0)));
        userProfile = new UserProfile("John", "Doe", "john.doe@example.com", messages);
    }

    @Test
    void testSerialization() throws IOException {
        // Serialize user profile to JSON
        GsonSerializer.serialize(userProfile, FILE_PATH);

        // Read the JSON file content as a string for validation
        String jsonContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(FILE_PATH)));

        // Check that email is present in JSON (since we customized serialization to include transient)
        assertTrue(jsonContent.contains("john.doe@example.com"), "Email should be serialized even if transient");
    }

    @Test
    void testDeserialization() throws IOException {
        // Serialize and then Deserialize user profile
        GsonSerializer.serialize(userProfile, FILE_PATH);
        UserProfile deserializedUserProfile = GsonSerializer.deserialize(FILE_PATH, UserProfile.class);

        // Check that deserialized object's email is null (excluded from deserialization)
        assertNull(deserializedUserProfile.getEmail(), "Email should not be deserialized");

        // Check other fields
        assertEquals(userProfile.getName(), deserializedUserProfile.getName());
        assertEquals(userProfile.getSurname(), deserializedUserProfile.getSurname());

        assertEquals(userProfile.getMessages(), deserializedUserProfile.getMessages());
    }
}
