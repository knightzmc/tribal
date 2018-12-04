package me.bristermitten.tribal.game.matchmaking;

import lombok.Data;

import java.util.List;

/**
 * A Data class of all data that should be used to matchmake players.
 */
@Data
public class MatchmakingData {

    /**
     * A list of UUIDs of all players who are joining the game. It will always have a size of 100, any players who disconnect won't be removed.
     */
    private List<String> allPlayers;
    /**
     * A List of Party objects that shows any parties of players. Any players in these parties will also appear in {@link MatchmakingData#allPlayers}
     */
    private List<Party> parties;
}
