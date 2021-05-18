package org.github.nullexceptionarg.atplayer.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.github.nullexceptionarg.atplayer.AtPlayer;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChatListener implements Listener {
    private AtPlayer instance;
    private FileConfiguration cfg;

    public ChatListener(AtPlayer main){
        instance = main;
    }

    @EventHandler
    public void onChatPing(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        List<UUID> concernedPlayers = Bukkit.getOnlinePlayers().stream().filter(x -> message.contains(x.getName())).map(Entity::getUniqueId).collect(Collectors.toList());
        if(concernedPlayers.size() == 0)
            return;

        cfg = instance.getConfig();
        for (UUID p : concernedPlayers) {
            Player ply = Bukkit.getPlayer(p);
                if(cfg.getBoolean(p.toString(),true)){
                    ply.playSound(ply.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1);
                }
                event.setMessage(event.getMessage().replaceAll(ply.getName(), ChatColor.AQUA + "@" + ply.getName() + ChatColor.RESET ));
        }
    }

}


