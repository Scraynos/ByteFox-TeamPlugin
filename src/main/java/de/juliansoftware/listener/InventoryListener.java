package de.juliansoftware.listener;

import de.juliansoftware.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
    private final String GUI_NAME = "§a§lTeam";
    public static void openGUI(Player p) {
        Inventory inventory = Bukkit.createInventory(null, 5*9, "§a§lTeam");
        for (int i = 0; i <= 9; i++) {
            inventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setLocalizedName("").setDisplayName("§0§l ").toItemStack());
        }
        for (int i = 44; i >= 35; i--) {
            inventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setLocalizedName("").setDisplayName("§0§l ").toItemStack());
        }
        ItemStack PLACE_HOLDER = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setLocalizedName("").setDisplayName("§0§l ").toItemStack();
        inventory.setItem(17, PLACE_HOLDER);
        inventory.setItem(18, PLACE_HOLDER);
        inventory.setItem(26, PLACE_HOLDER);
        inventory.setItem(27, PLACE_HOLDER);
        p.openInventory(inventory);
    }


    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(GUI_NAME)) {
        event.setCancelled(true);

        }



    }

}
