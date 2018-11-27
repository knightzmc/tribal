package me.bristermitten.tribal.data.player;

import lombok.Getter;
import me.bristermitten.tribal.data.tribes.Tribe;
import me.bristermitten.tribal.data.tribes.TribeType;
import me.bristermitten.tribal.data.tribes.Tribes;

@Getter
public class TribalPlayer {
    private transient String id;
    private TribeType tribeId;

    public TribalPlayer(String id) {
        this.id = id;
    }

    private TribalPlayer() {
    }


    public void joinTribe(Tribe t) {
        if (tribeId != null)
            leaveTribe(Tribes.getInstance().getByType(tribeId));

        this.tribeId = t.getTribeType();
        t.getMembers().add(this);
    }

    public void leaveTribe(Tribe t) {
        this.tribeId = null;
        t.getMembers().remove(this);
        joinTribe(Tribe.NO_TRIBE);
    }

}
