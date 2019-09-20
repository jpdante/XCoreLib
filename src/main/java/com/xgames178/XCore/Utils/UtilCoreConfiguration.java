package com.xgames178.XCore.Utils;

import com.xgames178.XCore.XCore;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class UtilCoreConfiguration {
    public boolean Database_Enabled = false;
    public String Database_Type = "sqlite";
    public String Database_Filename = "database.db";
    public String Database_Host = "127.0.0.1";
    public String Database_Port = "3306";
    public String Database_Database = "database";
    public String Database_Username = "username";
    public String Database_Password = "password";

    public boolean Redis_Enabled = false;
    public String Redis_Host = "127.0.0.1";
    public String Redis_Port = "6379";
    public int Redis_Database = 0;
    public String Redis_Password = "password";

    public boolean MemoryFixer_Enabled = false;
    public long MemoryFixer_Min = 1024;

    public boolean Chat_Enabled = false;
    public boolean Chat_Announce_Vip_Join = false;

    public boolean Inject_Enabled = false;
    public boolean Intercept_PlayerChat = false;
    public boolean Intercept_PlayerCommandPreProcess = false;
    public boolean Intercept_PlayerJoin = false;
    public boolean Intercept_PlayerQuit = false;

    public UtilCoreConfiguration(XCore plugin) {
        Database_Enabled = plugin.getConfig().getBoolean("database.needed");
        Database_Type = plugin.getConfig().getString("database.type");
        Database_Filename = plugin.getConfig().getString("database.filename");
        Database_Host = plugin.getConfig().getString("database.host");
        Database_Port = plugin.getConfig().getString("database.port");
        Database_Database = plugin.getConfig().getString("database.database");
        Database_Username = plugin.getConfig().getString("database.username");
        Database_Password = plugin.getConfig().getString("database.password");

        Redis_Enabled = plugin.getConfig().getBoolean("redis.needed");
        Redis_Host = plugin.getConfig().getString("redis.host");
        Redis_Port = plugin.getConfig().getString("redis.port");
        Redis_Database = plugin.getConfig().getInt("redis.database");
        Redis_Password = plugin.getConfig().getString("redis.password");

        MemoryFixer_Enabled = plugin.getConfig().getBoolean("memoryfixer.needed");
        MemoryFixer_Min = plugin.getConfig().getLong("memoryfixer.min");

        Chat_Enabled = plugin.getConfig().getBoolean("chat.needed");
        Chat_Announce_Vip_Join = plugin.getConfig().getBoolean("chat.announce_vip_join");

        Inject_Enabled = plugin.getConfig().getBoolean("injector.enabled");
    }
}
