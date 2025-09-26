package me.superchirok1.playerrickroll;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {

    private final PlayerRickroll plugin;

    public Command(PlayerRickroll plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("playerrickroll.rickroll")) {
            Utils.send(sender, plugin.getConfig().getString("messages.no-perms"));
            return true;
        }

        if (args.length == 0) {
            Utils.send(sender, plugin.getConfig().getString("messages.usage"));
            return true;
        }

        if (args.length >= 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                Utils.send(sender, plugin.getConfig().getString("messages.player-not-found"));
                return true;
            }

            new Rickroll(plugin).rickrollPlayer(player);
            Utils.send(sender, plugin.getConfig().getString("messages.rickrolled"));
            return true;
        }

        Utils.send(sender, plugin.getConfig().getString("messages.usage"));
        return true;
    }
}
