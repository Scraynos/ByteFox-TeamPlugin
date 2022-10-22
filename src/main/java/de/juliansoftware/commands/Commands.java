package de.juliansoftware.commands;

import de.juliansoftware.constants.Constants;


import de.juliansoftware.main.Main;
import de.juliansoftware.user.UserData;
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
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class Commands implements CommandExecutor {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex;

    private Main plugin;
    public static BukkitTask task2 = null;
    public static BukkitTask task1 = null;

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
                        itemIndex = 0;
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

                    p.sendMessage("§f§l============ Team ============");
                    p.sendMessage("§7  §l/team view");
                    p.sendMessage("§7  §l/team <add | remove> <user_name>");
                    p.sendMessage("§7  §l/team rank <add | remove> <rank_name>");
                    p.sendMessage("§7  §l/team ranks");
                    p.sendMessage("§f§l============ Team ============");
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
                        p.sendMessage(Constants.ByteFox+"§7Meintest du /team rank <add / remove> <rank_name>?");
                    } else if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+"§7Meintest du /team add <rank_name> <player_name>?");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+"§7Meintest du /team remove <player_name>?");
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+"§7Meintest du /team <add | remove | rank>?");
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("add")) {
                        p.sendMessage("");
                        p.sendMessage(Constants.ByteFox+"§7Meintest du /team add " + args[1] + " <player_name>?");

                    } else if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+"§7Meintest du /team rank add <player_name>?");
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+"§7Meintest du /team rank remove <player_name>?");
                        }else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+"§7Meintest du /team rank <add | remove> <player_name>?");
                        }
                    }else if (args[0].equalsIgnoreCase("remove")) {
                        UUID target = UUIDFetcher.getUUID(args[1]);
                        if (plugin.getUserService().getData(target) != null) {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+"§7Du hast erfolgreich §f" + args[1] + "§7 entfernt.");
                            plugin.getUserService().removeUser(target);
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.PLAYER_NOT_FOUND);
                        }
                    }
                }


                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (plugin.getGroupService().getGroup(args[1]) != null) {
                            UUID target = UUIDFetcher.getUUID(args[2]);
                                if (plugin.getUserService().getData(target) == null) {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.ByteFox+"§7Du hast erfolgreich§f " + args[2] + " §7zu §f" + args[1] + " §7hinzugefügt.");
                                    plugin.getUserService().addUser(target, args[1], args[2]);
                                }else {
                                    p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.PLAYER_ALREADY_FOUND);
                                }
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.ByteFox+Constants.RANK_NOT_FOUND);
                        }
                    }


                    if (args[0].equalsIgnoreCase("rank")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            if (plugin.getGroupService().getGroup(args[2]) == null) {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+ "§7Du hast erfolgreich §f" + args[2] + "§7 hinzugefügt.");
                                plugin.getGroupService().addGroup(args[2]);
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.RANK_ALREADY_FOUND);
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (plugin.getGroupService().getGroup(args[2]) != null) {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+"§7Du hast erfolgreich §f" + args[2] + " §7entfernt.");
                                plugin.getGroupService().removeGroup(args[2]);
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.ByteFox+Constants.COLOR_RED + Constants.RANK_NOT_FOUND);
                            }
                        } else {

                            p.sendMessage("§7Falsche Benutzung des §fBefehls§7.");
                        }
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
            Constants.tasks.clear();
            shuffle(inv, s);
            task1 = new BukkitRunnable() {
                @Override
                public void run() {
                    p.openInventory(inv);

                    Constants.tasks.put(p,
                            task2 = new BukkitRunnable() {

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
