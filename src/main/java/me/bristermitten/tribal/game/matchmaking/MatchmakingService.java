package me.bristermitten.tribal.game.matchmaking;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.bristermitten.tribal.data.player.TribalPlayer;
import me.bristermitten.tribal.data.player.TribalPlayers;
import me.bristermitten.tribal.data.tribes.Tribe;
import me.bristermitten.tribal.data.tribes.TribeType;
import uk.knightz.knightzapi.utils.EnumUtils;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class MatchmakingService {


    @Getter
    @Setter
    private MatchmakingData data;
    private TribeType[] used = new TribeType[1];

    /**
     * Give each player in a given Collection a team, while respecting any parties
     *
     * @return A List of Tribes, which include all players
     */
    public List<Tribe> allocateTeams(MatchmakingData data) {
        List<Tribe> tribes = new ArrayList<>();

        List<PlayerGroup> groups = allocateGroups(data);
        data.getParties().forEach(p -> {
            if (p.getMembers().size() <= 4) {
                try {
                    Tribe newTribe = newTribeUnlessFull();
                    for (String member : p.getMembers()) {
                        TribalPlayer player = TribalPlayers.getInstance().getById(member);
                        player.joinTribe(newTribe);
                    }
                } catch (Exception e) { //should never happen, there should always be enough tribes free
                    e.printStackTrace();
                }
            }
        });
        return tribes;
    }

    private List<PlayerGroup> allocateGroups(MatchmakingData data) {
        List<PlayerGroup> players = new ArrayList<>();

        Iterator<String> allPlayersIter = data.getAllPlayers().iterator();
        Iterator<Party> partiesIterator = data.getParties().iterator();
        while (partiesIterator.hasNext()) {
            Party p = partiesIterator.next();
            players.addAll(ofParty(p));
        }
    }

    private List<PlayerGroup> ofParty(Party p) {
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
                players.add(members.get(n));
            }
            list.add(new PlayerGroup(players));
        }
        return list;
    }

    private Tribe newTribeUnlessFull() throws Exception {
        if (used.length == TribeType.values().length) {
            throw new Exception("No more free TribeTypes!");
        }
        TribeType randomTribeType = EnumUtils.getRandom(TribeType.class, used);

        TribeType[] newUsed = new TribeType[used.length + 1];
        for (int i = 0, usedLength = used.length; i < usedLength; i++) {
            newUsed[i] = used[i];
        }
        newUsed[newUsed.length - 1] = randomTribeType;
        return new Tribe(randomTribeType);
    }

    @Data
    private class PlayerGroup {

        private final Set<String> players;
    }
}
