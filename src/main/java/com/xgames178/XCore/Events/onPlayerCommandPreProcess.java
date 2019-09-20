package com.xgames178.XCore.Events;

import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.Utils.Rank;
import com.xgames178.XCore.XCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by jpdante on 07/05/2017.
 */
public class onPlayerCommandPreProcess implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if(XCore.getInstance().config.Intercept_PlayerCommandPreProcess) return;
        if(!InternalPlayerCache.playerProfiles.containsKey(event.getPlayer().getUniqueId())) return;
        PlayerProfile profile = InternalPlayerCache.playerProfiles.get(event.getPlayer().getUniqueId());
        if(profile.profileData.rank == Rank.OWNER || profile.profileData.rank == Rank.ADMIN || profile.profileData.rank == Rank.DEVELOPER) return;
        if (event.getMessage().trim().equalsIgnoreCase("/help")) event.setCancelled(true);
        else if (event.getMessage().trim().equalsIgnoreCase("/?")) event.setCancelled(true);
        else if (event.getMessage().trim().equalsIgnoreCase("/bukkit")) event.setCancelled(true);
        else if (event.getMessage().trim().equalsIgnoreCase("/plugins")) event.setCancelled(true);
        else if (event.getMessage().trim().equalsIgnoreCase("/pl")) event.setCancelled(true);
    }
}
