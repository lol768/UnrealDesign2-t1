package com.tenjava.entries.UnrealDesign2.t1;

import com.tenjava.entries.UnrealDesign2.t1.configs.ConfigManager;
import com.tenjava.entries.UnrealDesign2.t1.database.DBManager;
import com.tenjava.entries.UnrealDesign2.t1.listeners.UPlayerJoinEvent;
import com.tenjava.entries.UnrealDesign2.t1.listeners.UPlayerKillEntityEvent;
import com.tenjava.entries.UnrealDesign2.t1.listeners.UPlayerQuitEvent;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayer;
import com.tenjava.entries.UnrealDesign2.t1.players.UPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin
{
    
    @Override
    public void onEnable()
    {
        ConfigManager.getInstance().setup(this);
        DBManager.getInstance().setup(this);
        UPlayerManager.getInstance().setup();
        
        for(Player p : getServer().getOnlinePlayers())
        {
            UPlayerManager.getInstance().login(p);
        }
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new UPlayerJoinEvent(), this);
        pm.registerEvents(new UPlayerQuitEvent(), this);
        pm.registerEvents(new UPlayerKillEntityEvent(), this);
        
        
    }
    
    @Override
    public void onDisable()
    {
        for(Player p : getServer().getOnlinePlayers())
        {
            UPlayerManager.getInstance().logout(p);
        }
    }
}
