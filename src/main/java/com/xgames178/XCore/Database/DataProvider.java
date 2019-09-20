package com.xgames178.XCore.Database;

import com.xgames178.XCore.Player.ProfileData;
import com.xgames178.XCore.Utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by jpdante on 02/05/2017.
 */
public class DataProvider {
    private Database database;
    private Statement statement;

    public DataProvider(Database database) {
        this.database = database;
        try {
            this.statement = database.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProfileData getProfileData(UUID uuid) {
        ResultSet res = null;
        try {
            res = this.statement.executeQuery("SELECT * FROM `arcade_players` WHERE `uuid`= '" + uuid.toString() + "';");
            ProfileData profileData = new ProfileData();
            if(res.next()) {
                int rank = res.getInt("rank");
                for(Rank r : Rank.values()) {
                    if(r.Index == rank) {
                        profileData.rank = r;
                        break;
                    }
                }
                profileData.current_gadget = res.getInt("cugd");
                profileData.receive_pm = res.getBoolean("repm");
                profileData.show_players = res.getBoolean("swpl");
                profileData.language = res.getString("language");
                profileData.enabled_collectibles = new ArrayList<>();
                res = this.statement.executeQuery("SELECT * FROM `arcade_gadgets` WHERE `uuid`= '" + uuid.toString() + "';");
                res.next();
                profileData.enabled_collectibles.add(res.getBoolean("g1"));
                profileData.enabled_collectibles.add(res.getBoolean("g2"));
                profileData.enabled_collectibles.add(res.getBoolean("g3"));
                profileData.enabled_collectibles.add(res.getBoolean("g4"));
                profileData.enabled_collectibles.add(res.getBoolean("g5"));
            } else {
                String language = "en-US";
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if(player.getUniqueId() == uuid) {
                        language = ((CraftPlayer) player).getHandle().locale;
                        break;
                    }
                }
                this.statement.executeUpdate("INSERT INTO `arcade_players` (`uuid`, `language`) VALUES ('" + uuid.toString() + "', '" + language + "');");
                this.statement.executeUpdate("INSERT INTO `arcade_gadgets` (`uuid`) VALUES ('" + uuid.toString() + "');");
                //profileData.uuid = uuid;
                profileData.rank = Rank.NONE;
                profileData.language = language;
                profileData.current_gadget = 0;
                profileData.receive_pm = true;
                profileData.show_players = true;
                profileData.enabled_collectibles = new ArrayList<>();
            }
            return profileData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
