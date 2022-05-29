package me.mrliam2614.facilitiscommons.utils;

import lombok.Getter;
import lombok.Setter;
import me.mrliam2614.facilitiscommons.FacilitisCommons;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

@Getter
@Setter
public class Positions {
    private UUID worldID;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public Positions(UUID worldID, double x, double y, double z) {
        this(worldID, x, y, z, 0, 0);
    }

    public Positions(UUID worldID, double x, double y, double z, float pitch, float yaw) {
        this.worldID = worldID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public boolean isWorldLoaded() {
        if (Bukkit.getWorld(worldID) == null) {
            Messages.sendError(FacilitisCommons.getFacilitisCommons().getJavaPlugin(), "World " + worldID + " cannot be loaded.");
            return false;
        }

        World world = Bukkit.getWorld(worldID);
        return world != null && Bukkit.getWorld(world.getUID()) != null;
    }

    public World getWorld() {
        if (isWorldLoaded())
            return Bukkit.getWorld(worldID);
        return null;
    }

    public void setWorld(World world) {
        worldID = world.getUID();
    }
}
