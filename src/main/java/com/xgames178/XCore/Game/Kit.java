package com.xgames178.XCore.Game;

import com.xgames178.XCore.GameManager;
import com.xgames178.XCore.Game.Events.PlayerKitGiveEvent;
import com.xgames178.XCore.Utils.C;
import com.xgames178.XCore.Utils.UtilInv;
import com.xgames178.XCore.Utils.UtilPlayer;
import com.xgames178.XCore.Utils.UtilServer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by jpdante on 08/05/2017.
 */
public abstract class Kit implements Listener {
    private GameManager Manager;
    private String _kitName;
    private String[] _kitDesc;
    private KitAvailability _kitAvailability;
    private int _cost;
    protected ItemStack _itemDisplay;

    public Kit(GameManager manager, String name, KitAvailability kitAvailability, String[] kitDesc, int cost, ItemStack itemDisplay) {
        Manager = manager;
        _kitName = name;
        _kitAvailability = kitAvailability;
        _kitDesc = kitDesc;
        _itemDisplay = itemDisplay;
        _cost = cost;
    }
    public void GiveItemsCall(Player player) {
        GiveItems(player);
        PlayerKitGiveEvent kitEvent = new PlayerKitGiveEvent(Manager.GetGame(), this, player);
        UtilServer.getServer().getPluginManager().callEvent(kitEvent);
    }
    public abstract void GiveItems(Player player);
    public String GetName() {
        return _kitName;
    }
    public boolean HasKit(Player player) {
        if (Manager.GetGame() == null) return false;
        return Manager.GetGame().HasKit(player, this);
    }
    public String[] GetDesc()
    {
        return _kitDesc;
    }
    public KitAvailability GetAvailability()
    {
        return _kitAvailability;
    }
    public String GetFormattedName()
    {
        return GetAvailability().GetColor() + "§l" + _kitName;
    }
    public int GetCost()
    {
        return _cost;
    }
    public void DisplayDesc(Player player) {
        for (int i=0 ; i<3 ; i++) UtilPlayer.message(player, "");
        UtilPlayer.message(player, C.cDGreen + C.Strike + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        UtilPlayer.message(player, "§aKit - §f§l" + GetName());
        for (String line : GetDesc()) UtilPlayer.message(player, C.cGray + "  " + line);
        UtilPlayer.message(player, C.cDGreen + C.Strike + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    public void Deselected(Player player) { }
    public void Selected(Player player) { }
    public void ApplyKit(Player player) {
        UtilInv.Clear(player);
        GiveItemsCall(player);
        UtilInv.Update(player);
    }
}
