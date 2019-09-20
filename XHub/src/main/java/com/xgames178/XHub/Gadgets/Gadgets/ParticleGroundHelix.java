package com.xgames178.XHub.Gadgets.Gadgets;

import com.xgames178.XCore.Updater.UpdateType;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import com.xgames178.XCore.Utils.UtilParticle;
import com.xgames178.XCore.Utils.UtilServer;
import com.xgames178.XHub.Gadgets.GadgetManager;
import com.xgames178.XHub.Gadgets.Types.GadgetType;
import com.xgames178.XHub.Gadgets.Types.ParticleGadget;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 * Created by jpdante on 05/05/2017.
 */
public class ParticleGroundHelix extends ParticleGadget {
    public ParticleGroundHelix(GadgetManager manager) {
        super(manager, GadgetType.Particle, "ParticleGroundHelix");
    }

    @EventHandler
    public void playParticle(UpdateEvent event) {
        if (event.getType() != UpdateType.FASTEST) return;
        for (Player player : _active) {
            if (!shouldDisplay(player)) continue;
            if (Manager.isMoving(player)) {
                UtilParticle.PlayParticle(EnumParticle.FLAME, player.getLocation().add(0, 1f, 0), 0.2f, 0.2f, 0.2f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
            } else {
                float radius = 2f;
                float curve = 5f;
                int particles = 30;
                int strands = 5;
                double rotation = Math.PI / 4;
                for (int i = 1; i <= strands; i++) {
                    for (int j = 1; j <= particles; j++) {
                        float ratio = (float) j / particles;
                        double angle = curve * ratio * 2 * Math.PI / strands + (2 * Math.PI * i / strands) + rotation;
                        double x = Math.cos(angle) * ratio * radius;
                        double z = Math.sin(angle) * ratio * radius;
                        UtilParticle.PlayParticle(EnumParticle.FLAME, player.getLocation().add(x, 0, z), 0f, 0f, 0f, 0, 1, UtilParticle.ViewDist.NORMAL, UtilServer.getPlayers());
                    }
                }
            }
        }
    }
}
