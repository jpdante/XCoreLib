package com.xgames178.XCore.Events;

import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.XCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        if(XCore.getInstance().config.Intercept_PlayerJoin) return;
        PlayerProfile pf = new PlayerProfile(event.getPlayer());
        InternalPlayerCache.playerProfiles.put(event.getPlayer().getUniqueId(), pf);
    }
}
