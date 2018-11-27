package me.bristermitten.tribal.game.matchmaking;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class Party {

    private Set<UUID> members = new HashSet<>();


}
