package me.bristermitten.tribal.data.player;

import lombok.Getter;
import lombok.ToString;
import me.bristermitten.tribal.data.tribes.Tribe;
import me.bristermitten.tribal.data.tribes.TribeType;

import static me.bristermitten.tribal.data.tribes.Tribe.NO_TRIBE;

@Getter
@ToString
public class TribalPlayer {
    private transient String id;
    private transient Tribe tribe;
    private TribeType currentTribeId;

    public TribalPlayer(String id) {
        this.id = id;
    }

    private TribalPlayer() {
    }


    public void joinTribe(Tribe t) {
        leaveTribe(tribe);
        this.tribe = t;
        this.currentTribeId = t.getTribeType();
        t.addMember(this);
    }

    public void leaveTribe(Tribe t) {
        this.tribe = NO_TRIBE;
        this.currentTribeId = TribeType.NONE;
        t.getMembers().remove(this);
    }

}
