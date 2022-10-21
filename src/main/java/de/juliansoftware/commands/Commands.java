package de.juliansoftware.commands;

import de.juliansoftware.constants.Constants;


import de.juliansoftware.group.GroupHandler;
import de.juliansoftware.main.Main;
import de.juliansoftware.user.UserData;
import de.juliansoftware.user.UserDataHandler;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.cloud.item.BrokenItem;
import dev.brokenstudio.cloud.item.Skull;
import dev.brokenstudio.cloud.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Driver;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

//TODO: player should allow a color for every rank (setDisplayName should get the color)
//TODO: Displayname upperCase
//TODO: Move /team remove rname pname --> arg rname not required
//TODO: DB Redis/MySQL

public class Commands implements CommandExecutor {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    private Main plugin;


    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        final Player p = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("team")) {

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("view")) {
                    if (plugin.getUserService().getData().size() > 0) {
                        Player player = (Player) sender;
                        spin(player, new ArrayList<>(Main.getInstance().getUserService().getData().values()));
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+"Aktuell sind keine Teammitglieder abgespeichert.");
                    }
                    return true;
                }
            }

            if (p.hasPermission("brokencloud.triage")) {

                if (args.length == 0) {

                    p.sendMessage(Constants.ByteFox + "§l============ Team ============");
                    p.sendMessage(Constants.COLOR_GREEN + "  §l/team view");
                    p.sendMessage(Constants.COLOR_GREEN + "  §l/team <add | remove> <user_name>");
                    p.sendMessage(Constants.COLOR_GREEN + "  §l/team rank <add | remove> <rank_name>");
                    p.sendMessage(Constants.COLOR_GREEN + "  §l/team ranks");
                    p.sendMessage(Constants.ByteFox + "§l============ Team ============");
                }


                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("ranks")) {
                        p.sendMessage("");
                        Main.getInstance().getGroupService().getGroups().forEach(cr -> {
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_GREEN+cr);
                        });
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("rank")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team rank <add / remove> <rank_name>?");
                    } else if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team add <rank_name> <player_name>?");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team remove <player_name>?");
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team <add | remove | rank>?");
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team add " + args[1] + " <player_name>?");

                    } else if (args[0].equalsIgnoreCase("remove")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team remove <player_name>?");
                    } else if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team rank add <player_name>?");
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team rank remove <player_name>?");
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED+"Meintest du /team rank <add | remove> <player_name>?");
                        }
                    }
                }


                if (args.length == 3) {

                    if (args[0].equalsIgnoreCase("add")) {
                        if (plugin.getGroupService().getGroup(args[1]) != null) {
                            UUID target = UUIDFetcher.getUUID(args[2]);
                            if (target == null) {
                                p.sendMessage(Constants.ByteFox+"Dieser Spieler ist aktuell nicht online.");
                                return true;
                            }
                                if (plugin.getUserService().getData(target) == null) {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.ByteFox+Constants.COLOR_GREEN+"Du hast erfolgreich " + args[2] + " zu " + args[1] + " hinzugefügt.");
                                    plugin.getUserService().addUser(target, args[1], args[2]);
                                }else {
                                    p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.PLAYER_ALREADY_FOUND);
                                }
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.RANK_NOT_FOUND);
                        }
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        UUID target = UUIDFetcher.getUUID(args[2]);
                        if (plugin.getUserService().getData(target) != null) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_GREEN + "Du hast erfolgreich " + args[2] + " entfernt.");
                            plugin.getUserService().removeUser(target);
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.PLAYER_NOT_FOUND);
                        }
                    }


                    if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            if (plugin.getGroupService().getGroup(args[2]) == null) {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_GREEN + "Du hast erfolgreich " + args[2] + " hinzugefügt.");
                                plugin.getGroupService().addGroup(args[2]);
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.RANK_ALREADY_FOUND);
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (plugin.getGroupService().getGroup(args[2]) != null) {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_GREEN + "Du hast erfolgreich " + args[2] + " entfernt.");
                                plugin.getGroupService().removeGroup(args[2]);
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.RANK_NOT_FOUND);
                            }
                        } else {

                            p.sendMessage("Falsche Benutzung des Befehls.");
                        }
                        CompletableFuture.runAsync(() -> {
                            plugin.getGroupService().saveGroups();
                            plugin.getUserService().saveUser();
                        });
                    }
                }
            }
        }
        return true;
    }




    public void shuffle(Inventory inv, ArrayList<UserData> s) {

            ItemStack[] items = new ItemStack[s.size()];

            for (int i = 0; i < s.size(); i++) {

                    UserData name = s.get(i);
                    String array_name = name.getName();
                    String array_role = name.getGroup().getName();
                    String array_date = Constants.format.format(name.getDate());
                    items[i] = new BrokenItem(Skull.getCustomSkull(CloudPlugin.api().getPlayerHeadStorage().getTexture(UUIDFetcher.getUUID(array_name)))).name(ChatColor.BOLD+array_name).lore("",ChatColor.WHITE+"Aktuelle Position: "+ChatColor.GRAY+array_role, ChatColor.WHITE+"Beigetreten am: "+ChatColor.GRAY+array_date);

            }
            contents = items;

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for(int index = 0; index < startingIndex; index++) {
            for(int itemstacks = 10; itemstacks < 17; itemstacks++) {
                inv.setItem(itemstacks,contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }
        for (int i = 0; i < 10; i++)
            inv.setItem(i, new BrokenItem(Material.BLACK_STAINED_GLASS_PANE).name(ChatColor.BLACK+""));
        for (int i = 17; i < 27; i++)
            inv.setItem(i, new BrokenItem(Material.BLACK_STAINED_GLASS_PANE).name(ChatColor.BLACK+""));
    }
    public void spin(final Player p, ArrayList<UserData> s) {

        CompletableFuture.runAsync(() -> {
            Inventory inv = Bukkit.createInventory(p, 3*9, Constants.ByteFox+"Team");
            inv.clear();
            shuffle(inv, s);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.openInventory(inv);


                    Constants.tasks.put(p,
                            new BukkitRunnable() {

                                public void run() {

                                    for (int itemstacks = 10; itemstacks < 17; itemstacks++)
                                        inv.setItem(itemstacks,contents[(itemstacks + itemIndex) % contents.length]);

                                    itemIndex++;

                                }
                            }.runTaskTimer(Main.getPlugin(Main.class), 0, 30));
                }
            }.runTask(Main.getPlugin(Main.class));
        });

    }
}
