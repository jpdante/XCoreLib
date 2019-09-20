package com.xgames178.XHub.Gadgets.Types;

import com.xgames178.XCore.Utils.UtilServer;
import com.xgames178.XHub.Gadgets.GadgetManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;

/**
 * Created by jpdante on 05/05/2017.
 */
public abstract class Gadget implements Listener {
    public GadgetManager Manager;
    private GadgetType _gadgetType;
    private String _name;
    public HashSet<Player> _active = new HashSet<>();
    public Gadget(GadgetManager manager, GadgetType type, String name) {
        Manager = manager;
        _gadgetType = type;
        _name = name;
    }
    public HashSet<Player> GetActive()
    {
        return _active;
    }
    public boolean IsActive(Player player)
    {
        return _active.contains(player);
    }
    public boolean shouldDisplay(Player player) {
        return Manager.shouldDisplay(player);
    }
    public GadgetType getGadgetType()
    {
        return _gadgetType;
    }
    public void Enable(Player player) {
        EnableCustom(player);
        Manager.setActive(player, this);
    }
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event)
    {
        Disable(event.getPlayer());
    }
    public void DisableForAll() {
        for (Player player : UtilServer.getPlayers()) Disable(player);
    }
    public void Disable(Player player) {
        if (IsActive(player)) Manager.removeActive(player, this);
        DisableCustom(player);
    }
    public abstract void EnableCustom(Player player);
    public abstract void DisableCustom(Player player);
}
