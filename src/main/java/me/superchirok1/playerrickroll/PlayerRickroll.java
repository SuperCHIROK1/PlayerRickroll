package me.superchirok1.playerrickroll;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerRickroll extends JavaPlugin {

    private final Map<UUID, SongPlayer> activeSongs = new HashMap<>();

    @Override
    public void onEnable() {
        Utils.plugin = this;

        saveDefaultConfig();
        saveResource("music.nbs", false);

        getCommand("rickroll").setExecutor(new Command(this));
        getCommand("playerrickroll").setExecutor(new Reload(this));

        getServer().getPluginManager().registerEvents(new Leave(activeSongs), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void music(Player player) {
        ConfigurationSection section = getConfig().getConfigurationSection("rickroll.music");

        if (section == null) {
            return;
        }

        if (section.getBoolean("enabled")) {
            String songFileName = section.getString("song", "music.nbs");
            File songFile = new File(getDataFolder(), songFileName);

            if (!songFile.exists()) {
                getLogger().warning("Music "+songFileName+ " not found");
                return;
            }

            if (songFile == null) {
                getLogger().warning("Music "+songFileName+ " is null");
                return;
            }

            Song song = NBSDecoder.parse(songFile);
            SongPlayer songPlayer = new RadioSongPlayer(song);

            songPlayer.setAutoDestroy(true);
            songPlayer.addPlayer(player);

            songPlayer.setPlaying(true);

            activeSongs.put(player.getUniqueId(), songPlayer);
        }
    }
}
