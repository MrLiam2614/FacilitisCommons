package me.mrliam2614.facilitiscommons.mobs.ally;

import me.mrliam2614.facilitiscommons.mobs.CompleteMob;
import me.mrliam2614.facilitiscommons.utils.Positions;
import org.bukkit.Material;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class AllayDiamondSword implements CompleteMob {
    @Override
    public Entity spawnMob(Positions positions) {
        EntityType entityType = EntityType.ALLAY;
        Entity entity = positions.getWorld().spawnEntity(positions.getLocation(), entityType);

        Allay mob = (Allay) entity;
        mob.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));

        return entity;
    }
}
