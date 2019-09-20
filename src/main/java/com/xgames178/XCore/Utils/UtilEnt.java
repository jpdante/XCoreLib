package com.xgames178.XCore.Utils;

import net.minecraft.server.v1_8_R3.EntityCreature;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;

import java.util.HashMap;

/**
 * Created by jpdante on 03/05/2017.
 */
public class UtilEnt {
    public static HashMap<LivingEntity, Double> getInRadius(Location loc, double dR) {
        HashMap<LivingEntity, Double> ents = new HashMap<LivingEntity, Double>();
        for (Entity cur : loc.getWorld().getEntities()) {
            if (!(cur instanceof LivingEntity) || UtilPlayer.isSpectator(cur))
                continue;
            LivingEntity ent = (LivingEntity)cur;
            double offset = UtilMath.offset(loc, ent.getLocation());
            if (offset < dR)
                ents.put(ent, 1 - (offset/dR));
        }
        return ents;
    }

    public static boolean hitBox(Location loc, LivingEntity ent, double mult, EntityType disguise)
    {
        if (disguise != null)
        {
            if (disguise == EntityType.SQUID)
            {
                if (UtilMath.offset(loc, ent.getLocation().add(0, 0.4, 0)) < 0.6 * mult)
                    return true;

                return false;
            }
        }

        if (ent instanceof Player)
        {
            Player player = (Player)ent;

            if (UtilMath.offset(loc, player.getEyeLocation()) < 0.4 * mult)
            {
                return true;
            }
            else if (UtilMath.offset2d(loc, player.getLocation()) < 0.6 * mult)
            {
                if (loc.getY() > player.getLocation().getY() && loc.getY() < player.getEyeLocation().getY())
                {
                    return true;
                }
            }
        }
        else
        {
            if (ent instanceof Giant)
            {
                if (loc.getY() > ent.getLocation().getY() && loc.getY() < ent.getLocation().getY() + 12)
                    if (UtilMath.offset2d(loc, ent.getLocation()) < 4)
                        return true;
            }
            else
            {
                if (loc.getY() > ent.getLocation().getY() && loc.getY() < ent.getLocation().getY() + 2)
                    if (UtilMath.offset2d(loc, ent.getLocation()) < 0.5 * mult)
                        return true;
            }
        }



        return false;
    }

    public static boolean isGrounded(Entity ent)
    {
        if (ent instanceof CraftEntity)
            return ((CraftEntity)ent).getHandle().onGround;

        return UtilBlock.solid(ent.getLocation().getBlock().getRelative(BlockFace.DOWN));
    }

    public static void PlayDamageSound(LivingEntity damagee)
    {
        Sound sound = Sound.HURT_FLESH;

        if (damagee.getType() == EntityType.BAT)				sound = Sound.BAT_HURT;
        else if (damagee.getType() == EntityType.BLAZE)			sound = Sound.BLAZE_HIT;
        else if (damagee.getType() == EntityType.CAVE_SPIDER)	sound = Sound.SPIDER_IDLE;
        else if (damagee.getType() == EntityType.CHICKEN)		sound = Sound.CHICKEN_HURT;
        else if (damagee.getType() == EntityType.COW)			sound = Sound.COW_HURT;
        else if (damagee.getType() == EntityType.CREEPER)		sound = Sound.CREEPER_HISS;
        else if (damagee.getType() == EntityType.ENDER_DRAGON)	sound = Sound.ENDERDRAGON_GROWL;
        else if (damagee.getType() == EntityType.ENDERMAN)		sound = Sound.ENDERMAN_HIT;
        else if (damagee.getType() == EntityType.GHAST)			sound = Sound.GHAST_SCREAM;
        else if (damagee.getType() == EntityType.GIANT)			sound = Sound.ZOMBIE_HURT;
            //else if (damagee.getType() == EntityType.HORSE)		sound = Sound.
        else if (damagee.getType() == EntityType.IRON_GOLEM)	sound = Sound.IRONGOLEM_HIT;
        else if (damagee.getType() == EntityType.MAGMA_CUBE)	sound = Sound.MAGMACUBE_JUMP;
        else if (damagee.getType() == EntityType.MUSHROOM_COW)	sound = Sound.COW_HURT;
        else if (damagee.getType() == EntityType.OCELOT)		sound = Sound.CAT_MEOW;
        else if (damagee.getType() == EntityType.PIG)			sound = Sound.PIG_IDLE;
        else if (damagee.getType() == EntityType.PIG_ZOMBIE)	sound = Sound.ZOMBIE_HURT;
        else if (damagee.getType() == EntityType.SHEEP)			sound = Sound.SHEEP_IDLE;
        else if (damagee.getType() == EntityType.SILVERFISH)	sound = Sound.SILVERFISH_HIT;
        else if (damagee.getType() == EntityType.SKELETON)		sound = Sound.SKELETON_HURT;
        else if (damagee.getType() == EntityType.SLIME)			sound = Sound.SLIME_ATTACK;
        else if (damagee.getType() == EntityType.SNOWMAN)		sound = Sound.STEP_SNOW;
        else if (damagee.getType() == EntityType.SPIDER)		sound = Sound.SPIDER_IDLE;
            //else if (damagee.getType() == EntityType.SQUID)		sound = Sound;
            //else if (damagee.getType() == EntityType.VILLAGER)	sound = Sound;
            //else if (damagee.getType() == EntityType.WITCH)		sound = Sound.;
        else if (damagee.getType() == EntityType.WITHER)		sound = Sound.WITHER_HURT;
        else if (damagee.getType() == EntityType.WOLF)			sound = Sound.WOLF_HURT;
        else if (damagee.getType() == EntityType.ZOMBIE)		sound = Sound.ZOMBIE_HURT;

        damagee.getWorld().playSound(damagee.getLocation(), sound, 1.5f + (float)(0.5f * Math.random()), 0.8f + (float)(0.4f * Math.random()));
    }

