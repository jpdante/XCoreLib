package com.xgames178.XCore.Utils;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by jpdante on 02/05/2017.
 */
public class UtilParticle
{
    public enum ViewDist
    {
        SHORT(8),
        NORMAL(24),
        LONG(48),
        LONGER(96),
        MAX(256);

        private int _dist;

        ViewDist(int dist)
        {
            _dist = dist;
        }

        public int getDist()
        {
            return _dist;
        }
    }

    private static PacketPlayOutWorldParticles getPacket(EnumParticle enumParticle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count, boolean displayFar) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(enumParticle, displayFar, (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, count, null);
        return packet;
    }

    public static void PlayParticle(EnumParticle particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count, ViewDist dist, Player... players) {
        PacketPlayOutWorldParticles packet = getPacket(particle, location, offsetX, offsetY, offsetZ, speed, count, true);
        for (Player player : players) {
            if (UtilMath.offset(player.getLocation(), location) > dist.getDist()) continue;
            UtilPlayer.sendPacket(player, packet);
        }
    }
}