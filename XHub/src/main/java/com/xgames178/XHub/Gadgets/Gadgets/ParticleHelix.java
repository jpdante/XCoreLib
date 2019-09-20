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
public class ParticleHelix extends ParticleGadget {
    public ParticleHelix(GadgetManager manager) {
        super(manager, GadgetType.Particle, "ParticleHelix");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.TICK) return;
        Bukkit.broadcastMessage("Sending particle...");
        for (Player player : _active) {
            if (!shouldDisplay(player)) continue;
            if (Manager.isMoving(player)) {
                UtilParticle.PlayParticle(EnumParticle.REDSTONE, player.getLocation().add(0, 1, 0), 0.2f, 0.2f, 0.2f, 0, 4, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
            } else {
                for (int height=0 ; height <= 20 ; height++) {
                    for (int i=0 ; i < 2 ; i++) {
                        double lead = i * ((2d * Math.PI)/2);
                        double heightLead = height * ((2d * Math.PI)/20);
                        float x = (float) (Math.sin(player.getTicksLived()/20d + lead + heightLead) * 1.2f);
                        float z = (float) (Math.cos(player.getTicksLived()/20d + lead + heightLead) * 1.2f);
                        float y = 0.15f * height;
                        UtilParticle.PlayParticle(EnumParticle.REDSTONE, player.getLocation().add(x * (1d - height/22d), y, z * (1d - height/22d)), 0f, 0f, 0f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                    }
                }
            }
        }
    }
}
