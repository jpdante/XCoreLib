package com.xgames178.XCore.Player;

import com.xgames178.XCore.Database.Callback;
import com.xgames178.XCore.Database.Tasks.LoadProfileTask;
import com.xgames178.XCore.Events.PlayerProfileLoaded;
import com.xgames178.XCore.Redis.RedisManager;
import com.xgames178.XCore.Updater.event.UpdateEvent;
import com.xgames178.XCore.Utils.Rank;
import com.xgames178.XCore.XCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Created by jpdante on 02/05/2017.
 */
public class PlayerProfile {
    public ProfileData profileData = null;
    public Player player;

    public PlayerProfile(Player eplayer) {
        player = eplayer;
        if(RedisManager.getContext().hasPlayer(eplayer.getUniqueId())) {
            Bukkit.getLogger().log(Level.INFO, "Loading " + eplayer.getUniqueId().toString() + " from Redis...");
            profileData = RedisManager.getContext().getPlayer(eplayer.getUniqueId());
            XCore.getPlugin().getServer().getPluginManager().callEvent(new PlayerProfileLoaded(eplayer.getUniqueId()));
        } else {
            if(XCore.getInstance().config.Database_Enabled) {
                Bukkit.getLogger().log(Level.INFO, "Loading " + eplayer.getUniqueId().toString() + " from Database...");
                XCore.getInstance().getDataManager().addTask(new LoadProfileTask(player.getUniqueId(), new Callback<ProfileData>() {
                    @Override
                    public void onComplete(ProfileData pD) {
                        profileData = pD;
                        RedisManager.getContext().addPlayer(eplayer.getUniqueId(), profileData);
                        if(XCore.getInstance().config.Chat_Announce_Vip_Join) {
                            if(profileData.rank == Rank.VIP || profileData.rank == Rank.VIPPLUS || profileData.rank == Rank.MVP) Bukkit.broadcastMessage(profileData.rank.GetColor() + "[" + profileData.rank.GetTag(false, true) + "] " + eplayer.getPlayer().getName() + " Entrou no servidor");
                        }
                        XCore.getPlugin().getServer().getPluginManager().callEvent(new PlayerProfileLoaded(eplayer.getUniqueId()));
                    }
                }));
            }
        }
    }
}
