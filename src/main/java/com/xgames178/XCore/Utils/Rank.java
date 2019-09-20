package com.xgames178.XCore.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by jpdante on 03/05/2017.
 */
public enum Rank {
    NONE(0, "", ChatColor.GRAY),
    VIP(1, "Vip", ChatColor.GREEN),
    VIPPLUS(2, "Vip+", ChatColor.GREEN),
    MVP(3, "MVP", ChatColor.AQUA),
    HELPER(4, "Helper", ChatColor.DARK_AQUA),
    MAPDEV(5, "Builder", ChatColor.DARK_GREEN),
    MODERATOR(6, "Mod", ChatColor.GREEN),
    ADMIN(7, "Admin", ChatColor.RED),
    OWNER(8, "Dono", ChatColor.DARK_RED),
    DEVELOPER(9, "Dev", ChatColor.GOLD);
    private ChatColor Color;
    public String Name;
    public int Index;
    Rank(int index, String name, ChatColor color) {
        Color = color;
        Name = name;
        Index = index;
    }
    public String GetTag(boolean bold, boolean uppercase) {
        if (Name.equalsIgnoreCase("ALL")) return "";
        String name = Name;
        if (uppercase) name = Name.toUpperCase();
        if (bold) return Color + C.Bold + name;
        else return Color + name;
    }
    public ChatColor GetColor() {
        return Color;
    }
}