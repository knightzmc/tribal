package me.bristermitten.tribal.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import com.google.inject.Inject;
import me.bristermitten.tribal.data.locations.GameLocation;
import me.bristermitten.tribal.data.locations.GameLocations;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.knightz.knightzapi.menu.adapter.CollectionToMenuAdapter;

@CommandAlias("tribal")
public class TribalCommand extends BaseCommand {
    @Inject
    private GameLocations gameLocations;


    @Subcommand("admin")
    @CommandPermission("tribal.admin")
    public class AdminCommands extends TribalCommand {

        @HelpCommand
        public void doHelp(CommandSender sender, CommandHelp help) {
            help.showHelp();
        }


        @Subcommand("locations")
        public void doLocationsCommand(Player p) {
            new CollectionToMenuAdapter<GameLocation>().adapt(gameLocations.getLocations()).open(p);
        }
    }

}
