package com.xgames178.XCore.Language;

import com.xgames178.XCore.Player.PlayerProfile;
import com.xgames178.XCore.Utils.NautHashMap;

public class LanguageManager {

    private static NautHashMap<String, LanguageDB> languageDBList;

    public LanguageManager() {
        languageDBList = new NautHashMap<>();
    }

    public static void addLanguage(LanguageDB languageDB) {
        languageDBList.put(languageDB.getLanguage(), languageDB);
    }

    public static void removeLanguage(String locale) {
        languageDBList.remove(locale);
    }

    public static void sendMessage(PlayerProfile playerProfile, String key) {
        if(!languageDBList.containsKey(playerProfile.profileData.language)) {
            playerProfile.player.sendMessage(languageDBList.get("en_US").getTranslation(key));
            return;
        }
        playerProfile.player.sendMessage(languageDBList.get(playerProfile.profileData.language).getTranslation(key));
    }

    public static String getTranslation(PlayerProfile playerProfile, String key) {
        if(!languageDBList.containsKey(playerProfile.profileData.language)) {
            return languageDBList.get("en_US").getTranslation(key);
        }
        return languageDBList.get(playerProfile.profileData.language).getTranslation(key);
    }
}
