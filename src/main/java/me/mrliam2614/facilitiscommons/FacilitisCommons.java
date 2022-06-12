package me.mrliam2614.facilitiscommons;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class FacilitisCommons {
    @Getter
    private static FacilitisCommons facilitisCommons = null;
    @Getter
    private final JavaPlugin javaPlugin;

    public FacilitisCommons(JavaPlugin javaPlugin) {
        facilitisCommons = this;
        this.javaPlugin = javaPlugin;
    }
}
