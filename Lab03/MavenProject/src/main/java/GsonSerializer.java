import Entities.UserProfile;
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
        Writer writer = new FileWriter(filePath);
        try{
            gson.toJson(object, writer);
        }finally {
            writer.close();
        }
    }

    public static <T> T deserialize(String filePath, Class<T> clazz) throws IOException {
        Reader reader = new FileReader(filePath);
        try{
            return gson.fromJson(reader, clazz);
        }finally {
            reader.close();
        }
    }
}
