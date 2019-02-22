package me.bristermitten.tribal.data.locations;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

@Data
public class GameLocation {

    private final String alias;
    private Location location;

    public GameLocation(Location location, String alias) {
        this.location = location;
        this.alias = alias;
    }

    public void teleport(Player player) {
        player.teleport(location);
    }

    public void teleport(Collection<? extends Player> players) {
        players.forEach(this::teleport);
    }

    public void teleportAll() {
        teleport(Bukkit.getOnlinePlayers());
    }
}
