package me.bristermitten.tribal.data.player;

import com.google.gson.Gson;
import com.google.inject.Inject;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.io.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;

public class FileTribalPlayers extends MemoryTribalPlayers {

    private transient final Config.StorageType type;
    private transient final Gson gson = new Gson();
    private transient final Yaml yaml = new Yaml();
    @Inject
    private Tribal p;
    private File file;

    public FileTribalPlayers(Config.StorageType type) {
        this.type = type;
        file = new File(p.getDataFolder(), "players." + type.toString().toLowerCase());
        save();
    }


    public void save() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            switch (type) {
                case YAML:
                    writer.write(yaml.dump(this));
                    break;
                case JSON:
                    writer.write(gson.toJson(this));
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
