package me.bristermitten.tribal.data.player;

import lombok.NonNull;
import me.bristermitten.tribal.io.Config;

public class FileTribalPlayers extends TribalPlayers {

    protected FileTribalPlayers(Config.@NonNull StorageType type) {
        super(type);
    }
}
