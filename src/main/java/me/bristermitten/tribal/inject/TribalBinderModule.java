package me.bristermitten.tribal.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.data.locations.GameLocation;
import me.bristermitten.tribal.io.Config;
import me.bristermitten.tribal.io.StorageType;

import java.io.File;

import static com.google.inject.name.Names.named;

public class TribalBinderModule extends AbstractModule {

    private final Tribal tribalPlugin;
    private final GameLocation lobbyLocation;

    public TribalBinderModule(Tribal tribalPlugin) {
        this.tribalPlugin = tribalPlugin;
        this.lobbyLocation = new GameLocation(null, "Lobby");
    }

    @Override
    protected void configure() {
        this.bind(Tribal.class).toInstance(tribalPlugin);
        this.bind(StorageType.class).toInstance(tribalPlugin.config().getStorageType());
        this.bind(Config.class).toInstance(tribalPlugin.config());
        this.bind(GameLocation.class).annotatedWith(named("Lobby")).toInstance(lobbyLocation);
        this.bind(File.class).annotatedWith(named("TribalDataFolder")).toInstance(tribalPlugin.getDataFolder());
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }
}
