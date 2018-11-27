package me.bristermitten.tribal.data.tribes;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.block.Biome.*;

@Getter
public enum TribeType {

    ASAMAL(DESERT, YELLOW),
    YANRIB(BIRCH_FOREST, AQUA),
    NONE(DEEP_OCEAN, ChatColor.WHITE);
    private final Biome biome;

    private final ChatColor color;

    TribeType(Biome biome, ChatColor color) {
        this.biome = biome;
        this.color = color;
    }
}
