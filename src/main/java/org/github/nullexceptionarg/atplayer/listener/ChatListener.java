package org.github.nullexceptionarg.atplayer.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.github.nullexceptionarg.atplayer.AtPlayer;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {
    private AtPlayer instance;
    private FileConfiguration cfg;

    public ChatListener(AtPlayer main){
        instance = main;
    }

    @EventHandler
    public void onChatPing(AsyncPlayerChatEvent event) {
        if (!event.getMessage().contains("@")) return;

        Pattern pingPattern = Pattern.compile("(@([^\\s@]+))");
        Matcher pingMatcher = pingPattern.matcher(event.getMessage());
        if (pingMatcher.find()) {
            StringBuffer buffer = new StringBuffer();
            List<Player> concernedPlayers = new ArrayList<>();
            List<Player> colPlayer = new ArrayList<>(Bukkit.getOnlinePlayers());
            do {
                String name = pingMatcher.group(2).toUpperCase();
                cfg = instance.getConfig();
                for (Player p : colPlayer) {

                    if (p.getDisplayName().toUpperCase().contains(name) || p.getName().toUpperCase().contains(name)) {
                        pingMatcher.appendReplacement(buffer, ChatColor.AQUA + "@" + p.getName());
                        if (cfg.getBoolean(p.getUniqueId().toString(),true) && !concernedPlayers.contains(p))
                            concernedPlayers.add(p);
                        break;
                    }
                }
            } while (pingMatcher.find());
            pingMatcher.appendTail(buffer);
            for (Player p : concernedPlayers) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1);
            }

            event.setMessage(buffer.toString());
        }

    }

}
