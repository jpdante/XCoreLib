package com.xgames178.XHub.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.xgames178.XCore.Events.PlayerProfileLoaded;
import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.Utils.Rank;
import com.xgames178.XCore.Utils.UtilItem;
import com.xgames178.XHub.Gadgets.Types.GadgetType;
import com.xgames178.XHub.Utils.PlayerLobby;
import com.xgames178.XHub.XHub;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock();
        if (block.getType() == Material.SOIL) {
            event.setCancelled(true);
            event.getPlayer().teleport(event.getFrom().add(0.5,0.5,0.5));
        }
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        PlayerProfile profile = InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId());
        if(profile.profileData.rank == Rank.DEVELOPER || profile.profileData.rank == Rank.OWNER || profile.profileData.rank == Rank.MAPDEV) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        PlayerProfile profile = InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId());
        if(profile.profileData.rank == Rank.DEVELOPER || profile.profileData.rank == Rank.OWNER || profile.profileData.rank == Rank.MAPDEV) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        PlayerProfile profile = InternalPlayerCache.playerProfiles.get(event.getWhoClicked().getUniqueId());
        if(profile.profileData.rank == Rank.DEVELOPER || profile.profileData.rank == Rank.OWNER || profile.profileData.rank == Rank.MAPDEV) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getItem()==null)return;
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(UtilItem.isEqualItem(event.getItem(), Material.COMPASS)) {
                event.setCancelled(true);
                InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId()).profileData.menus.get(0).openMenu(event.getPlayer());
            } else if(UtilItem.isEqualItem(event.getItem(), Material.NETHER_STAR)) {
                event.setCancelled(true);
                InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId()).profileData.menus.get(1).openMenu(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) return;
        PlayerProfile playerProfile = InternalPlayerCache.playerProfiles.get(event.getWhoClicked().getUniqueId());
        if(playerProfile.profileData.menus.get(0).inventory.hashCode() == event.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.STAINED_GLASS, "§b§lEletricFloor")) {
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.CAT_MEOW, 1f, 2f);
                event.getWhoClicked().closeInventory();
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("eletricfloor");
                playerProfile.player.sendPluginMessage(XHub.getPlugin(), "BungeeCord", out.toByteArray());
            }
        } else if(playerProfile.profileData.menus.get(1).inventory.hashCode() == event.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.FIREWORK)) {
                playerProfile.profileData.menus.get(2).updateMenu(playerProfile);
                playerProfile.profileData.menus.get(2).openMenu(playerProfile.player);
            }
        } else if(playerProfile.profileData.menus.get(2).inventory.hashCode() == event.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), UtilItem.getItemStack(Material.INK_SACK, 1, (byte) 8, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.helix")))) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                XHub.getGadgetManager().setActive(playerProfile.player, XHub.getGadgetManager().getGadgets(GadgetType.Particle).get(0));
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
            } else if(UtilItem.isEqualItem(event.getCurrentItem(), UtilItem.getItemStack(Material.INK_SACK, 1, (byte) 8, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.rain")))) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                XHub.getGadgetManager().setActive(playerProfile.player, XHub.getGadgetManager().getGadgets(GadgetType.Particle).get(1));
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
            } else if(UtilItem.isEqualItem(event.getCurrentItem(), UtilItem.getItemStack(Material.INK_SACK, 1, (byte) 8, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.firerings")))) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                XHub.getGadgetManager().setActive(playerProfile.player, XHub.getGadgetManager().getGadgets(GadgetType.Particle).get(2));
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
            } else if(UtilItem.isEqualItem(event.getCurrentItem(), UtilItem.getItemStack(Material.INK_SACK, 1, (byte) 8, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.groundhelix")))) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                XHub.getGadgetManager().setActive(playerProfile.player, XHub.getGadgetManager().getGadgets(GadgetType.Particle).get(3));
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
            } else if(UtilItem.isEqualItem(event.getCurrentItem(), UtilItem.getItemStack(Material.INK_SACK, 1, (byte) 8, "§b" + LanguageManager.getTranslation(playerProfile, "item.gadgetsmenu.particles.shield")))) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                XHub.getGadgetManager().setActive(playerProfile.player, XHub.getGadgetManager().getGadgets(GadgetType.Particle).get(4));
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.LEVEL_UP, 1f, 2f);
            } else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.REDSTONE)) {
                XHub.getGadgetManager().DisableAll(playerProfile.player);
                playerProfile.player.playSound(playerProfile.player.getLocation(), Sound.DOOR_CLOSE, 1f, 2f);
            }
        } else {
            if(playerProfile.profileData.rank == Rank.OWNER || playerProfile.profileData.rank == Rank.DEVELOPER) return;
            event.setCancelled(true);
        }
        /*PlayerProfile playerProfile = Cache.profiles.get(player);
        if(event.getInventory().hashCode() == GadgetMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.FIREWORK, "§6§lParticles")) {
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 0.5f, 1f);
                event.getWhoClicked().closeInventory();
                playerProfile.particlesMenu.updateInventory(playerProfile.profileData);
                event.getWhoClicked().openInventory(playerProfile.particlesMenu.getInventory());
            }
        } else if (event.getInventory().hashCode() == playerProfile.particlesMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.REDSTONE, "§cDisable Particles")) disableParticle(playerProfile);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Helix")) selectParticle(playerProfile, 0);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Rain")) selectParticle(playerProfile, 1);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Fire Rings")) selectParticle(playerProfile, 2);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Ground Helix")) selectParticle(playerProfile, 3);
            else if(UtilItem.isEqualItem(event.getCurrentItem(), Material.INK_SACK, "§bParticle Shield")) selectParticle(playerProfile, 4);
        } else if (event.getInventory().hashCode() == MiniGamesMenu.getInventory().hashCode()) {
            event.setCancelled(true);
            if(UtilItem.isEqualItem(event.getCurrentItem(), Material.STAINED_GLASS, "§b§lEletric Floor")) {
                player.playSound(player.getLocation(), Sound.CAT_MEOW, 1f, 2f);
                event.getWhoClicked().closeInventory();
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF("eletricfloor");
                player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
            }
        }*/
    }

    @EventHandler
    public void onPlayerProfileLoaded(PlayerProfileLoaded event) {
        PlayerLobby.loadPlayer(InternalPlayerCache.playerProfiles.get(event.getUUID()));
    }
}
