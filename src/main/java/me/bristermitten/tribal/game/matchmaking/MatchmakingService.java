package me.bristermitten.tribal.game.matchmaking;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.bristermitten.tribal.data.tribes.Tribe;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MatchmakingService {


    @Getter
    @Setter
    private MatchmakingData data;

    /**
     * Give each player in a given Collection a team, while respecting any parties
     *
     * @return
     */
    public List<Tribe> allocateTeams(MatchmakingData data) {

    }
}
