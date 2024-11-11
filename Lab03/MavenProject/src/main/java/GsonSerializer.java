import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class GsonSerializer {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(UserProfile.class, new UserProfileAdapter())
            .create();

    public static <T> void serialize(T object, String filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        }
    }

    public static <T> T deserialize(String filePath, Class<T> clazz) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, clazz);
        }
    }
}
