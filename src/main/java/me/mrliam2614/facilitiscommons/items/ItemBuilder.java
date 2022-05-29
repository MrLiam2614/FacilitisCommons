package me.mrliam2614.facilitiscommons.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    public ItemStack createItem(String itemName, ItemStack itemStack, String... lore) {
        List<String> loreList = new ArrayList<>();
        for (String loreLine : lore) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', loreLine));
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
        itemMeta.setLore(loreList);

        if (itemStack.getAmount() <= 0) {
            itemStack.setAmount(1);
        }
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack createHead(String itemName, String base64, String... lore) {
        List<String> loreList = new ArrayList<>();
        for (String loreLine : lore) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', loreLine));
        }

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        try {
            Method setProfile = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            setProfile.setAccessible(true);

            GameProfile profile = new GameProfile(UUID.randomUUID(), "custom-skull");
            profile.getProperties().put("textures", new Property("textures", base64));

            setProfile.invoke(skullMeta, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
        skullMeta.setLore(loreList);

        if (itemStack.getAmount() <= 0) {
            itemStack.setAmount(1);
        }
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public ItemStack createHead(String itemName, OfflinePlayer offlinePlayer, String... lore) {
        List<String> loreList = new ArrayList<>();
        for (String loreLine : lore) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', loreLine));
        }
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(offlinePlayer.getName());

        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
        skullMeta.setLore(loreList);

        if (itemStack.getAmount() <= 0) {
            itemStack.setAmount(1);
        }
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
}
