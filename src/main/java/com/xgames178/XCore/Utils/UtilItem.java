package com.xgames178.XCore.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by jpdante on 04/05/2017.
 */
public class UtilItem {
    public static ItemStack getItemStack(Material material, int count, byte byteid, String name, String... lore) {
        ItemStack itemStack = new ItemStack(material, count, byteid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int count, String name, String... lore) {
        ItemStack itemStack = new ItemStack(material, count);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int count, byte byteid, String name) {
        ItemStack itemStack = new ItemStack(material, count, byteid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int count, String name) {
        ItemStack itemStack = new ItemStack(material, count);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int count, byte byteid) {
        ItemStack itemStack = new ItemStack(material, count, byteid);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int count) {
        ItemStack itemStack = new ItemStack(material, count);
        return itemStack;
    }

    @Deprecated
    public static ItemStack getItemStack(int id) {
        ItemStack itemStack = new ItemStack(id);
        return itemStack;
    }

    public static boolean isEqualItem(ItemStack is1, ItemStack is2) {
        if(is1.getType() == is2.getType()) {
            if(is1.getItemMeta().getDisplayName().equalsIgnoreCase(is2.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEqualItem(ItemStack is1, Material type) {
        if(is1.getType() == type) return true;
        return false;
    }

    public static boolean isEqualItem(ItemStack is1, Material type, String name) {
        if(is1.getType() == type) {
            if(is1.getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
