package com.xgames178.XHub.Gadgets.Gadgets;

import com.xgames178.XCore.Updater.UpdateType;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import com.xgames178.XCore.Utils.UtilParticle;
import com.xgames178.XCore.Utils.UtilServer;
import com.xgames178.XHub.Gadgets.GadgetManager;
import com.xgames178.XHub.Gadgets.Types.GadgetType;
import com.xgames178.XHub.Gadgets.Types.ParticleGadget;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 * Created by jpdante on 05/05/2017.
 */
public class ParticleRain extends ParticleGadget {
    public ParticleRain(GadgetManager manager) {
        super(manager, GadgetType.Particle, "ParticleRain");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.TICK) return;
        for (Player player : _active) {
            if (!shouldDisplay(player)) continue;
            if (Manager.isMoving(player)) {
                UtilParticle.PlayParticle(EnumParticle.WATER_SPLASH, player.getLocation().add(0, 1, 0), 0.2f, 0.2f, 0.2f, 0, 4, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
            } else {
                UtilParticle.PlayParticle(EnumParticle.EXPLOSION_NORMAL, player.getLocation().add(0, 3.5, 0), 0.6f, 0f, 0.6f, 0, 8, UtilParticle.ViewDist.NORMAL, player);
                for (Player other : UtilServer.getPlayers())
                    if (!player.equals(other)) UtilParticle.PlayParticle(EnumParticle.CLOUD, player.getLocation().add(0, 3.5, 0), 0.6f, 0.1f, 0.6f, 0, 8, UtilParticle.ViewDist.NORMAL, other);
                UtilParticle.PlayParticle(EnumParticle.DRIP_WATER, player.getLocation().add(0, 3.5, 0), 0.4f, 0.1f, 0.4f, 0, 2, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                player.getWorld().playSound(player.getLocation(), Sound.AMBIENCE_RAIN, 0.1f, 1f);
            }
        }
    }
}