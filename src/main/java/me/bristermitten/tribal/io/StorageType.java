package me.bristermitten.tribal.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import me.bristermitten.tribal.Tribal;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.StringWriter;
import java.io.Writer;

public enum StorageType {
    JSON("json") {
        @Override
        public void save(Object toSave, Writer out) {
            gson.toJson(toSave, out);
        }

        @Override
        public <T> T load(String data, Class<T> type) {
            return gson.fromJson(data, type);
        }

    }, YAML("yml") {
        @Override
        public void save(Object toSave, Writer out) {
            yaml.dump(toSave, out);
        }

        @Override
        public <T> T load(String data, Class<T> type){
            return yaml.loadAs(data, type);
        }
    };

    private static final Gson gson;
    private static final Yaml yaml;

    static {
        gson = new GsonBuilder().setPrettyPrinting().create();


        Representer representer = new Representer();
        representer.addClassTag(Config.class, Tag.MAP);
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        yaml = new Yaml(
                new CustomClassLoaderConstructor(Config.class, Tribal.class.getClassLoader()),
                representer, options
        );
        yaml.setBeanAccess(BeanAccess.FIELD);
    }

    @Getter
    private final String fileExtension;

    StorageType(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public abstract void save(Object toSave, Writer out);

    public String saveToString(Object toSave){
        StringWriter writer = new StringWriter();
        save(toSave, writer);
        return writer.toString();
    }

    public abstract <T> T load(String data, Class<T> type);
}