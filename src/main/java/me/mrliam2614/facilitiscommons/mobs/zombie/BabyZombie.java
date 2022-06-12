package me.mrliam2614.facilitiscommons.mobs.zombie;

import me.mrliam2614.facilitiscommons.mobs.CompleteMob;
import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class BabyZombie implements CompleteMob {
    @Override
    public Entity spawnMob(Positions positions) {
        EntityType entityType = EntityType.ZOMBIE;
        Entity entity = positions.getWorld().spawnEntity(positions.getLocation(), entityType);

        Zombie mob = (Zombie) entity;
        mob.setBaby();

        return entity;
    }
}
