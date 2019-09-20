package com.xgames178.XCore;

import com.xgames178.XCore.Database.DataManager;
import com.xgames178.XCore.Database.DataProvider;
import com.xgames178.XCore.Database.Database;
import com.xgames178.XCore.Database.Mysql.MySQL;
import com.xgames178.XCore.Database.Sqlite.SQLite;
import com.xgames178.XCore.Events.onPlayerChat;
import com.xgames178.XCore.Events.onPlayerCommandPreProcess;
import com.xgames178.XCore.Events.onPlayerJoin;
import com.xgames178.XCore.Events.onPlayerQuit;
import com.xgames178.XCore.Language.LanguageManager;
import com.xgames178.XCore.Memory.MemoryFix;
import com.xgames178.XCore.Monitor.LagMeter;
import com.xgames178.XCore.Redis.RedisManager;
import com.xgames178.XCore.Updater.Updater;
import com.xgames178.XCore.Utils.InternalPlayerCache;
import com.xgames178.XCore.Utils.UtilCache;
import com.xgames178.XCore.Utils.UtilCoreConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by jpdante on 06/05/2017.
 */
public class XCore extends JavaPlugin {
    private static XCore instance;
    public UtilCoreConfiguration config;
    private static Plugin plugin;
    private static UtilCache cache;
    private static Database database;
    private static DataProvider defaultDataProvider;
    private static DataManager dataManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.INFO, "[XCore] Loading XCore...");
        plugin = this;
        instance = this;
        Bukkit.getServer().setWhitelist(true);
        saveDefaultConfig();
        reloadConfig();
        config = new UtilCoreConfiguration(instance);
        if(config.Database_Enabled) {
            if(config.Database_Type.equalsIgnoreCase("mysql")) {
                database = new MySQL(config.Database_Host, config.Database_Port, config.Database_Database, config.Database_Username, config.Database_Password);
                Bukkit.getLogger().log(Level.INFO, "[XCore] Using MySQL");
            } else if(config.Database_Type.equalsIgnoreCase("sqlite")) {
                database = new SQLite(config.Database_Filename);
                Bukkit.getLogger().log(Level.INFO, "[XCore] Using SQLite");
            }
            try {
                database.openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                Bukkit.shutdown();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Bukkit.shutdown();
            }
            defaultDataProvider = new DataProvider(database);
            dataManager = new DataManager(defaultDataProvider);
        }
        if(config.Redis_Enabled) {
            Bukkit.getLogger().log(Level.INFO, "[XCore] Using Redis");
            Bukkit.getServer().getPluginManager().registerEvents(new RedisManager(), plugin);
            RedisManager.getContext().Connect(config.Redis_Host, config.Redis_Port, config.Redis_Password, config.Redis_Database);
        }
        new Updater(instance);
        new InternalPlayerCache();
        new LanguageManager();
        cache = new UtilCache();
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerJoin(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerQuit(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerChat(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new onPlayerCommandPreProcess(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new LagMeter(instance), plugin);
        if(config.MemoryFixer_Enabled) {
            MemoryFix.last_failed = false;
            MemoryFix.min_memory = config.MemoryFixer_Min;
            Bukkit.getServer().getPluginManager().registerEvents(new MemoryFix(), plugin);
        }
        Bukkit.getServer().setWhitelist(false);
        Bukkit.getLogger().log(Level.INFO, "[XCore] XCore Loaded!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "[XCore] Unloading XCore...");
        if(config.Database_Enabled) {
            dataManager.shutdown();
        }
        if(config.Redis_Enabled) {
            RedisManager.getContext().Disconnect();
        }
        Bukkit.getLogger().log(Level.INFO, "[XCore] XCore Unloaded!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static XCore getInstance() {
        return instance;
    }

    public static UtilCache getCache() {
        return cache;
    }

    public DataProvider getDefaultDataProvider() {
        return defaultDataProvider;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public Database getDatabaseIO() {
        return database;
    }
}
