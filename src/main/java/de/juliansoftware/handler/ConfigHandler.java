package de.juliansoftware.handler;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigHandler {

    private static ConfigHandler handler;

    public File getFile() {
        return new File("plugins/ByteFoxTeamPlugin", "config.yml");
    }

    public YamlConfiguration yamlConfiguration () {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void saveConfiguration(YamlConfiguration cfg) {
        try {
            cfg.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getList(String listName) {
        return new ArrayList<>(yamlConfiguration().getStringList(listName));
    }

    public void saveList(ArrayList<String> list, String listName) {
        YamlConfiguration cfg = yamlConfiguration();
        cfg.set(listName, list);
        saveConfiguration(cfg);
    }

    public static ConfigHandler handler() {
        if (handler == null)
            handler = new ConfigHandler();
        return handler;
    }
}
