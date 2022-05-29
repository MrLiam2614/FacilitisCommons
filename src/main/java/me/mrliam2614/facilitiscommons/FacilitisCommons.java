package me.mrliam2614.facilitiscommons;

import lombok.Getter;
import me.mrliam2614.facilitiscommons.storage.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class FacilitisCommons {
    @Getter
    private static FacilitisCommons facilitisCommons = null;
    @Getter
    private final JavaPlugin javaPlugin;

    private Data messages;
    private Data fullData;

    public FacilitisCommons(JavaPlugin javaPlugin) {
        facilitisCommons = this;
        this.javaPlugin = javaPlugin;

        fullData = new Data();
        messages = new Data();

        List<String> test = new ArrayList<>();

        messages.set("list", test);
        messages.set("version", 1);
        messages.set("lang", "it");

        fullData.set("messages", messages);
    }

    public void enable() {

    }
}
