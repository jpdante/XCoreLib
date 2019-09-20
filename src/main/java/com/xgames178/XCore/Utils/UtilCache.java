package com.xgames178.XCore.Utils;

import java.util.Collection;
import java.util.Set;

/**
 * Created by jpdante on 07/05/2017.
 */
public class UtilCache {
    private NautHashMap<String, Object> cache;

    public UtilCache() {
        cache = new NautHashMap<>();
    }

    public void register(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public NautHashMap getNautHashMap(String key) {
        Object value = get(key);
        if(value instanceof NautHashMap) {
            return (NautHashMap) value;
        }
        return null;
    }

    public String getKey(Object value) {
        for(String key : cache.keySet()) {
            if(value == cache.get(key)) {
                return key;
            }
        }
        return null;
    }

    public void delete(String key) {
        cache.remove(key);
    }

    public void delete(Object value) {
        String key = getKey(value);
        if(key != null) {
            delete(key);
        }
    }

    public Set<String> getKeys() {
        return cache.keySet();
    }

    public Collection<Object> getValues() {
        return cache.values();
    }
}
