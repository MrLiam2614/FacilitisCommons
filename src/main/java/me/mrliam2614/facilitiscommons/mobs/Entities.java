package me.mrliam2614.facilitiscommons.mobs;

import lombok.Getter;
import me.mrliam2614.facilitiscommons.mobs.ally.NormalAllay;
import me.mrliam2614.facilitiscommons.mobs.ally.AllayDiamondSword;
import me.mrliam2614.facilitiscommons.mobs.ally.AllayLantern;
import me.mrliam2614.facilitiscommons.mobs.ally.AllayStonePickaxe;
import me.mrliam2614.facilitiscommons.mobs.zombie.BabyZombie;
import me.mrliam2614.facilitiscommons.mobs.zombie.NormalZombie;
import me.mrliam2614.facilitiscommons.mobs.zombie.villagers.baby.*;
import me.mrliam2614.facilitiscommons.mobs.zombie.villagers.normal.*;

@Getter
public enum Entities {
    //NormalZombie
    BabyZombie("baby_zombie", BabyZombie.class),
    Zombie("zombie", NormalZombie.class),
    //NormalZombie Villagers
    ZombieVillager("zombie_villager", ZombieVillagerNone.class),
    ZombieVillagerArmored("zombie_villager_armored", ZombieVillagerArmored.class),
    ZombieVillagerButcher("zombie_villager_butcher", ZombieVillagerButcher.class),
    ZombieVillagerCartographer("zombie_villager_cartographer", ZombieVillagerCartographer.class),
    ZombieVillagerCleric("cleric", ZombieVillagerCleric.class),
    ZombieVillagerFarmer("zombie_villager_farmer", ZombieVillagerFarmer.class),
    ZombieVillagerFisherman("zombie_villager_fisherman", ZombieVillagerFisherman.class),
    ZombieVillagerFletcher("zombie_villager_fletcher", ZombieVillagerFletcher.class),
    ZombieVillagerLeatherworker("zombie_villager_leatherworker", ZombieVillagerLeatherworker.class),
    ZombieVillagerLibrarian("zombie_villager_librarian", ZombieVillagerLibrarian.class),
    ZombieVillagerMason("zombie_villager_mason", ZombieVillagerMason.class),
    ZombieVillagerNitwit("zombie_villager_nitwit", ZombieVillagerNitwit.class),
    ZombieVillagerNone("zombie_villager_none", ZombieVillagerNone.class),
    ZombieVillagerShepherd("zombie_villager_shepherd", ZombieVillagerShepherd.class),
    ZombieVillagerToolsmith("zombie_villager_toolsmith", ZombieVillagerToolsmith.class),
    ZombieVillagerWeaponsmith("zombie_villager_weaponsmith", ZombieVillagerWeaponsmith.class),
    //NormalZombie Villager Baby
    BabyZombieVillager("zombie_villager", BabyZombieVillagerNone.class),
    BabyZombieVillagerArmored("zombie_villager_armored", BabyZombieVillagerArmored.class),
    BabyZombieVillagerButcher("zombie_villager_butcher", BabyZombieVillagerButcher.class),
    BabyZombieVillagerCartographer("zombie_villager_cartographer", BabyZombieVillagerCartographer.class),
    BabyZombieVillagerCleric("cleric", BabyZombieVillagerCleric.class),
    BabyZombieVillagerFarmer("zombie_villager_farmer", BabyZombieVillagerFarmer.class),
    BabyZombieVillagerFisherman("zombie_villager_fisherman", BabyZombieVillagerFisherman.class),
    BabyZombieVillagerFletcher("zombie_villager_fletcher", BabyZombieVillagerFletcher.class),
    BabyZombieVillagerLeatherworker("zombie_villager_leatherworker", BabyZombieVillagerLeatherworker.class),
    BabyZombieVillagerLibrarian("zombie_villager_librarian", BabyZombieVillagerLibrarian.class),
    BabyZombieVillagerMason("zombie_villager_mason", BabyZombieVillagerMason.class),
    BabyZombieVillagerNitwit("zombie_villager_nitwit", BabyZombieVillagerNitwit.class),
    BabyZombieVillagerNone("zombie_villager_none", BabyZombieVillagerNone.class),
    BabyZombieVillagerShepherd("zombie_villager_shepherd", BabyZombieVillagerShepherd.class),
    BabyZombieVillagerToolsmith("zombie_villager_toolsmith", BabyZombieVillagerToolsmith.class),
    BabyZombieVillagerWeaponsmith("zombie_villager_weaponsmith", BabyZombieVillagerWeaponsmith.class),
    Allay("allay", NormalAllay.class),
    AllayDiamondSword("allay_diamond_sword", AllayDiamondSword.class),
    AllayLatern("allay_lantern", AllayLantern.class),
    AllayStonePick("allay_stone_pickaxe", AllayStonePickaxe.class);

    private final String name;
    private final Class<? extends CompleteMob> clazz;

    Entities(String name, Class<? extends CompleteMob> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Entities getByName(String name) {
        for (Entities e : Entities.values()) {
            if (e.getName().equalsIgnoreCase(name))
                return e;
        }
        for (Entities e : Entities.values()) {
            if (e.getName().replaceAll("_", "").equalsIgnoreCase(name))
                return e;
        }
        return null;
    }
}