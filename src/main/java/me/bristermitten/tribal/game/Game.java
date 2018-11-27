package me.bristermitten.tribal.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.bristermitten.tribal.Tribal;

/**
 * Stores all info about the currently running Tribal game. It is a Singleton, as only 1 Game can be running at a time,
 * and will never be null (use {@link Game#hasStarted} to check if the game is active
 */
@Singleton
public class Game {

    @Inject
    private Tribal plugin;
    private boolean gameHasStarted;

    public boolean hasStarted() {
        return gameHasStarted;
    }

}
