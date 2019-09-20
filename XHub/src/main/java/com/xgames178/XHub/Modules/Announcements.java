package com.xgames178.XHub.Modules;

import com.xgames178.XCore.Updater.UpdateType;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Announcements implements Listener {
    private int step = 0;
    private List<String> announcements;

    public Announcements(List<String> messages) {
        announcements = messages;
    }

    @EventHandler
    public void executeAnnouncement(UpdateEvent event) {
        if (event.getType() != UpdateType.MIN_02) return;
        if(step == announcements.size()) step = 0;
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(announcements.get(step));
        }
        step++;
    }
}
