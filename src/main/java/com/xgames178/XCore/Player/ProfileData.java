package com.xgames178.XCore.Player;

import com.xgames178.XCore.Utils.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by jpdante on 02/05/2017.
 */
public class ProfileData {
    public Rank rank = Rank.NONE;
    public String language = "en-US";
    public int current_gadget = 0;
    public boolean show_players = false;
    public boolean receive_pm = false;
    public List<Boolean> enabled_collectibles = new ArrayList<>();
    public List<MenuInventory> menus = new ArrayList<>();
}
