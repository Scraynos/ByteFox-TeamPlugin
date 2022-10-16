package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.constants.Constants;
import de.juliansoftware.handler.ConfigHandler;
import de.juliansoftware.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        try {
            if(ConfigHandler.handler().getFile().createNewFile()) {
                ConfigHandler.handler().saveList(new ArrayList<>(), Constants.STRING_TEAM);
                ConfigHandler.handler().saveList(new ArrayList<>(), Constants.STRING_RANK);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getCommand("team").setExecutor(new Commands());
       Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {

    }
}

