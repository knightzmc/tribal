package me.bristermitten.tribal.data.tribes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.io.StorageType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Tribes extends SavedData {

    protected final Map<TribeType, Tribe> tribeMap = new HashMap<>();

    @Inject
    public Tribes(@Named("TribalDataFolder") File tribalDataFolder, StorageType type) {
        super(tribalDataFolder, type, "tribes");
    }

    @Override
    protected Object getDataToSave() {
        return tribeMap;
    }

    public Tribe getByType(TribeType tribeType) {
        return tribeMap.computeIfAbsent(tribeType, Tribe::new);
    }
}
