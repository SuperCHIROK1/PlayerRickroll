package me.superchirok1.playerrickroll;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.io.File;

public class Rickroll {

    private final PlayerRickroll plugin;

    public Rickroll(PlayerRickroll plugin) {
        this.plugin = plugin;
    }

    public void rickrollPlayer(Player player) {

        title(player);
        chat(player);
        plugin.music(player);
        actionbar(player);

    }

    private void title(Player player) {

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("rickroll.title");

        if (section == null) {
            return;
        }

        if (section.getBoolean("enabled")) {
            String title = Utils.format(player, section.getString("title"));
            String subtitle = Utils.format(player, section.getString("subtitle"));
            int fadeIn = section.getInt("fade-in");
            int stay = section.getInt("stay");
            int fadeOut = section.getInt("fade-out");

            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    private void chat(Player player) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("rickroll.chat");

        if (section == null) {
            return;
        }

        if (section.getBoolean("enabled")) {
            for (String msg : section.getStringList("msgs")) {
                Utils.send(player, msg);
            }
        }
    }

    private void actionbar(Player player) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("rickroll.actionbar");

        if (section == null) {
            return;
        }

        if (section.getBoolean("enabled")) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format(section.getString("text"))));
        }
    }

}
