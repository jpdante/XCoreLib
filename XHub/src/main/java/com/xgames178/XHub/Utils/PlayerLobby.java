package com.xgames178.XHub.Utils;

import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.*;
import com.xgames178.XHub.Menus.GadgetsMenu;
import com.xgames178.XHub.Menus.MiniGamesMenu;
import com.xgames178.XHub.Menus.ParticlesMenu;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;

public class PlayerLobby {

    public static void loadPlayer(PlayerProfile playerProfile) {
        playerProfile.profileData.menus.add(new MiniGamesMenu(playerProfile));
        playerProfile.profileData.menus.add(new GadgetsMenu(playerProfile));
        playerProfile.profileData.menus.add(new ParticlesMenu(playerProfile));
        playerProfile.player.setGameMode(GameMode.ADVENTURE);
        playerProfile.player.getInventory().clear();
        playerProfile.player.setFlying(false);
        playerProfile.player.setAllowFlight(true);
        playerProfile.player.teleport(new Location(Bukkit.getServer().getWorld("world"), -1097.0d, 92.0d, 1228.0d, -90.6f, -0.6f));
        UtilInv.setItem(playerProfile.player, UtilItem.getItemStack(Material.COMPASS, 1, LanguageManager.getTranslation(playerProfile, "item.gamemenu"), "", ""), 0);
        UtilInv.setItem(playerProfile.player, UtilItem.getItemStack(Material.NETHER_STAR, 1, LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu"), "", ""), 4);
        UtilInv.setItem(playerProfile.player, UtilItem.getItemStack(Material.DRAGON_EGG, 1, LanguageManager.getTranslation(playerProfile, "item.profilemenu"), "", ""), 8);
        UtilTextTab.display("§a§lFight§c§lCraft §b§lArcade", "     §6§lloja.fightcraftbr.com     ", playerProfile.player);
        UtilTextMiddle.display("§a§lBem-vindo ao", "§aFight§cCraft §b§lArcade", 10, 25, 10, playerProfile.player);
    }
}
