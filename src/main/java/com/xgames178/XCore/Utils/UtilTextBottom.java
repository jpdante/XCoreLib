package com.xgames178.XCore.Utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by jpdante on 03/05/2017.
 */
public class UtilTextBottom {
    public static void display(String text, Player... players) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+text+"\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,(byte) 2);
        for(Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
        }
    }

    public static void displayProgress(double amount, Player... players) {
        displayProgress(null, amount, null, players);
    }

    public static void displayProgress(String prefix, double amount, Player... players) {
        displayProgress(prefix, amount, null, players);
    }

    public static void displayProgress(String prefix, double amount, String suffix, Player... players) {
        displayProgress(prefix, amount, suffix, false, players);
    }

    public static void displayProgress(String prefix, double amount, String suffix, boolean progressDirectionSwap, Player... players) {
        if (progressDirectionSwap) amount = 1 - amount;
        int bars = 24;
        String progressBar = ChatColor.GREEN + "";
        boolean colorChange = false;
        for (int i=0 ; i<bars ; i++) {
            if (!colorChange && (float)i/(float)bars >= amount) {
                progressBar += ChatColor.RED;
                colorChange = true;
            }

            progressBar += "▌";
        }
        for (Player player : players) {
            display((prefix == null ? "" : prefix + ChatColor.RESET + " ") + progressBar + (suffix == null ? "" : ChatColor.RESET + " " + suffix), players);
        }
    }
}