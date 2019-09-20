package com.xgames178.XCore.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/**
 * Created by jpdante on 03/05/2017.
 */
public class UtilAction {
    public static void velocity(Entity ent, double str, double yAdd, double yMax, boolean groundBoost) {
        velocity(ent, ent.getLocation().getDirection(), str, false, 0, yAdd, yMax, groundBoost);
    }

    public static void velocity(Entity ent, Vector vec, double str, boolean ySet, double yBase, double yAdd, double yMax, boolean groundBoost) {
        if (Double.isNaN(vec.getX()) || Double.isNaN(vec.getY()) || Double.isNaN(vec.getZ()) || vec.length() == 0) return;
        if (ySet) vec.setY(yBase);
        vec.normalize();
        vec.multiply(str);
        vec.setY(vec.getY() + yAdd);
        if (vec.getY() > yMax) vec.setY(yMax);
        if (groundBoost) if (UtilEnt.isGrounded(ent)) vec.setY(vec.getY() + 0.2);
        ent.setFallDistance(0);
        ent.setVelocity(vec);
    }
}
