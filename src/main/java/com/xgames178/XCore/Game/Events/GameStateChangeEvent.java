package com.xgames178.XCore.Game.Events;

import com.xgames178.XCore.Game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by jpdante on 13/05/2017.
 */
public class GameStateChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Game _game;
    private Game.GameState _to;

    public GameStateChangeEvent(Game game, Game.GameState to)
    {
        _game = game;
        _to = to;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public Game GetGame()
    {
        return _game;
    }

    public Game.GameState GetState()
    {
        return _to;
    }
}
