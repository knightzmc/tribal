package me.bristermitten.tribal.data.player;

import lombok.Getter;
import lombok.Setter;

public class TribalPlayer {

    @Getter
    @Setter
    private transient String id;

    public TribalPlayer(String id) {
        this.id = id;
    }

    private TribalPlayer() {
    }
}
