package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.constants.Constants;
import de.juliansoftware.group.GroupHandler;
import de.juliansoftware.listener.InventoryListener;
import de.juliansoftware.user.UserData;
import de.juliansoftware.user.UserDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static Main instance;

    private UserDataHandler userService;
    private GroupHandler groupService;


    @Override
    public void onEnable() {
        instance = this;
        getDataFolder().mkdirs();

        onInit(Bukkit.getPluginManager());

        groupService = new GroupHandler();
        userService = new UserDataHandler();

    }

    private void onInit(PluginManager pluginManager) {
        pluginManager.registerEvents(new InventoryListener(), this);
        getCommand("team").setExecutor(new Commands(this));


    }

    @Override
    public void onDisable() {
        userService.saveUser();
        groupService.saveGroups();
    }

    public static Main getInstance() {
        return instance;
    }

    public UserDataHandler getUserService() {
        return userService;
    }

    public GroupHandler getGroupService() {
        return groupService;
    }
}

