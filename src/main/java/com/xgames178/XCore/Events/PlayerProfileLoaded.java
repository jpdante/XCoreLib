package com.xgames178.XCore.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import java.util.UUID;

public class PlayerProfileLoaded extends Event {
    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    public PlayerProfileLoaded(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUUID()
    {
        return this.uuid;
    }
    public HandlerList getHandlers()
    {
        return handlers;
    }
    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
