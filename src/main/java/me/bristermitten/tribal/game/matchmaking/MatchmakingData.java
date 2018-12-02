package me.bristermitten.tribal.game.matchmaking;

import lombok.Data;

import java.util.List;

@Data
public class MatchmakingData {

    private List<String> allPlayers;
    private List<Party> parties;
}
