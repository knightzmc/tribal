package me.bristermitten.tribal.data.player;

import lombok.NonNull;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.io.Config;

import java.util.HashMap;
import java.util.Map;

public class TribalPlayers extends SavedData {

    protected static TribalPlayers instance;
    protected final Map<String, TribalPlayer> playerMap = new HashMap<>();

    protected TribalPlayers(Config.@NonNull StorageType type) {
        super(type, "players");
    }

    private static TribalPlayers getImpl() {
        return new FileTribalPlayers(p.config().getStorageType());
    }

    public static TribalPlayers getInstance() {
        if (instance == null) return instance = getImpl();
        return instance;
    }

    @Override
    protected Object getDataToSave() {
        return playerMap;
    }

    public TribalPlayer getById(String id) {
        return playerMap.computeIfAbsent(id, TribalPlayer::new);
    }
}
