package me.bristermitten.tribal;

import com.google.inject.Injector;
import me.bristermitten.tribal.data.Tribe;
import me.bristermitten.tribal.data.Tribes;
import me.bristermitten.tribal.inject.TribalBinderModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Tribal extends JavaPlugin {

    private boolean debug = false;

    @Override
    public void onEnable() {
        TribalBinderModule binder = new TribalBinderModule(this);
        Injector injector = binder.createInjector();
        injector.injectMembers(this);


        Tribe t = new Tribe(Tribes.ASAMAL);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void debug(String s) {
        debug(s, Level.INFO);
    }

    public void debug(String s, Level level) {
        if (debug)
            getLogger().log(level, "[DEBUG] " + s);
    }
}
