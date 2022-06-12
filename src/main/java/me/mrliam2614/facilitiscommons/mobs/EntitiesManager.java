package me.mrliam2614.facilitiscommons.mobs;

import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.entity.Entity;

import java.lang.reflect.Method;

public class EntitiesManager {
    public static Entity spawnMob(Positions positions, Entities entities) {
        try {
            Method method = entities.getClazz().getMethod("spawnMob", Positions.class);
            method.setAccessible(true);
            return (Entity) method.invoke(entities.getClazz().getDeclaredConstructor().newInstance(), positions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
