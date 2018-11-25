package me.bristermitten.tribal.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.data.player.TribalPlayers;
import me.bristermitten.tribal.io.Config;

public class TribalBinderModule extends AbstractModule {

    private final Tribal tribalPlugin;

    private final Config config;

    public TribalBinderModule(Tribal tribalPlugin) {
        this.tribalPlugin = tribalPlugin;
        //snakeyaml needs this to find Config.class
        Thread.currentThread().setContextClassLoader(tribalPlugin.getClass().getClassLoader());

        this.config = Config.YAML.loadAs(tribalPlugin.getConfig().saveToString(), Config.class);

    }

    @Override
    protected void configure() {
        this.bind(Tribal.class).toInstance(tribalPlugin);
        this.bind(Config.class).toProvider(() -> config);
        this.requestStaticInjection(TribalPlayers.class);
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }
}
