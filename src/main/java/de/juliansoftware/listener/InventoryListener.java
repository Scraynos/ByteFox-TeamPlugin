package de.juliansoftware.listener;

import de.juliansoftware.constants.Constants;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§c§lUnser Team")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void inventoryClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§c§lUnser Team")) {
            Constants.tasks.get(e.getPlayer()).cancel();
        }
    }

}
