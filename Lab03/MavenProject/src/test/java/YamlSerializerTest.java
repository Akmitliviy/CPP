import Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlSerializerTest {

    private UserProfile userProfile;
    private static final String FILE_PATH = "YamlFile.yaml";

    @BeforeEach
    void setUp() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello", "This is the first message", LocalDateTime.now()));
        userProfile = new UserProfile("John", "Doe", "john.doe@example.com", messages);
    }

    @Test
    void testSerialization() throws IOException {
        // Serialize UserProfile to YAML
        YamlSerializer.serialize(userProfile, FILE_PATH);

        // Check that file is created and has content
        String yamlContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(FILE_PATH)));
        assertTrue(yamlContent.contains("John"), "Name should be serialized");
        assertTrue(yamlContent.contains("Doe"), "Surname should be serialized");
        assertTrue(yamlContent.contains("Hello"), "Messages should be serialized");
    }

    @Test
    void testDeserialization() throws IOException {
        // Serialize and then Deserialize UserProfile
        YamlSerializer.serialize(userProfile, FILE_PATH);
        UserProfile deserializedUserProfile = YamlSerializer.deserialize(FILE_PATH, UserProfile.class);

        // Check deserialized data
        assertEquals(userProfile.getName(), deserializedUserProfile.getName());
        assertEquals(userProfile.getSurname(), deserializedUserProfile.getSurname());

        // Check that date is skipped in messages after deserialization
        assertNotNull(deserializedUserProfile.getMessages());
        assertEquals(1, deserializedUserProfile.getMessages().size());
        assertNull(deserializedUserProfile.getMessages().get(0).getDate(), "Date should be skipped in deserialization");
    }
}
