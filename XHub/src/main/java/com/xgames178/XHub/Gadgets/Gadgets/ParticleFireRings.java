package com.xgames178.XHub.Gadgets.Gadgets;

import com.xgames178.XCore.Updater.UpdateType;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import com.xgames178.XCore.Utils.UtilParticle;
import com.xgames178.XCore.Utils.UtilServer;
import com.xgames178.XHub.Gadgets.GadgetManager;
import com.xgames178.XHub.Gadgets.Types.GadgetType;
import com.xgames178.XHub.Gadgets.Types.ParticleGadget;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 * Created by jpdante on 05/05/2017.
 */
public class ParticleFireRings extends ParticleGadget {
    public ParticleFireRings(GadgetManager manager) {
        super(manager, GadgetType.Particle, "ParticleFireRings");
        Bukkit.broadcastMessage("aaa");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.TICK) return;
        for (Player player : _active) {
            if (!shouldDisplay(player)) continue;
            if (Manager.isMoving(player)) {
                UtilParticle.PlayParticle(EnumParticle.FLAME, player.getLocation().add(0, 1f, 0), 0.2f, 0.2f, 0.2f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
            } else {
                for (int i=0 ; i < 1 ; i++) {
                    double lead = i * ((2d * Math.PI)/2);
                    float x = (float) (Math.sin(player.getTicksLived()/5d + lead) * 1f);
                    float z = (float) (Math.cos(player.getTicksLived()/5d + lead) * 1f);
                    float y = (float) (Math.sin(player.getTicksLived()/5d + lead) + 1f);
                    UtilParticle.PlayParticle(EnumParticle.FLAME, player.getLocation().add(x, y, z), 0f, 0f, 0f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                }

                for (int i=0 ; i < 1 ; i++) {
                    double lead = i * ((2d * Math.PI)/2);
                    float x = (float) -(Math.sin(player.getTicksLived()/5d + lead) * 1f);
                    float z = (float) (Math.cos(player.getTicksLived()/5d + lead) * 1f);
                    float y = (float) (Math.sin(player.getTicksLived()/5d + lead) + 1f);
                    UtilParticle.PlayParticle(EnumParticle.FLAME, player.getLocation().add(x, y, z), 0f, 0f, 0f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                }
            }
        }
    }
}