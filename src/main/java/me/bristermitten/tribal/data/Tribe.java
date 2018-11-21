package me.bristermitten.tribal.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.bristermitten.tribal.data.player.TribalPlayer;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class Tribe {

    private final Set<TribalPlayer> members = new HashSet<>(4);
    private final Tribes internalTribe;
    private int level = 1;

}
