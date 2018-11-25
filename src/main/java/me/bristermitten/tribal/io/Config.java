package me.bristermitten.tribal.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    public static final Yaml YAML = new Yaml(new Constructor(Config.class));
    private StorageType storageType = StorageType.JSON;
    private boolean debug;


    public enum StorageType {
        JSON, YAML
    }
}
