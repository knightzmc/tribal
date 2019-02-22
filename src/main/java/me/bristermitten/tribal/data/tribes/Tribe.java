package me.bristermitten.tribal.data.tribes;

import com.google.common.collect.ImmutableSet;
import lombok.Data;
import me.bristermitten.tribal.data.player.TribalPlayer;

import java.util.HashSet;
import java.util.Set;

@Data
public class Tribe {


    public static final Tribe NO_TRIBE = new Tribe(TribeType.NONE);

    static {
        NO_TRIBE.members = ImmutableSet.of();
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

    public Tribe addMember(TribalPlayer tribalPlayer) {
        if (members.size() == 4) {
            throw new IndexOutOfBoundsException("Members for tribe " + tribeType + " is full");
        }
        members.add(tribalPlayer);
        return this;
    }
}
