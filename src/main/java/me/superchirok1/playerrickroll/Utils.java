package me.superchirok1.playerrickroll;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static PlayerRickroll plugin;

    public Utils(PlayerRickroll plugin) {
        this.plugin = plugin;
    }

    private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");

    private static String placeholders(String text, Player player) {
        return text
                .replace("%name%", player.getName())
                .replace("%x%", String.valueOf(player.getLocation().getX()))
                .replace("%y%", String.valueOf(player.getLocation().getY()))
                .replace("%z%", String.valueOf(player.getLocation().getZ()))
                .replace("%world%", player.getWorld().getName())
                .replace("%health%", String.valueOf(player.getHealth()))
                .replace("%max_health%", String.valueOf(player.getMaxHealth()));
    }

    public static String format(Player player, String message) {
        if (message == null) return "";

        if (player != null) {
            message = placeholders(message, player);
        }

        if (player != null && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }

        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hex = matcher.group(1);
            matcher.appendReplacement(buffer, ChatColor.of("#" + hex).toString());
        }
        matcher.appendTail(buffer);
        message = buffer.toString();

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String format(String message) {
        return format(null, message);
    }

    public static void send(Player player, String message) {
        player.sendMessage(format(player, message));
    }

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(format(null, message));
    }

    public static List<String> formatList(Player player, List<String> list) {
        if (list == null) return List.of();
        return list.stream().map(line -> format(player, line)).toList();
    }

}
