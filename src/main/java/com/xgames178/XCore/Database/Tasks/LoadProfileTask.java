package com.xgames178.XCore.Database.Tasks;

import com.xgames178.XCore.Database.Callback;
import com.xgames178.XCore.Database.DataTask;
import com.xgames178.XCore.Player.ProfileData;
import com.xgames178.XCore.XCore;
import org.bukkit.Bukkit;
import java.util.UUID;

/**
 * Created by jpdante on 02/05/2017.
 */
public class LoadProfileTask extends DataTask {
    private final UUID playerUUID;
    private final Callback<ProfileData> callback;

    public LoadProfileTask(UUID uuid, Callback<ProfileData> callback) {
        super(DataTask.Priority.HIGH);
        this.playerUUID = uuid;
        this.callback = callback;
    }

    @Override
    public void run() {
        final ProfileData profileData = XCore.getInstance().getDefaultDataProvider().getProfileData(playerUUID);
        Bukkit.getScheduler().runTask(XCore.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onComplete(profileData);
                }
            }
        });
    }
}
