package com.xgames178.XCore.Utils;

import com.xgames178.XCore.Player.PlayerProfile;
import java.util.UUID;

/**
 * Created by jpdante on 07/05/2017.
 */
public class InternalPlayerCache {
    public static NautHashMap<UUID, PlayerProfile> playerProfiles;

    public InternalPlayerCache() {
        playerProfiles = new NautHashMap<>();
    }
}
