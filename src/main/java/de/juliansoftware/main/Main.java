package de.juliansoftware.main;

import de.juliansoftware.commands.Commands;
import de.juliansoftware.constants.Constants;
import de.juliansoftware.listener.InventoryListener;
import de.juliansoftware.util.ItemBuilder;
import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends JavaPlugin implements Listener {

    private static Main m;
    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;
    public String ake;
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

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command");
                return true;
            }
            Player player = (Player) sender;
               spin(player);
               return true;

        }

        return false;
    }

    public void shuffle(Inventory inv) {


        //Head >> aus TEAM ARRAY Namen getten
            if (contents == null) {
                ItemStack[] items = new ItemStack[Constants.sizer];
                for (int i = 0; i < Constants.sizer; i++) {
                    if (!(Commands.cfg.getList(Constants.STRING_TEAM) == null)) {Constants.get_team();
                        String name = Constants.PLAYER_TEAM.get(i);
                        String array_name = name.split(" ")[0];
                        String array_role = name.split(" ")[1];
                        String array_join_data = name.split(" ")[2];
                        items[i] = new ItemBuilder(SkullCreator.itemFromName(name)).setDisplayName(array_name).setLore("§8§lName: §7"+ array_name,"§8§lAbteilung: §7"+ array_role,"§8§lSeit dem §7"+array_join_data).toItemStack();
                    }

                }
                contents = items;
            }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for(int index = 0; index < startingIndex; index++) {
            for(int itemstacks = 9; itemstacks < 18; itemstacks++) {
                inv.setItem(itemstacks,contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }
    }
    public void spin(final Player p) {

        Inventory inv = Bukkit.createInventory(null, 3*9, ChatColor.GOLD+"+"+ ChatColor.BOLD+"" );
        shuffle(inv);
        invs.add(inv);
        p.sendMessage(String.valueOf(Constants.sizer));
        p.openInventory(inv);

        new BukkitRunnable() {

            public void run() {

                    for (int itemstacks = 9; itemstacks < 18; itemstacks++)
                        inv.setItem(itemstacks,contents[(itemstacks + itemIndex) % contents.length]);

                    itemIndex++;

                }
            }.runTaskTimer(this, 0, 15);

    }
}

