import Entities.UserProfile;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class YamlSerializer {

    private static final Yaml yaml;

    static {
        var options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        var representer = new Representer(options);
        representer.getPropertyUtils().setSkipMissingProperties(true); // Пропуск відсутніх властивостей
        representer.addClassTag(UserProfile.class, Tag.MAP);
        representer.addClassTag(UUID.class, Tag.STR);

        yaml = new Yaml(representer);
    }

    public static <T> void serialize(T object, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(object, writer);
        }
    }

    public static <T> T deserialize(String filePath, Class<T> clazz) throws IOException {
        try (InputStream input = new FileInputStream(filePath)) {
            return yaml.loadAs(input, clazz);
        }
    }
}
