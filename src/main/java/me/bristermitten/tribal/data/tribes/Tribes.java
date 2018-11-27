package me.bristermitten.tribal.data.tribes;

import lombok.NonNull;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.io.Config;

import java.util.HashMap;
import java.util.Map;

public abstract class Tribes extends SavedData {

    protected static Tribes instance;
    protected final Map<TribeType, Tribe> tribeMap = new HashMap<>();

    protected Tribes(Config.@NonNull StorageType type) {
        super(type, "tribes");
    }


    private static Tribes getImpl() {
        return new FileTribes(p.config().getStorageType());
    }


    public static Tribes getInstance() {
        if (instance == null) instance = getImpl();
        return instance;
    }

    @Override
    protected Object getDataToSave() {
        return tribeMap;
    }

    public Tribe getByType(TribeType tribeType) {
        return tribeMap.computeIfAbsent(tribeType, Tribe::new);
    }
}
