package com.xgames178.XCore.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class UtilMenu {

    public enum InventorySize {
        Tiny(9),
        Small(27),
        Medium(54),
        Big(63);

        int Size = 9;
        InventorySize(int size) {
            Size = size;
        }
        public int getSize() {
            return Size;
        }
    }

    public static Inventory createInventory(InventoryType type, String title) {
        return Bukkit.getServer().createInventory(null, type, title);
    }

    public static Inventory createInventory(int size, String title) {
        return Bukkit.getServer().createInventory(null, size, title);
    }

    public static Inventory createInventory(InventorySize size, String title) {
        return Bukkit.getServer().createInventory(null, size.getSize(), title);
    }

    public static void setItem(Inventory inventory, ItemStack itemStack) {
        inventory.addItem(itemStack);
    }

    public static void setItem(Inventory inventory, ItemStack itemStack, int slot) {
        inventory.setItem(slot, itemStack);
    }

    public static void setItem(Inventory inventory, ItemStack itemStack, int horizontal, int vertical) {
        inventory.setItem(horizontal + (vertical * 9), itemStack);
    }

    public static ItemStack getItem(Inventory inventory, int slot) {
        return inventory.getItem(slot);
    }

    public static ItemStack getItem(Inventory inventory, int horizontal, int vertical) {
        return inventory.getItem(horizontal + (vertical * 9));
    }

    public static void clear(Inventory inventory) {
        inventory.clear();
    }

    public static boolean contains(Inventory inventory, Material item, byte data, int required) {
        return contains(inventory, null, item, data, required);
    }

    public static boolean contains(Inventory inventory, String itemNameContains, Material item, byte data, int required) {
        return contains(inventory, itemNameContains, item, data, required, true, true);
    }

    public static boolean contains(Inventory inventory, String itemNameContains, Material item, byte data, int required, boolean checkArmor, boolean checkCursor) {
        for (ItemStack stack : getItems(inventory)) {
            if (required <= 0) return true;
            if (stack == null) continue;
            if (stack.getType() != item) continue;
            if (stack.getAmount() <= 0) continue;
            if (data >=0 && stack.getData() != null && stack.getData().getData() != data) continue;
            if (itemNameContains != null && (stack.getItemMeta().getDisplayName() == null || !stack.getItemMeta().getDisplayName().contains(itemNameContains))) continue;
            required -= stack.getAmount();
        }
        if (required <= 0) return true;
        return false;
    }

    public static ArrayList<ItemStack> getItems(Inventory inventory) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                items.add(item.clone());
            }
        }
        return items;
    }
}
