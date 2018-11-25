package me.bristermitten.tribal.data.player;

import com.google.inject.Inject;
import me.bristermitten.tribal.Tribal;

import java.util.HashMap;
import java.util.Map;

public abstract class TribalPlayers {

    protected static TribalPlayers instance;
    @Inject
    protected static Tribal p;
    protected final Map<String, TribalPlayer> playerMap = new HashMap<>();


    private static TribalPlayers getImpl() {
        return new FileTribalPlayers(p.config().getStorageType());
    }

    public static TribalPlayers getInstance() {
        if (instance == null) return instance = getImpl();
        return instance;
    }


    public abstract void save();

    public TribalPlayer getById(String id) {
        return playerMap.computeIfAbsent(id, TribalPlayer::new);
    }
}
