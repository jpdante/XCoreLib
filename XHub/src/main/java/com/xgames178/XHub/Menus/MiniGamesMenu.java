package com.xgames178.XHub.Menus;

import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Player.MenuInventory;
import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.UtilItem;
import com.xgames178.XCore.Utils.UtilMenu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MiniGamesMenu extends MenuInventory{

    public MiniGamesMenu(PlayerProfile playerProfile) {
        super(playerProfile);
        inventory = UtilMenu.createInventory(UtilMenu.InventorySize.Medium, "§l" + LanguageManager.getTranslation(playerProfile, "item.gamemenu"));
        //UtilMenu.setItem(inventory, UtilItem.getItemStack(Material.DIAMOND_SWORD, 1, "§4§lFullPvP"), 2, 2);
        UtilMenu.setItem(inventory, UtilItem.getItemStack(Material.STAINED_GLASS, 1, (byte)3,  "§b§lEletricFloor"), 4, 2);
        //UtilMenu.setItem(inventory, UtilItem.getItemStack(Material.DIAMOND_SWORD, 1, "§a§lS§b§lk§c§ly§1§lF§5§la§6§ll§e§ll"), 6, 2);
    }

    @Override
    public void openMenu(Player player) {
        player.closeInventory();
        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 2f, 2f);
    }
}
