package com.xgames178.XHub.Gadgets;

import com.xgames178.XCore.Utils.NautHashMap;
import com.xgames178.XCore.Utils.UtilMath;
import com.xgames178.XCore.Utils.UtilServer;
import com.xgames178.XCore.Utils.UtilTime;
import com.xgames178.XHub.Gadgets.Gadgets.*;
import com.xgames178.XHub.Gadgets.Types.Gadget;
import com.xgames178.XHub.Gadgets.Types.GadgetType;
import com.xgames178.XHub.Gadgets.Types.ParticleGadget;
import com.xgames178.XHub.XHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpdante on 05/05/2017.
 */
public class GadgetManager implements Listener {

    private NautHashMap<GadgetType, List<Gadget>> _gadgets;
    private NautHashMap<Player, Long> _lastMove = new NautHashMap<Player, Long>();
    private NautHashMap<Player, NautHashMap<GadgetType, Gadget>> _playerActiveGadgetMap = new NautHashMap<Player, NautHashMap<GadgetType, Gadget>>();
    private List<Player> _hideparticle = new ArrayList<Player>();

    public GadgetManager() {
        List<Gadget> particles = new ArrayList<Gadget>();
        particles.add(new ParticleHelix(this));
        particles.add(new ParticleRain(this));
        particles.add(new ParticleFireRings(this));
        particles.add(new ParticleGroundHelix(this));
        particles.add(new ParticleShield(this));
        for(Gadget gadget : particles) {
            Bukkit.getPluginManager().registerEvents(gadget, XHub.getPlugin());
        }
        _gadgets = new NautHashMap<GadgetType, List<Gadget>>();
        _gadgets.put(GadgetType.Particle, particles);
    }

    public boolean isMoving(Player player) {
        if (!_lastMove.containsKey(player)) return false;
        return !UtilTime.elapsed(_lastMove.get(player), 500);
    }

    @EventHandler
    public void setMoving(PlayerMoveEvent event) {
        if(!_playerActiveGadgetMap.containsKey(event.getPlayer())) return;
        if (UtilMath.offset(event.getFrom(), event.getTo()) <= 0) return;
        _lastMove.put(event.getPlayer(), System.currentTimeMillis());
    }


    @EventHandler
    public void quit(PlayerQuitEvent event) {
        DisableAll(event.getPlayer());
        _lastMove.remove(event.getPlayer());
        _playerActiveGadgetMap.remove(event.getPlayer());
    }

    public void setActive(Player player, Gadget gadget) {
        if (!_playerActiveGadgetMap.containsKey(player)) _playerActiveGadgetMap.put(player, new NautHashMap<GadgetType, Gadget>());
        _playerActiveGadgetMap.get(player).put(gadget.getGadgetType(), gadget);
        Bukkit.broadcastMessage(player.getName() + " | " + gadget.toString());
    }

    public Gadget getActive(Player player, GadgetType gadgetType) {
        if (!_playerActiveGadgetMap.containsKey(player)) _playerActiveGadgetMap.put(player, new NautHashMap<GadgetType, Gadget>());
        return _playerActiveGadgetMap.get(player).get(gadgetType);
    }

    public List<Gadget> getGadgets(GadgetType gadgetType)
    {
        return _gadgets.get(gadgetType);
    }

    public void removeActive(Player player, Gadget gadget) {
        if (!_playerActiveGadgetMap.containsKey(player)) _playerActiveGadgetMap.put(player, new NautHashMap<GadgetType, Gadget>());
        _playerActiveGadgetMap.get(player).remove(gadget.getGadgetType());
        gadget.GetActive().remove(player);
    }

    public void DisableAll(Player player) {
        for (GadgetType gadgetType : _gadgets.keySet()) {
            for (Gadget gadget : _gadgets.get(gadgetType)) {
                gadget.Disable(player);
            }
        }
    }

    public void DisableAll() {
        for (GadgetType gadgetType : _gadgets.keySet()) {
            for (Gadget gadget : _gadgets.get(gadgetType)) {
                if (gadget instanceof ParticleGadget) continue;
                for (Player player : UtilServer.getPlayers()) gadget.Disable(player);
            }
        }
    }


    public boolean shouldDisplay(Player player) {
        return !_hideparticle.contains(player);
    }
}
