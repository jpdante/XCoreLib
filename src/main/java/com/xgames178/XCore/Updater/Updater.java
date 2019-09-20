package com.xgames178.XCore.Updater;

import com.xgames178.XCore.Updater.event.UpdateEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by jpdante on 03/05/2017.
 */
public class Updater implements Runnable
{
    private JavaPlugin _plugin;

    public Updater(JavaPlugin plugin)
    {
        _plugin = plugin;
        _plugin.getServer().getScheduler().scheduleSyncRepeatingTask(_plugin, this, 0L, 1L);
    }

    @Override
    public void run()
    {
        for (UpdateType updateType : UpdateType.values())
        {
            if (updateType.Elapsed())
            {
                _plugin.getServer().getPluginManager().callEvent(new UpdateEvent(updateType));
            }
        }
    }
}
