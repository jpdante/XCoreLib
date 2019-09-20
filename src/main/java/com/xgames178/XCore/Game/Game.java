package com.xgames178.XCore.Game;

import com.xgames178.XCore.GameManager;
import com.xgames178.XCore.Utils.F;
import com.xgames178.XCore.Utils.NautHashMap;
import com.xgames178.XCore.Utils.UtilPlayer;
import com.xgames178.XCore.Utils.UtilServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Created by jpdante on 08/05/2017.
 */
public abstract class Game implements Listener  {

    public GameManager Manager;
    protected String _gameName;
    protected String[] _gameDesc;
    private GameState _gameState = GameState.Loading;
    private Kit[] _kits;
    protected ArrayList<GameTeam> _teamList = new ArrayList<GameTeam>();
    protected NautHashMap<Player, Kit> _playerKit = new NautHashMap<Player, Kit>();
    private boolean _prepareCountdown = false;
    private int _countdown = -1;
    public boolean SpawnNearAllies = false;
    public boolean SpawnNearEnemies = false;
    public enum GameState {
        Loading,
        Lobby,
        Prepare,
        Running,
        Finished
    }

    public Game(GameManager manager, String gameName, String[] gameDesc, Kit[] kits) {
        Manager = manager;
        _kits = kits;
        _gameDesc = gameDesc;
        _gameName = gameName;
    }

    public void set_gameState(GameState gameState) {
        _gameState = gameState;
    }
    public boolean IsLive()
    {
        return _gameState == GameState.Running;
    }
    public ArrayList<Player> GetPlayers(boolean aliveOnly) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (GameTeam team : _teamList) players.addAll(team.GetPlayers(aliveOnly));
        return players;
    }
    public boolean IsAlive(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            GameTeam team = GetTeam(player);
            if (team == null) return false;
            return team.IsAlive(player);
        }
        return false;
    }
    public GameTeam GetTeam(String player, boolean aliveOnly) {
        for (GameTeam team : _teamList) if (team.HasPlayer(player, aliveOnly)) return team;
        return null;
    }
    public GameTeam GetTeam(Player player) {
        if (player == null) return null;
        for (GameTeam team : _teamList) if (team.HasPlayer(player)) return team;
        return null;
    }
    public GameTeam GetTeam(ChatColor color) {
        for (GameTeam team : _teamList) if (team.GetColor() == color) return team;
        return null;
    }
    public int GetCountdown()
    {
        return _countdown;
    }
    public void SetCountdown(int time)
    {
        _countdown = time;
    }
    public boolean CanJoinTeam(GameTeam team) {
        return Manager.IsTeamBalance() ? team.GetSize() < Math.max(1, UtilServer.getPlayers().length / GetTeamList().size()) : true;
    }
    public boolean IsPlaying(Player player)
    {
        return GetTeam(player) != null;
    }
    public void RespawnPlayer(final Player player) {
        player.eject();
        player.teleport(GetTeam(player).GetSpawn());
        Manager.Clear(player);
        Manager.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Manager.getPlugin(), new Runnable() {
            public void run() {
                GetKit(player).ApplyKit(player);
            }
        }, 0);
    }
    public Kit GetKit(Player player)
    {
        return _playerKit.get(player);
    }
    public Kit[] GetKits()
    {
        return _kits;
    }
    public boolean HasKit(Kit kit) {
        for (Kit cur : GetKits()) if (cur.equals(kit)) return true;
        return false;
    }
    public boolean HasKit(Player player, Kit kit) {
        if (!IsAlive(player)) return false;
        if (GetKit(player) == null) return false;
        return GetKit(player).equals(kit);
    }
    public void SetKit(Player player, Kit kit, boolean announce) {
        GameTeam team = GetTeam(player);
        if (team != null) {
            if (!team.KitAllowed(kit)) {
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 2f, 0.5f);
                UtilPlayer.message(player, F.main("Kit", F.elem(team.GetFormattedName()) + " cannot use " + F.elem(kit.GetFormattedName() + " Kit") + ""));
                return;
            }
        }
        if (_playerKit.get(player) != null) {
            _playerKit.get(player).Deselected(player);
        }
        _playerKit.put(player, kit);
        kit.Selected(player);
        if (announce) {
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2f, 1f);
            UtilPlayer.message(player, F.main("Kit", "You equipped " + F.elem(kit.GetFormattedName() + " Kit") + ""));
        }
        if (InProgress()) kit.ApplyKit(player);
    }
    public void ValidateKit(Player player, GameTeam team) {
        if (GetKit(player) == null || !team.KitAllowed(GetKit(player))) {
            for (Kit kit : _kits) {
                if (kit.GetAvailability() == KitAvailability.Null) continue;
                if (team.KitAllowed(kit)) {
                    SetKit(player, kit, false);
                    break;
                }
            }
        }
    }
    public void SetPlayerTeam(Player player, GameTeam team, boolean in) {
        GameTeam pastTeam = this.GetTeam(player);
        if (pastTeam != null) {
            pastTeam.RemovePlayer(player);
        }
        team.AddPlayer(player, in);
        ValidateKit(player, team);
        //Scoreboard.SetPlayerTeam(player, team.GetName().toUpperCase());
        //Manager.GetLobby().AddPlayerToScoreboards(player, team.GetName().toUpperCase());
        //Manager.GetGameTournamentManager().setTournamentTeam(player, team);
    }
    public void RegisterKits() {
        for (Kit kit : _kits) UtilServer.getServer().getPluginManager().registerEvents(kit, Manager.getPlugin());
    }
    public void DeregisterKits() {
        for (Kit kit : _kits) HandlerList.unregisterAll(kit);
    }
    public void AddTeam(GameTeam team) {
        GetTeamList().add(team);
        System.out.println("Created Team: " + team.GetName());
    }
    public void RemoveTeam(GameTeam team) {
        if (GetTeamList().remove(team)) System.out.println("Deleted Team: " + team.GetName());
    }
    public boolean HasTeam(GameTeam team) {
        for (GameTeam cur : GetTeamList()) if (cur.equals(team)) return true;
        return false;
    }
    public boolean InProgress()
    {
        return GetState() == GameState.Prepare || GetState() == GameState.Running;
    }
    public GameState GetState()
    {
        return _gameState;
    }
    public ArrayList<GameTeam> GetTeamList()
    {
        return _teamList;
    }

}
