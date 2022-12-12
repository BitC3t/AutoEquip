package me.floof.here.autoequip.controllers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArmorController {

    private Cache<Player, Integer> cache = CacheBuilder.newBuilder()
                                            .expireAfterWrite(2, TimeUnit.SECONDS)
                                            .build();
    
    
    // Armor Types
    private List<Material> plates = Arrays.asList(Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE, 
            Material.GOLDEN_CHESTPLATE, Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.NETHERITE_CHESTPLATE);
    private List<Material> legs = Arrays.asList(Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS, Material.NETHERITE_LEGGINGS);
    private List<Material> helmets = Arrays.asList(Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.NETHERITE_HELMET);
    private List<Material> boots = Arrays.asList(Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS, Material.IRON_BOOTS, Material.LEATHER_BOOTS, Material.NETHERITE_BOOTS);

    // Main-Hand
    public void replaceArmor(Player player, ItemStack newArmor, int slotNum) {
        // Check if Player has Armor
        ArmorType a = ArmorType.getArmorType(newArmor.getType());

        if(a == null) {
            return;
        }

        ArmorType armorType = null;
        ItemStack oldArmor = null;

        for(ItemStack i : player.getInventory().getArmorContents()) {
            if(i == null) {
                continue;
            }

            armorType = ArmorType.getArmorType(i.getType());

            if(armorType == null) {
                continue;
            }

            if(armorType.equals(a)) {
                oldArmor = i;

                break;
            }
        }

        cache.put(player, 1);

        if(a.equals(ArmorType.CHEST)) {
            player.getInventory().setChestplate(newArmor);
        } else if (a.equals(ArmorType.BOOTS)) {
            player.getInventory().setBoots(newArmor);
        } else if (a.equals(ArmorType.HELMET)) {
            player.getInventory().setHelmet(newArmor);
        } else if (a.equals(ArmorType.LEGS)) {
            player.getInventory().setLeggings(newArmor);
        }

        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);

        if(armorType == null || oldArmor == null) {
            return;
        }

        player.getInventory().setItem(slotNum, oldArmor);
    }

    // Off-Hand
    public void replaceArmor(Player player, ItemStack newArmor) {
        // Check if Player has Armor
        ArmorType a = ArmorType.getArmorType(newArmor.getType());

        if(a == null) {
            return;
        }

        ArmorType armorType = null;
        ItemStack oldArmor = null;

        for(ItemStack i : player.getInventory().getArmorContents()) {
            if(i == null) {
                continue;
            }

            armorType = ArmorType.getArmorType(i.getType());

            if(armorType == null) {
                continue;
            }

            if(armorType.equals(a)) {
                oldArmor = i;

                break;
            }
        }

        cache.put(player, 1);

        if(a.equals(ArmorType.CHEST)) {
            player.getInventory().setChestplate(newArmor);
        } else if (a.equals(ArmorType.BOOTS)) {
            player.getInventory().setBoots(newArmor);
        } else if (a.equals(ArmorType.HELMET)) {
            player.getInventory().setHelmet(newArmor);
        } else if (a.equals(ArmorType.LEGS)) {
            player.getInventory().setLeggings(newArmor);
        }

        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);

        if(armorType == null || oldArmor == null) {
            return;
        }

        player.getInventory().setItemInOffHand(oldArmor);
    }

    public boolean isOnCache(Player player) {
        if(cache.asMap().containsKey(player)) {
            return true;
        } else {
            return false;
        }
    }
}
