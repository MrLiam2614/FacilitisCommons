package me.mrliam2614.facilitiscommons.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Messages {
    public static void sendMessage(Player player, String message) {
        if(!isMessage(message))
            return;
        player.sendMessage(color(message));
    }

    public static void sendMessage(CommandSender sender, String message) {
        if(!isMessage(message))
            return;
        sender.sendMessage(color(message));
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendError(JavaPlugin plugin, String message) {
        if(!isMessage(message))
            return;
        String pluginName = plugin.getName();
        String completeMessage = color("&7[&4Error&7] " + pluginName + ": " + message);
        sendLog(completeMessage);
    }

    public static void sendWarning(JavaPlugin plugin, String message) {
        if(!isMessage(message))
            return;
        String pluginName = plugin.getName();
        String completeMessage = color("&7[&eWarning&7] " + pluginName + ": " + message);
        sendLog(completeMessage);
    }

    public static void sendInfo(JavaPlugin plugin, String message) {
        if(!isMessage(message))
            return;
        String pluginName = plugin.getName();
        String completeMessage = color("&7[&9Info&7] " + pluginName + ": " + message);
        sendLog(completeMessage);
    }

    private static void sendLog(String completeMessage) {
        Bukkit.getConsoleSender().sendMessage(color(completeMessage));
    }

    private static boolean isMessage(String msg){
        if(msg.isBlank() || msg.isEmpty())
            return false;
        return true;
    }
}
