package me.bristermitten.tribal.game;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import me.bristermitten.tribal.game.matchmaking.MatchmakingService;

import static me.bristermitten.tribal.game.GameState.WAITING_FOR_PLAYERS;

/**
 * Stores all info about the currently running Tribal game. It is a Singleton, as only 1 Game can be running at a time,
 * and will never be null (use {@link Game#hasStarted} to check if the game is active
 */
@Singleton
public class Game {

    @Getter
    private static Game instance;
    @Getter
    private GameState gameState;
    private MatchmakingService service;


    @Inject
    public Game(MatchmakingService service) {
        this.service = service;
        setGameState(WAITING_FOR_PLAYERS);
    }

    public static void setInstance(Game instance) {
        if (Game.instance != null) {
            throw new IllegalStateException("Cannot redefine Game instance!");
        }
        Game.instance = instance;
    }

    private void setGameState(GameState gameState) {
        this.gameState = gameState;
        gameState.run();
    }

    public boolean hasStarted() {
        return gameState == GameState.IN_PROGRESS;
    }

    public void startGame() {
        setGameState(GameState.IN_PROGRESS);
    }
}
