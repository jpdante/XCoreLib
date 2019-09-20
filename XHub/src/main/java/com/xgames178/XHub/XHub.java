package com.xgames178.XHub;

import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.XCoreInjector;
import com.xgames178.XHub.Events.PlayerEvents;
import com.xgames178.XHub.Events.WorldEvents;
import com.xgames178.XHub.Gadgets.GadgetManager;
import com.xgames178.XHub.Menus.MiniGamesMenu;
import com.xgames178.XHub.Modules.Announcements;
import com.xgames178.XHub.Modules.AuthMeChecker;
import com.xgames178.XHub.Modules.JumpModule;
import com.xgames178.XHub.Translation.EN_US;
import com.xgames178.XHub.Translation.PT_BR;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class XHub extends JavaPlugin {
    private static XHub instance;
    private static Plugin plugin;
    private static GadgetManager gadgetManager;

    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        saveDefaultConfig();
        reloadConfig();
        LanguageManager.addLanguage(new EN_US());
        LanguageManager.addLanguage(new PT_BR());
        gadgetManager = new GadgetManager();
        Bukkit.getServer().getPluginManager().registerEvents(gadgetManager, instance);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldEvents(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new AuthMeChecker(), instance);
        if(getConfig().getBoolean("module_announcements.enabled")) Bukkit.getServer().getPluginManager().registerEvents(new Announcements(getConfig().getStringList("module_announcements.announcements")), instance);
        if(getConfig().getBoolean("module_jump.enabled")) Bukkit.getServer().getPluginManager().registerEvents(new JumpModule(), instance);
        XCoreInjector.Intercept_PlayerJoin();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPlugin() {
        return plugin;
    }
    public static GadgetManager getGadgetManager() {
        return gadgetManager;
    }
    public static XHub getInstance() {
        return instance;
    }

}
