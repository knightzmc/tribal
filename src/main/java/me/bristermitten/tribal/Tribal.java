package me.bristermitten.tribal;

import co.aikar.commands.PaperCommandManager;
import com.google.inject.Injector;
import me.bristermitten.tribal.command.TribalCommand;
import me.bristermitten.tribal.game.Game;
import me.bristermitten.tribal.inject.TribalBinderModule;
import me.bristermitten.tribal.io.Config;
import me.bristermitten.tribal.io.StorageType;
import me.bristermitten.tribal.world.TribalWorldGenerator;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tribal extends JavaPlugin {

    private Config config;

    public Config config() {
        return config;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        //Snakeyaml needs this to find Config.class
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        this.config = StorageType.YAML.load(getConfig().saveToString(), Config.class);

        Injector injector = setupInjection();
        Game.setInstance(injector.getInstance(Game.class));

        //register commands
        PaperCommandManager manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");
        manager.registerCommand(injector.getInstance(TribalCommand.class));
    }

    private Injector setupInjection() {
        TribalBinderModule binder = new TribalBinderModule(this);
        return binder.createInjector();
    }

    @Override
    public void onDisable() {
        try {
            getConfig().loadFromString(StorageType.YAML.saveToString(config));
            saveConfig();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new TribalWorldGenerator();
    }

}
