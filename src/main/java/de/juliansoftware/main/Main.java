package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.group.GroupHandler;
import de.juliansoftware.listener.Listener;
import de.juliansoftware.user.UserDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private UserDataHandler userService;
    private GroupHandler groupService;


    @Override
    public void onEnable() {
        instance = this;

        onInit(Bukkit.getPluginManager());

        groupService = new GroupHandler();
        userService = new UserDataHandler();

    }

    private void onInit(PluginManager pluginManager) {
        getCommand("team").setExecutor(new Commands(this));
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
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

