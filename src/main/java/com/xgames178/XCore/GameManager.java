package com.xgames178.XCore;

import com.xgames178.XCore.Game.Game;
import com.xgames178.XCore.Game.GameServerConfig;
import com.xgames178.XCore.Utils.UtilInv;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

/**
 * Created by jpdante on 08/05/2017.
 */
public class GameManager implements Listener {

    private GameServerConfig _serverConfig;
    private Plugin _plugin;
    private Game _game;

    public GameManager(Plugin plugin, GameServerConfig serverConfig) {
        _serverConfig = serverConfig;
        _plugin = plugin;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void MessageMOTD(ServerListPingEvent event) {
        event.setMaxPlayers(_serverConfig.MaxPlayers);
        if(_game != null) {
            event.setMotd(_game.GetState().toString());
        }
    }

    public boolean IsTeamBalance()
    {
        return _serverConfig.TeamForceBalance;
    }
    public void Clear(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(false);
        player.setFlySpeed(0.1F);
        UtilInv.Clear(player);
        ((CraftEntity) player).getHandle().getDataWatcher().watch(0, Byte.valueOf((byte) 0));
        player.setSprinting(false);
        player.setSneaking(false);
        player.setFoodLevel(20);
        player.setSaturation(3f);
        player.setExhaustion(0f);
        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.setFireTicks(0);
        player.setFallDistance(0);
        player.eject();
        player.leaveVehicle();
        player.setLevel(0);
        player.setExp(0f);
        ((CraftPlayer) player).getHandle().k = true;
        for (PotionEffect potion : player.getActivePotionEffects()) player.removePotionEffect(potion.getType());
    }
    public Game GetGame()
    {
        return _game;
    }
    public void SetGame(Game game)
    {
        _game = game;
    }
    public int GetPlayerMin()
    {
        return GetServerConfig().MinPlayers;
    }
    public int GetPlayerFull()
    {
        return GetServerConfig().MaxPlayers;
    }
    public GameServerConfig GetServerConfig()
    {
        return _serverConfig;
    }
    public Plugin getPlugin() {
        return _plugin;
    }
    @EventHandler
    public void addPlayerToGame(PlayerJoinEvent event) {

    }
    @EventHandler
    public void removePlayerFromGame(PlayerQuitEvent event) {

    }
}