    public static boolean onBlock(Player player)
    {
        //Side Standing
        double xMod = player.getLocation().getX() % 1;
        if (player.getLocation().getX() < 0)
            xMod += 1;

        double zMod = player.getLocation().getZ() % 1;
        if (player.getLocation().getZ() < 0)
            zMod += 1;

        int xMin = 0;
        int xMax = 0;
        int zMin = 0;
        int zMax = 0;

        if (xMod < 0.3)	xMin = -1;
        if (xMod > 0.7)	xMax = 1;

        if (zMod < 0.3)	zMin = -1;
        if (zMod > 0.7)	zMax = 1;

        for (int x=xMin ; x<=xMax ; x++)
        {
            for (int z=zMin ; z<=zMax ; z++)
            {
                //Standing on SOMETHING
                if (player.getLocation().add(x, -0.5, z).getBlock().getType() != Material.AIR && !player.getLocation().add(x, -0.5, z).getBlock().isLiquid())
                    return true;

                //Inside a Lillypad
                if (player.getLocation().add(x, 0, z).getBlock().getType() == Material.WATER_LILY)
                    return true;

                //Fences/Walls
                Material beneath = player.getLocation().add(x, -1.5, z).getBlock().getType();
                if (player.getLocation().getY() % 0.5 == 0 &&
                        (beneath == Material.FENCE ||
                                beneath == Material.FENCE_GATE ||
                                beneath == Material.NETHER_FENCE ||
                                beneath == Material.COBBLE_WALL))
                    return true;
            }
        }

        return false;
    }

    public static boolean CreatureMoveFast(Entity ent, Location target, float speed)
    {
        return CreatureMoveFast(ent, target, speed, true);
    }

    public static boolean CreatureMoveFast(Entity ent, Location target, float speed, boolean slow)
    {
        if (!(ent instanceof Creature))
            return false;

        if (UtilMath.offset(ent.getLocation(), target) < 0.1)
            return false;

        if (UtilMath.offset(ent.getLocation(), target) < 2)
            speed = Math.min(speed, 1f);

        EntityCreature ec = ((CraftCreature)ent).getHandle();
        ec.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

        return true;
    }

    public static Entity getEntityById(int entityId)
    {
        for (World world : Bukkit.getWorlds())
        {
            for (Entity entity : world.getEntities())
            {
                if (entity.getEntityId() == entityId)
                {
                    return entity;
                }
            }
        }

        return null;
    }

    public static boolean inWater(LivingEntity ent)
    {
        return ent.getLocation().getBlock().getTypeId() == 8 || ent.getLocation().getBlock().getTypeId() == 9;
    }
}
