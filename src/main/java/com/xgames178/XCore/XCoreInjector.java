package com.xgames178.XCore;

import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.logging.Level;

public class XCoreInjector {

    public static void authenticatePlayer(Player player) {
        PlayerProfile pf = new PlayerProfile(player);
        InternalPlayerCache.playerProfiles.put(player.getUniqueId(), pf);
    }

    public static void Intercept_PlayerChat() {
        if(!XCore.getInstance().config.Inject_Enabled) {
            Bukkit.getLogger().log(Level.WARNING, "[XCore] The interception was denied!");
            return;
        }
        XCore.getInstance().config.Intercept_PlayerChat = true;
        Bukkit.getLogger().log(Level.INFO, "[XCore] onPlayerChat Intercepted!");
    }

    public static void Intercept_PlayerCommandPreProcess() {
        if(!XCore.getInstance().config.Inject_Enabled) {
            Bukkit.getLogger().log(Level.WARNING, "[XCore] The interception was denied!");
            return;
        }
        XCore.getInstance().config.Intercept_PlayerCommandPreProcess = true;
        Bukkit.getLogger().log(Level.INFO, "[XCore] onPlayerCommandPreProcess Intercepted!");
    }

    public static void Intercept_PlayerJoin() {
        if(!XCore.getInstance().config.Inject_Enabled) {
            Bukkit.getLogger().log(Level.WARNING, "[XCore] The interception was denied!");
            return;
        }
        XCore.getInstance().config.Intercept_PlayerJoin = true;
        Bukkit.getLogger().log(Level.INFO, "[XCore] onPlayerJoin Intercepted!");
    }

    public static void Intercept_PlayerQuit() {
        if(!XCore.getInstance().config.Inject_Enabled) {
            Bukkit.getLogger().log(Level.WARNING, "[XCore] The interception was denied!");
            return;
        }
        XCore.getInstance().config.Intercept_PlayerQuit = true;
        Bukkit.getLogger().log(Level.INFO, "[XCore] onPlayerQuit Intercepted!");
    }
}
