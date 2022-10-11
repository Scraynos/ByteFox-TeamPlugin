package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import org.bukkit.command.CommandExecutor;

public class LoadStuff {

    public static void loadListener_Commands() {
        Main.getInstance().getCommand("bedwars").setExecutor((CommandExecutor)new Commands());
    }

}
