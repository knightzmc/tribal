package me.bristermitten.tribal.game.matchmaking;

import lombok.Data;

import java.util.List;

/**
 * A Data class of all data that should be used to matchmake players.
 */
@Data
public class MatchmakingData {

    /**
     * A list of UUIDs of all players who are joining the game but aren't in a party. All of these players + all party players = 100
     */
    private List<String> nonPartiedPlayers;
    /**
     * A List of Party objects that shows any parties of players. Any players in these parties won't appear in {@link MatchmakingData#nonPartiedPlayers}
     */
    private List<Party> parties;
}
