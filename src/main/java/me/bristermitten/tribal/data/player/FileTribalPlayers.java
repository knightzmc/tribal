package me.bristermitten.tribal.data.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import me.bristermitten.tribal.io.Config;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileWriter;

public class FileTribalPlayers extends MemoryTribalPlayers {

    @NonNull
    private transient final Config.StorageType type;
    private transient final Gson gson;
    private transient final Yaml yaml;
    private File file;

    {
        gson = new GsonBuilder().setPrettyPrinting().create();

        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        yaml = new Yaml(new Constructor(), new Representer(), options);
    }

    protected FileTribalPlayers(Config.@NonNull StorageType type) {
        this.type = type;
    }


    public void save() {
        if (file == null)
            file = new File(p.getDataFolder(), "players." + type.toString().toLowerCase());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            switch (type) {
                case YAML:
                    writer.write(yaml.dump(playerMap));
                    break;
                case JSON:
                    writer.write(gson.toJson(playerMap));
                    break;
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            p.getLogger().severe("TribalPlayers failed to save to " + file.getPath());
            p.debug("Exception: " + e.getMessage());
        }
    }
}
