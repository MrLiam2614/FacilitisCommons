package me.mrliam2614.facilitiscommons.mobs.ally;

import me.mrliam2614.facilitiscommons.mobs.CompleteMob;
import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class NormalAllay implements CompleteMob {
    @Override
    public Entity spawnMob(Positions positions) {
        EntityType entityType = EntityType.ALLAY;
        Entity entity = positions.getWorld().spawnEntity(positions.getLocation(), entityType);

        return entity;
    }
}
