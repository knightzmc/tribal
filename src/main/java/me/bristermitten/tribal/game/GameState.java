package me.bristermitten.tribal.game;

import me.bristermitten.tribal.util.TimeUtils;

public enum GameState {
    WAITING_FOR_PLAYERS(() -> {
    }),
    STARTING(() -> {
        TimeUtils.inOneMinute(() -> {
        });
        Game.getInstance().startGame();
    }),
    IN_PROGRESS(() -> {
    }),
    ENDED(() -> {
    });

    private final Runnable onSet;

    GameState(Runnable onSet) {
        this.onSet = onSet;
    }

    public void run() {

    }
}
