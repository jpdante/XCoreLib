package com.xgames178.XCore.Events;

import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.Utils.Rank;
import com.xgames178.XCore.XCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(!XCore.getInstance().config.Chat_Enabled) return;
        if(XCore.getInstance().config.Intercept_PlayerChat) return;
        if(!InternalPlayerCache.playerProfiles.containsKey(event.getPlayer().getUniqueId())) return;
        event.setMessage(event.getMessage().replace("%", ""));
        PlayerProfile profile = InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId());
        if(profile.profileData.rank == Rank.NONE) {
            event.setFormat(profile.profileData.rank.GetColor() + event.getPlayer().getName() + " §7›› " + profile.profileData.rank.GetColor() + event.getMessage());
        } else {
            event.setFormat(profile.profileData.rank.GetColor() + "[" + profile.profileData.rank.GetTag(false, true) + "] " + event.getPlayer().getName() + " §7›› " + profile.profileData.rank.GetColor() + event.getMessage().replace("&", "§"));
        }
    }

}
