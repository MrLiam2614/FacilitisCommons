package me.mrliam2614.facilitiscommons.mobs.zombie.villagers.baby;

import me.mrliam2614.facilitiscommons.mobs.CompleteMob;
import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

public class BabyZombieVillagerWeaponsmith implements CompleteMob {
    @Override
    public Entity spawnMob(Positions positions) {
        EntityType entityType = EntityType.ZOMBIE_VILLAGER;
        Entity entity = positions.getWorld().spawnEntity(positions.getLocation(), entityType);

        ZombieVillager mob = (ZombieVillager) entity;
        mob.setVillagerProfession(Villager.Profession.WEAPONSMITH);
        mob.setBaby();
        mob.setVillagerType(Villager.Type.PLAINS);

        return entity;
    }
}
