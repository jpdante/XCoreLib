package com.xgames178.XCore.Utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by jpdante on 03/05/2017.
 */
public class UtilTextMiddle {

    public static void display(String text, String subtitle, int fadeIn, int stay, int fadeOut, Player... players) {
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + text + "'}");
        IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subtitle + "'}");
        PacketPlayOutTitle timePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (BaseComponent[]) null, fadeIn, stay, fadeOut);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
        for(Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timePacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlePacket);
        }
    }

    public static void display(String text, int fadeIn, int stay, int fadeOut, Player... players) {
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + text + "'}");
        PacketPlayOutTitle timePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (BaseComponent[]) null, fadeIn, stay, fadeOut);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON);
        for(Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timePacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
        }
    }

    public static void clear(int fadeIn, int stay, int fadeOut, Player... players) {
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': ''}");
        PacketPlayOutTitle clearPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, titleJSON);
        for(Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(clearPacket);
        }
    }

    public static void reset(int fadeIn, int stay, int fadeOut, Player... players) {
        IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': ''}");
        PacketPlayOutTitle clearPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, titleJSON);
        for(Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(clearPacket);
        }
    }

    public static String progress(float exp) {
        String out = "";
        for (int i=0 ; i<40 ; i++) {
            float cur = i * (1f /40f);
            if (cur < exp)
                out += ChatColor.GREEN + "" + ChatColor.BOLD + "|";
            else
                out += ChatColor.GRAY + "" + ChatColor.BOLD  + "|";
        }
        return out;
    }
}
