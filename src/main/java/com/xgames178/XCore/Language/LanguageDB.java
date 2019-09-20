package com.xgames178.XCore.Language;

import com.xgames178.XCore.Utils.NautHashMap;

public abstract class LanguageDB {
    private String language;
    private String languageName;
    private NautHashMap<String, String> translation;

    public LanguageDB(String language, String languageName) {
        this.language = language;
        this.languageName = languageName;
        this.translation = new NautHashMap<>();
    }

    public boolean insertTranslation(String key, String value) {
        if(this.translation.containsKey(key)) return false;
        this.translation.put(key, value);
        return true;
    }

    public String getTranslation(String key) {
        if(!this.translation.containsKey(key)) return "§cmissing var " + key + "§f";
        return this.translation.get(key);
    }

    public boolean removeTranslation(String key) {
        if(!this.translation.containsKey(key)) return false;
        this.translation.remove(key);
        return true;
    }

    public String[] getKeys() {
        return (String[]) this.translation.keySet().toArray();
    }

    public String[] getValues() {
        return (String[]) this.translation.values().toArray();
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public boolean compareLanguage(String language) {
        return this.language.equalsIgnoreCase(language);
    }
}
