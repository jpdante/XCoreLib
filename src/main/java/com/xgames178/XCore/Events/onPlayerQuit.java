package com.xgames178.XCore.Events;

import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.XCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by jpdante on 02/05/2017.
 */
public class onPlayerQuit implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        if(XCore.getInstance().config.Intercept_PlayerQuit) return;
        InternalPlayerCache.playerProfiles.remove(event.getPlayer().getUniqueId());
    }
}
