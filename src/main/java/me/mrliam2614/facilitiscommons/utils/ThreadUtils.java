package me.mrliam2614.facilitiscommons.utils;

import me.mrliam2614.facilitiscommons.FacilitisCommons;
import org.bukkit.Bukkit;

public class ThreadUtils {
    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(FacilitisCommons.getFacilitisCommons().getJavaPlugin(), runnable);
    }
}
