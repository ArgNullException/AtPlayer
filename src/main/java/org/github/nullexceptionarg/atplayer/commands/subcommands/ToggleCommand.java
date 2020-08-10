package org.github.nullexceptionarg.atplayer.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.nullexceptionarg.atplayer.AtPlayer;
import org.github.nullexceptionarg.atplayer.commands.SubCommand;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ToggleCommand extends SubCommand {
    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getDescription() {
        return "Toggle the ping from @ command";
    }

    @Override
    public String getSyntax() {
        return "/ap toggle";
    }

    @Override
    public String getPermission(){
        return "atplayer.toggle";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(!permissionCheck(player)) return;
        AtPlayer instance = JavaPlugin.getPlugin(AtPlayer.class);
        FileConfiguration cfg = instance.getConfig();
        String id = player.getUniqueId().toString();

        if(cfg.getBoolean(id,true)){
            cfg.set(id, false);
            player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "@Player" + ChatColor.YELLOW + "]" + ChatColor.RESET +
                    "Sound Notification: " + ChatColor.RED + "OFF");
        } else{
            cfg.set(id,true);
            player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "@Player" + ChatColor.YELLOW + "]" + ChatColor.RESET + "Sound Notification: " + ChatColor.GREEN + "ON");
        }
        instance.saveConfig();
    }
}
