package com.xgames178.XCore.Game;

import org.bukkit.ChatColor;

/**
 * Created by jpdante on 08/05/2017.
 */
public enum KitAvailability {
    Free(ChatColor.GREEN),
    Gem(ChatColor.AQUA),
    Null(ChatColor.GRAY);
    ChatColor _color;
    KitAvailability(ChatColor color) {
        _color = color;
    }
    public ChatColor GetColor() {
        return _color;
    }
}
