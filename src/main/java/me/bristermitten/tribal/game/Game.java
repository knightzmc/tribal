package me.bristermitten.tribal.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import me.bristermitten.tribal.Tribal;
import me.bristermitten.tribal.game.matchmaking.MatchmakingService;

/**
 * Stores all info about the currently running Tribal game. It is a Singleton, as only 1 Game can be running at a time,
 * and will never be null (use {@link Game#hasStarted} to check if the game is active
 */
@Singleton
public class Game {

    @Getter
    private static Game instance;
    @Inject
    private Tribal plugin;
    private boolean gameHasStarted;


    @Inject
    @Getter
    private MatchmakingService service;

    public static void setInstance(Game instance) {
        if (Game.instance != null) {
            throw new IllegalStateException("Cannot redefine Game instance!");
        }
        Game.instance = instance;
    }

    public boolean hasStarted() {
        return gameHasStarted;
    }

}
