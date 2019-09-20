package com.xgames178.XHub.Menus;

import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Player.MenuInventory;
import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.UtilItem;
import com.xgames178.XCore.Utils.UtilMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ParticlesMenu extends MenuInventory {

    public ParticlesMenu(PlayerProfile playerProfile) {
        super(playerProfile);
        inventory = UtilMenu.createInventory(UtilMenu.InventorySize.Medium, "§l" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles"));
        UtilMenu.setItem(inventory, UtilItem.getItemStack(Material.REDSTONE, 1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.disable")), 4, 4);
    }

    @Override
    public void openMenu(Player player) {
        player.closeInventory();
        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 2f, 2f);
    }

    @Override
    public void updateMenu(PlayerProfile playerProfile) {
        if(playerProfile.profileData.enabled_collectibles.get(0)) {
            byte data = 8;
            if(playerProfile.profileData.current_gadget == 1) data = 10;
            inventory.setItem(10, UtilItem.getItemStack(Material.INK_SACK,1, data, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.helix")));
        } else inventory.setItem(10, UtilItem.getItemStack(Material.BARRIER,1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.unconquered")));

        if(playerProfile.profileData.enabled_collectibles.get(1)) {
            byte data = 8;
            if(playerProfile.profileData.current_gadget == 2) data = 10;
            inventory.setItem(12, UtilItem.getItemStack(Material.INK_SACK,1, data, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.rain")));
        } else inventory.setItem(12, UtilItem.getItemStack(Material.BARRIER,1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.unconquered")));

        if(playerProfile.profileData.enabled_collectibles.get(2)) {
            byte data = 8;
            if(playerProfile.profileData.current_gadget == 3) data = 10;
            inventory.setItem(14, UtilItem.getItemStack(Material.INK_SACK,1, data, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.firerings")));
        } else inventory.setItem(14, UtilItem.getItemStack(Material.BARRIER,1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.unconquered")));

        if(playerProfile.profileData.enabled_collectibles.get(3)) {
            byte data = 8;
            if(playerProfile.profileData.current_gadget == 4) data = 10;
            inventory.setItem(16, UtilItem.getItemStack(Material.INK_SACK,1, data, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.groundhelix")));
        } else inventory.setItem(16, UtilItem.getItemStack(Material.BARRIER,1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.unconquered")));

        if(playerProfile.profileData.enabled_collectibles.get(4)) {
            byte data = 8;
            if(playerProfile.profileData.current_gadget == 5) data = 10;
            inventory.setItem(28, UtilItem.getItemStack(Material.INK_SACK,1, data, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.shield")));
        } else inventory.setItem(28, UtilItem.getItemStack(Material.BARRIER,1, "§c" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.unconquered")));
    }
}
