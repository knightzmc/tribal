package me.bristermitten.tribal.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.NonNull;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.io.Config;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class SavedData {

    @Inject
    protected static Tribal p;
    private final File file;

    protected transient final Config.StorageType type;
    protected transient final Gson gson;
    protected transient final Yaml yaml;

    protected SavedData(@NonNull Config.StorageType type, String fileName) {
        this.type = type;

        this.file = new File(p.getDataFolder(), fileName + "." + type.toString().toLowerCase());

        this.gson = new GsonBuilder().setPrettyPrinting().create();

        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        this.yaml = new Yaml(new Constructor(), new Representer(), options);
        yaml.setBeanAccess(BeanAccess.FIELD);
    }

    public void save() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            switch (type) {
                case YAML:
                    yaml.dump(getDataToSave(), writer);
                    break;
                case JSON:
                    gson.toJson(getDataToSave(), writer);
                    break;
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            p.getLogger().severe(getClass().getSimpleName() + " Failed to save to " + file.getPath());
            p.debug("Exception: " + e.getMessage());
        }
    }

    protected abstract Object getDataToSave();
}
