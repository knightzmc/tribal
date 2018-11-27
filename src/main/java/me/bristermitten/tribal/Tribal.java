package me.bristermitten.tribal;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.bristermitten.tribal.data.player.TribalPlayers;
import me.bristermitten.tribal.data.tribes.TribeType;
import me.bristermitten.tribal.data.tribes.Tribes;
import me.bristermitten.tribal.inject.TribalBinderModule;
import me.bristermitten.tribal.io.Config;
import me.bristermitten.tribal.world.TribalWorldGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static me.bristermitten.tribal.data.player.TribalPlayers.getInstance;

public final class Tribal extends JavaPlugin {


    @Inject
    private Config config;

    public Config config() {
        return config;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupInjection();


        TribalPlayers players = getInstance();
        players.getById("hi");
        players.getById("hi2");
        players.getById("hi3");
        players.getById("hi4");
        players.save();


        Tribes tribes = Tribes.getInstance();
        tribes.getByType(TribeType.ASAMAL);
        tribes.getByType(TribeType.YANRIB);
        tribes.save();
    }

    private void setupInjection() {
        TribalBinderModule binder = new TribalBinderModule(this);
        Injector injector = binder.createInjector();
        injector.injectMembers(this);
        injector.injectMembers(getInstance());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new TribalWorldGenerator();
    }

    public void debug(String s) {
        debug(s, Level.INFO);
    }

    public void debug(String s, Level level) {
        if (config.isDebug())
            getLogger().log(level, "[DEBUG] " + s);
    }
}
