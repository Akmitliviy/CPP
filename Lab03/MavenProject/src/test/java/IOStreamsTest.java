import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class IOStreamsTest {

    private static final String messagesFile = "MessagesFile.bin";
    private static final String userProfileFile = "UserProfileFile.bin";
    private List<Message> messages;
    private UserProfile userProfile;

    @BeforeEach
    void setUp() {
        messages = List.of(
                new Message("Review", "Lorem Ipsum", LocalDateTime.of(2022, Month.JANUARY, 4, 0, 0)),
                new Message("Buzzkills in New York", "I hate buzzkills. They're funny tho.", LocalDateTime.of(2022, Month.JANUARY, 4, 0, 0)),
                new Message("Summary", "I want to finally sum up all the summaries and results and make last conclusion", LocalDateTime.of(2022, Month.JANUARY, 4, 0, 0))
        );

        userProfile = new UserProfile("Andrii", "Kmitliviy", "akmitliviy@gmail.com", messages);
    }

    @Test
    void testWriteMessagesToFile() {

        try {
            IOStreams.writeMessagesToFile(messagesFile, messages);
            assertTrue(true);
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    void testReadMessagesFromFile() {
        List<Message> readMessages = new ArrayList<>();
        try{
            readMessages = IOStreams.readMessagesFromFile(messagesFile);
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e);
            fail();
        }

        assertEquals(readMessages.size(), messages.size());
        for (Message message : readMessages) {
            System.out.println(message + "\n***\n\n");
        }
    }

    @Test
    void testWriteUserProfileToFile() {
        try{
            IOStreams.writeUserProfileToFile(userProfileFile, userProfile);
            assertTrue(true);
        }catch (IOException e){
            System.out.println(e);
            fail();
        }
    }

    @Test
    void testReadUserProfileFromFile() {
        UserProfile readUserProfile = null;
        try{
            readUserProfile = IOStreams.readUserProfileFromFile(userProfileFile);
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e);
            fail();
        }

        assertEquals(readUserProfile.getName(), userProfile.getName());
        assertEquals(readUserProfile.getSurname(), userProfile.getSurname());
        assertNotEquals(readUserProfile.getEmail(), userProfile.getEmail());
        //assertEquals(readUserProfile.getMessages().toString(), messages.toString());

        System.out.println(readUserProfile);

    }
}