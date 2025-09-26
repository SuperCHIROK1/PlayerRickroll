package me.superchirok1.playerrickroll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Reload implements CommandExecutor {

    private final PlayerRickroll plugin;

    public Reload(PlayerRickroll plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("playerrickroll.reload")) {
            Utils.send(sender, plugin.getConfig().getString("messages.no-perms"));
            return true;
        }

        plugin.reloadConfig();
        Utils.send(sender, plugin.getConfig().getString("messages.reloaded"));
        return true;
    }
}
