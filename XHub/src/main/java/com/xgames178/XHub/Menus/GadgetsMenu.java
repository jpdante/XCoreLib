package com.xgames178.XHub.Menus;

import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Player.MenuInventory;
import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.UtilItem;
import com.xgames178.XCore.Utils.UtilMenu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GadgetsMenu extends MenuInventory {
    public GadgetsMenu(PlayerProfile playerProfile) {
        super(playerProfile);
        inventory = UtilMenu.createInventory(UtilMenu.InventorySize.Medium, "§l" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu"));
        UtilMenu.setItem(inventory, UtilItem.getItemStack(Material.FIREWORK, 1, "§6§l" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles")), 2, 2);
    }

    @Override
    public void openMenu(Player player) {
        player.closeInventory();
        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 2f, 2f);
    }
}
