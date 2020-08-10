package org.github.nullexceptionarg.atplayer;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.github.nullexceptionarg.atplayer.commands.CommandManager;
import org.github.nullexceptionarg.atplayer.listener.ChatListener;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AtPlayer extends JavaPlugin {

    @Override
    public void onEnable(){
        if(!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getCommand("ap").setExecutor(new CommandManager());
    }
    @Override
    public void onDisable(){

    }
}
