package me.bristermitten.tribal.game.matchmaking;

import com.google.inject.Inject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.bristermitten.tribal.data.player.TribalPlayer;
import me.bristermitten.tribal.data.player.TribalPlayers;
import me.bristermitten.tribal.data.tribes.Tribe;
import me.bristermitten.tribal.data.tribes.TribeType;
import me.bristermitten.tribal.util.RandomUtils;

import javax.inject.Singleton;
import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class MatchmakingService {

    private final TribalPlayers tribalPlayers;

    @Getter
    @Setter
    private MatchmakingData data;
    private TribeType[] used = new TribeType[]{TribeType.NONE};

    @Inject
    public MatchmakingService(TribalPlayers tribalPlayers) {
        this.tribalPlayers = tribalPlayers;
    }


    public List<Tribe> allocateTeams() {
        return allocateTeams(data);
    }

    /**
     * Give each player in a given Collection a team, while respecting any parties
     *
     * @return A List of Tribes, which include all players
     */
    public List<Tribe> allocateTeams(MatchmakingData data) {
        List<Tribe> tribes = new ArrayList<>();

        List<PlayerGroup> groups = allocateGroups(data);
        groups.forEach(p -> {
            if (p.getPlayers().size() <= 4) {
                try {
                    Tribe newTribe = newTribeUnlessFull();
                    for (String member : p.getPlayers()) {
                        TribalPlayer player = tribalPlayers.getById(member);
                        player.joinTribe(newTribe);
                    }
                    tribes.add(newTribe);
                } catch (SizeLimitExceededException e) { //should never happen, there should always be enough tribes free
                    e.printStackTrace();
                }
            }
        });
        return tribes;
    }

    private List<PlayerGroup> allocateGroups(MatchmakingData data) {
        List<PlayerGroup> players = new ArrayList<>();

        List<String> nonPartied = data.getNonPartiedPlayers();
        if (data.getParties() != null) {
            for (Party p : data.getParties()) {
                players.addAll(ofParty(p, nonPartied));
            }
        }

        Set<String> playerSet = new HashSet<>();
        for (String player : nonPartied) {
            playerSet.add(player);
            if (playerSet.size() >= 4) {
                players.add(new PlayerGroup(playerSet));
                playerSet = new HashSet<>();
            }
        }
        return players;
    }

    private List<PlayerGroup> ofParty(Party p, List<String> nonPartied) {
        List<PlayerGroup> list = new ArrayList<>();
        List<String> members = p.getMembers();
        int memberRemainder = members.size() % 4;
        if (memberRemainder == 0) {
            for (int i = 0; i < members.size() / 4; i++) {
                Set<String> players = new HashSet<>();
                for (int x = 0; x < 4; x++) {
                    players.add(members.get(x + i));
                }
                list.add(new PlayerGroup(players));
            }
        } else {
            int needed = 4 - memberRemainder;
            Set<String> players = new HashSet<>(members);
            for (int n = 0; n < needed; n++) {
                players.add(nonPartied.get(n));
                nonPartied.remove(n);
            }
            list.add(new PlayerGroup(players));
        }
        return list;
    }

    private Tribe newTribeUnlessFull() throws SizeLimitExceededException {
        if (used.length == TribeType.values().length) {
            throw new SizeLimitExceededException("No more free TribeTypes!");
        }
        TribeType randomTribeType = RandomUtils.randomEnum(TribeType.class, used);

        TribeType[] newUsed = new TribeType[used.length + 1];
        System.arraycopy(used, 0, newUsed, 0, used.length);
        newUsed[newUsed.length - 1] = randomTribeType;
        used = newUsed;

        return new Tribe(randomTribeType);
    }

    @Data
    private class PlayerGroup {

        private final Set<String> players;
    }
}
