package me.bristermitten.tribal.data.tribes;

import lombok.NonNull;
import me.bristermitten.tribal.io.Config;

public class FileTribes extends Tribes {

    protected FileTribes(Config.@NonNull StorageType type) {
        super(type);
    }
}
