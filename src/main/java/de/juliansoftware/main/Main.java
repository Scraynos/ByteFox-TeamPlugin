package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main m;

    @Override
    public void onEnable() {
       getCommand("team").setExecutor(new Commands());
       Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {

    }
    public static Main getInstance() {
        return Main.m;
    }
}

