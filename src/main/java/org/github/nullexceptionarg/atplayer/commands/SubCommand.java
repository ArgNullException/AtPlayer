package org.github.nullexceptionarg.atplayer.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.nullexceptionarg.atplayer.AtPlayer;

public abstract class SubCommand {

    public Boolean permissionCheck(Player p){
        if(!p.hasPermission(getPermission())){
            p.sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "@Player" + ChatColor.YELLOW + "]" + ChatColor.RED + "You do not have the permission to use this command!");
            return false;
        }
        return true;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getPermission();

    public abstract void perform(Player player, String args[]);
}
