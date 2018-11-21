package me.bristermitten.tribal.data.player;

import lombok.Getter;
import me.bristermitten.tribal.io.Config;

import java.util.HashMap;
import java.util.Map;

public abstract class TribalPlayers {

    @Getter
    protected static TribalPlayers instance = getImpl();


    private final Map<String, TribalPlayer> playerMap = new HashMap<>();

    private static TribalPlayers getImpl() {
        return instance = new FileTribalPlayers(Config.storageType);
    }
}
