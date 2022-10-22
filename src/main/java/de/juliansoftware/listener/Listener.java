package de.juliansoftware.listener;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.constants.Constants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase(Constants.ByteFox+"Team")) {
            Commands.task1.cancel();
            Commands.task2.cancel();
        }
    }
}
