package me.bristermitten.tribal.data;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.block.Biome.DESERT;

@Getter
public enum Tribes {

    ASAMAL(DESERT, YELLOW),
    YANRIB(Biome.BIRCH_FOREST, AQUA);
    private final Biome biome;

    private final ChatColor color;

    Tribes(Biome biome, ChatColor color) {
        this.biome = biome;
        this.color = color;
    }
}
