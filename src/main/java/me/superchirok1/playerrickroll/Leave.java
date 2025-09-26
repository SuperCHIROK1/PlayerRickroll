package me.superchirok1.playerrickroll;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class Leave implements Listener {

    private final Map<UUID, SongPlayer> activeSongs;

    public Leave(Map<UUID, SongPlayer> activeSongs) {
        this.activeSongs = activeSongs;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (activeSongs.containsKey(uuid)) {
            SongPlayer songPlayer = activeSongs.get(uuid);
            songPlayer.destroy();
            activeSongs.remove(uuid);
        }
    }

}
