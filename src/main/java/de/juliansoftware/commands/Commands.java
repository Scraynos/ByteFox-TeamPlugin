package de.juliansoftware.commands;

import com.sun.org.apache.bcel.internal.Const;
import de.juliansoftware.constants.Constants;

import de.juliansoftware.handler.ConfigHandler;
import de.juliansoftware.main.Main;
import de.juliansoftware.util.ItemBuilder;
import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//TODO: player should allow a color for every rank (setDisplayName should get the color)
//TODO: Displayname upperCase

public class Commands implements CommandExecutor {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        ArrayList<String> teamList = ConfigHandler.handler().getList(Constants.STRING_TEAM);
        ArrayList<String> rankList = ConfigHandler.handler().getList(Constants.STRING_RANK);

        long entryCountT = 0;
        long entryCountP = 0;
        if (args.length == 3) {
            entryCountP = teamList.stream().map(x -> x.split(" ")[0]).filter(x -> x.equalsIgnoreCase(args[2])).count();
            entryCountT = rankList.stream().map(x -> x.split(" ")[0]).filter(x -> x.equalsIgnoreCase(args[2])).count();
        }

        if (!(sender instanceof Player)) return false;
        final Player p = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("team")) {

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("view")) {
                    if (teamList != null) {
                        Player player = (Player) sender;
                        spin(player, teamList);
                        return true;
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.COLOR_GREEN_BOLD+"Aktuell sind keine Teammitglieder abgespeichert.");
                    }
                }
            }

            if (p.hasPermission("brokencloud.triage")) {

                if (args.length == 0) {

                    p.sendMessage(ChatColor.AQUA + "§l============ Team ============");
                    p.sendMessage(ChatColor.AQUA + "  §l/team view");
                    p.sendMessage(ChatColor.AQUA + "  §l/team <add | remove> <rank_name> <user_name>");
                    p.sendMessage(ChatColor.AQUA + "  §l/team rank <add | remove> <rank_name>");
                    p.sendMessage(ChatColor.AQUA + "  §l/team ranks");
                    p.sendMessage(ChatColor.AQUA + "§l============ Team ============");
                }


                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("ranks")) {
                        //TODO: give out array list
                    }
                    if (args[0].equalsIgnoreCase("rank")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team rank <add / remove> <rank_name>?");
                    } else if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team add <rank_name> <player_name>?");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team remove <rank_name> <player_name>?");
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team <add | remove | rank>?");
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team add " + args[1] + " <player_name>?");

                    } else if (args[0].equalsIgnoreCase("remove")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team remove " + args[1] + " <player_name>?");
                    } else if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team rank add <player_name>?");
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team rank remove <player_name>?");
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.RED_BOLD_EXCLE+Constants.COLOR_AQUA_BOLD + "Meintest du /team rank <add | remove> <player_name>?");
                        }
                    }
                }


                if (args.length == 3) {


                    if (args[0].equalsIgnoreCase("add")) {
                        if (rankList.contains(args[1].toLowerCase())) {
                            if (entryCountP == 0) {
                                teamList.add(args[2].toLowerCase() + " " + args[1].toLowerCase() + " " + java.time.LocalDate.now());
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_GREEN_BOLD + "Du hast erfolgreich " + args[2] + " zum " + args[1] + " hinzugefügt.");
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD + Constants.PLAYER_ALREADY_FOUND);
                            }
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_RED_BOLD + Constants.RANK_NOT_FOUND);
                        }
                    }


                    if (args[0].equalsIgnoreCase("remove")) {
                        if (entryCountP == 1) {
                            teamList.removeIf(x -> x.contains(args[2].toLowerCase()));
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_GREEN_BOLD + "Du hast erfolgreich " + args[2] + " entfernt.");
                            ConfigHandler.handler().saveList(rankList, Constants.STRING_RANK);
                            ConfigHandler.handler().saveList(teamList, Constants.STRING_TEAM);
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_RED_BOLD + Constants.PLAYER_NOT_FOUND);
                        }
                    }


                    if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            if (entryCountT == 0) {
                                rankList.add(args[2].toLowerCase());
                                p.sendMessage(Constants.COLOR_GREEN_BOLD + "Du hast erfolgreich " + args[2] + " hinzugefügt.");
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD + Constants.RANK_ALREADY_FOUND);
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (entryCountT == 1) {
                                rankList.remove(args[2].toLowerCase());
                                p.sendMessage(Constants.COLOR_GREEN_BOLD + "Du hast erfolgreich " + args[2] + " entfernt.");
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD + Constants.RANK_NOT_FOUND);
                            }
                        } else {
                            p.sendMessage("Falsche Benutzung des Befehls.");
                        }
                    }
                }
            }
        }
        ConfigHandler.handler().saveList(rankList, Constants.STRING_RANK);
        ConfigHandler.handler().saveList(teamList, Constants.STRING_TEAM);
        return true;
    }


    public void shuffle(Inventory inv, ArrayList<String> s) {

            ItemStack[] items = new ItemStack[s.size()];
            for (int i = 0; i < s.size(); i++) {

                    String name = s.get(i);
                    String array_name = name.split(" ")[0];
                    String array_role = name.split(" ")[1];
                    String array_date = name.split(" ")[2];
                    items[i] = new ItemBuilder(SkullCreator.itemFromName(array_name)).setDisplayName(ChatColor.BOLD+array_name).setLore("",ChatColor.DARK_GRAY+"Aktuelle Position: "+ChatColor.GRAY+array_role, ChatColor.DARK_GRAY+"Beigetreten am: "+ChatColor.GRAY+array_date).toItemStack();

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
            inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(ChatColor.BLACK+"").toItemStack());
        for (int i = 17; i < 27; i++)
            inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(ChatColor.BLACK+"").toItemStack());
    }
    public void spin(final Player p, ArrayList<String> s) {

        Inventory inv = Bukkit.createInventory(p, 3*9, "§c§lUnser Team");
        inv.clear();
        shuffle(inv, s);
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
    }
