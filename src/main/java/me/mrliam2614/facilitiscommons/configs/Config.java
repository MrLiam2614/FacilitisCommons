package me.mrliam2614.facilitiscommons.configs;

import lombok.Getter;
import me.mrliam2614.facilitiscommons.utils.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Config {
    private final String source;
    private final String end;
    private final JavaPlugin plugin;
    private File file;
    @Getter
    private FileConfiguration fileConfig;

    public Config(String fileSource, JavaPlugin plugin) {
        this.source = fileSource;
        this.end = fileSource;
        this.plugin = plugin;
    }

    public Config(String fileSource, String endFile, JavaPlugin plugin) {
        this.source = fileSource;
        this.end = endFile;
        this.plugin = plugin;
    }

    public void init() {
        file = new File(plugin.getDataFolder(), end);
        if (!file.exists()) {
            File sourceF = new File(plugin.getDataFolder(), source);
            file.getParentFile().mkdirs();
            plugin.saveResource(source, false);
            sourceF.renameTo(file);
        }

        fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (Exception e) {
            Messages.sendError(plugin, e.toString());
        }
    }

    public void save() {
        try {
            fileConfig.save(file);
        } catch (Exception e) {
            Messages.sendError(plugin, e.toString());
        }
    }
}
