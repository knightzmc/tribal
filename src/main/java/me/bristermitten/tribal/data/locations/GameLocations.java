package me.bristermitten.tribal.data.locations;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.io.StorageType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Singleton
public class GameLocations extends SavedData {

    private final Map<String, GameLocation> locations = new HashMap<>();

    @Inject
    protected GameLocations(@Named("TribalDataFolder") File tribalDataFolder, StorageType type) {
        super(tribalDataFolder, type, "locations");
        addLocation(new GameLocation(null, "Lobby"));
    }

    public void addLocation(GameLocation location) {
        locations.put(location.getAlias(), location);
    }


    public Set<GameLocation> getLocations() {
        return Sets.newHashSet(locations.values());
    }

    @Override
    protected Object getDataToSave() {
        return locations;
    }
}
