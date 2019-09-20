package com.xgames178.XHub.Modules;

import com.xgames178.XCore.Updater.UpdateType;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.XCoreInjector;
import com.xgames178.XHub.Utils.PlayerLobby;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuthMeChecker implements Listener {

    @EventHandler
    public void checkAuthenticatedPlayers(UpdateEvent event) {
        if(event.getType() != UpdateType.TWOSEC) return;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(InternalPlayerCache.playerProfiles.containsKey(p.getUniqueId())) continue;
            if(!AuthMeApi.getInstance().isAuthenticated(p)) continue;
            XCoreInjector.authenticatePlayer(p);
            return;
        }
    }
}
