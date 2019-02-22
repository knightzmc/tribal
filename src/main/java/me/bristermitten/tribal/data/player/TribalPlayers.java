package me.bristermitten.tribal.data.player;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.io.StorageType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TribalPlayers extends SavedData {

    protected final Map<String, TribalPlayer> playerMap = new HashMap<>();

    @Inject
    public TribalPlayers(@Named("TribalDataFolder") File tribalDataFolder, StorageType type) {
        super(tribalDataFolder, type, "players");
    }


    @Override
    protected Object getDataToSave() {
        return playerMap;
    }

    public TribalPlayer getById(String id) {
        return playerMap.computeIfAbsent(id, TribalPlayer::new);
    }
}
