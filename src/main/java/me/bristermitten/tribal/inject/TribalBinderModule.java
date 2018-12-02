package me.bristermitten.tribal.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.data.SavedData;
import me.bristermitten.tribal.game.Game;
import me.bristermitten.tribal.game.matchmaking.MatchmakingService;
import me.bristermitten.tribal.io.Config;

public class TribalBinderModule extends AbstractModule {

    private final Tribal tribalPlugin;
    private final Config config;
    private final Game game;
    private final MatchmakingService matchmakingService;

    public TribalBinderModule(Tribal tribalPlugin) {
        this.tribalPlugin = tribalPlugin;
        this.matchmakingService = new MatchmakingService();

        this.game = new Game();
        requestInjection(game);
        Game.setInstance(game);

        //snakeyaml needs this to find Config.class
        Thread.currentThread().setContextClassLoader(tribalPlugin.getClass().getClassLoader());
        this.config = Config.YAML.loadAs(tribalPlugin.getConfig().saveToString(), Config.class);

    }

    @Override
    protected void configure() {
        this.bind(Tribal.class).toInstance(tribalPlugin);
        this.bind(Config.class).toInstance(config);
        this.bind(Game.class).toInstance(game);
        this.bind(MatchmakingService.class).toInstance(matchmakingService);
        this.requestStaticInjection(SavedData.class);

    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }
}
