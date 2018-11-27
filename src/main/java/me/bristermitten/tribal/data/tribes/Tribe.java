package me.bristermitten.tribal.data.tribes;

import lombok.Data;
import me.bristermitten.tribal.data.player.TribalPlayer;

import java.util.HashSet;
import java.util.Set;

@Data
public class Tribe {


    public static final Tribe NO_TRIBE = new Tribe(TribeType.NONE);

    static {
        NO_TRIBE.members = new HashSet<>();
        NO_TRIBE.level = -1;
    }

    private Set<TribalPlayer> members = new HashSet<>(4);
    private transient TribeType tribeType;
    private int level = 1;


    private Tribe() {
    }

    public Tribe(TribeType tribeType) {
        this.tribeType = tribeType;
    }
}
