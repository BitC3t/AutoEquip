package me.floof.here.autoequip.controllers;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum ArmorType {
    CHEST((List<Material>) Arrays.asList(Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE, Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.NETHERITE_CHESTPLATE)),
    LEGS((List<Material>) Arrays.asList(Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS,
            Material.GOLDEN_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS, Material.NETHERITE_LEGGINGS)),
    HELMET((List<Material>) Arrays.asList(Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET,
            Material.GOLDEN_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.NETHERITE_HELMET)),
    BOOTS((List<Material>) Arrays.asList(Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS,
            Material.GOLDEN_BOOTS, Material.IRON_BOOTS, Material.LEATHER_BOOTS, Material.NETHERITE_BOOTS));

    private List<Material> values;

    ArmorType(List<Material> value) {
        this.values = value;
    }

    public List<Material> getArmorTypes() {
        return values;
    }

    public static ArmorType getArmorType(Material material) {
        if(ArmorType.LEGS.getArmorTypes().contains(material)) {
            return ArmorType.LEGS;
        }

        if(ArmorType.CHEST.getArmorTypes().contains(material)) {
            return ArmorType.CHEST;
        }

        if(ArmorType.HELMET.getArmorTypes().contains(material)) {
            return ArmorType.HELMET;
        }

        if(ArmorType.BOOTS.getArmorTypes().contains(material)) {
            return ArmorType.BOOTS;
        }

        return null;
    }
}

