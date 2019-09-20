package com.xgames178.XCore.Player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class MenuInventory {
    public Inventory inventory;
    public MenuInventory(PlayerProfile playerProfile) {}
    public void openMenu(Player player) {}
    public void updateMenu(PlayerProfile playerProfile) {}
}
