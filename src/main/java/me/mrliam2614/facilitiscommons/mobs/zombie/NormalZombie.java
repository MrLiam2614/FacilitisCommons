package me.mrliam2614.facilitiscommons.mobs.zombie;

import me.mrliam2614.facilitiscommons.mobs.CompleteMob;
import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class NormalZombie implements CompleteMob {
    @Override
    public Entity spawnMob(Positions positions) {
        EntityType entityType = EntityType.ZOMBIE;
        Entity entity = positions.getWorld().spawnEntity(positions.getLocation(), entityType);

        org.bukkit.entity.Zombie mob = (org.bukkit.entity.Zombie) entity;
        mob.setAdult();

        return entity;
    }
}
