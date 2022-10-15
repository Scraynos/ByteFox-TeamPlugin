package de.juliansoftware.commands;

import com.sun.org.apache.bcel.internal.Const;
import de.juliansoftware.constants.Constants;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


import java.io.File;
import java.io.IOException;
import java.util.List;


import static sun.awt.image.MultiResolutionCachedImage.map;

public class Commands implements CommandExecutor {

    public static File f;
    public static FileConfiguration cfg;

    static {
        Commands.f = new File("plugins/Team", "config.yml");
        Commands.cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(Commands.f);
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        long entryCountP = Constants.PLAYER_TEAM.stream().map(x -> x.split(" ")[0]).filter(x -> x.equalsIgnoreCase(args[2])).count();
        long entryCountT = Constants.RANK_TEAM.stream().map(x -> x.split(" ")[0]).filter(x -> x.equalsIgnoreCase(args[1])).count();
        if (!(sender instanceof Player)) return false;
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("team")) {
            if (args.length == 0) {
                p.sendMessage("============ Team ============");
                p.sendMessage("/team view");
                p.sendMessage("/team add [name] [role]");
                p.sendMessage("/team remove [name]");
                p.sendMessage("============ Team ============");
            }
            if(args[0].equalsIgnoreCase("view")) {
p.sendMessage("");
                }
            if (args[0].equalsIgnoreCase("add")) {
                    if (!(Commands.cfg.getList(Constants.STRING_TEAM) == null)) { Constants.get_team(); }
                    if (!(Commands.cfg.getList(Constants.STRING_RANK) == null)) { Constants.get_rank(); }
                        if (Constants.RANK_TEAM.contains(args[1].toLowerCase())) {
                            if (entryCountP == 0) {
                                Constants.PLAYER_TEAM.add(args[2].toLowerCase() + " " + args[1].toLowerCase());
                                Commands.cfg.set(Constants.STRING_TEAM,Constants.PLAYER_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_GREEN_BOLD+"Du hast erfolgreich "+args[2]+" zum "+args[1]+" hinzugefügt.");
                            } else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_RED_BOLD+Constants.RANK_NOT_FOUND);
                        }
                    try {
                        Commands.cfg.save(Commands.f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

            }
            if (args[0].equalsIgnoreCase("remove")) {
                    if (!(Commands.cfg.getList(Constants.STRING_TEAM) == null)) { Constants.get_team(); }
                    if (entryCountP == 1) {
                        Constants.PLAYER_TEAM.removeIf(x -> x.contains(args[2].toLowerCase()));
                        Commands.cfg.set(Constants.STRING_TEAM, Constants.PLAYER_TEAM);
                        p.sendMessage("");
                        p.sendMessage(Constants.COLOR_GREEN_BOLD + "Du hast erfolgreich " + args[2] + " von/m " + args[1] + " entfernt.");
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Constants.COLOR_RED_BOLD + Constants.PLAYER_NOT_FOUND);
                    }
                    try {
                        Commands.cfg.save(Commands.f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);

                    }
                }
            if (args[0].equalsIgnoreCase("rank")) {
                    if (args[1].equalsIgnoreCase("add")) {
                        if (entryCountT == 0) {
                            if (!(Commands.cfg.getList(Constants.STRING_RANK) == null)) {Constants.get_rank();}
                            Constants.RANK_TEAM.add(args[2].toLowerCase());
                            Commands.cfg.set(Constants.STRING_RANK, Constants.RANK_TEAM);
                            p.sendMessage(Constants.COLOR_GREEN_BOLD+"Du hast erfolgreich "+args[2]+" hinzugefügt.");
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_RED_BOLD+Constants.RANK_ALREADY_FOUND);
                        }
                        try {
                            Commands.cfg.save(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (args[1].equalsIgnoreCase("remove")) {
                        if (entryCountT == 1) {
                            if (!(Commands.cfg.getList(Constants.STRING_RANK) == null)) {Constants.get_rank();}
                            Constants.RANK_TEAM.removeIf(x -> x.contains(args[2].toLowerCase()));
                            Commands.cfg.set(Constants.STRING_RANK, Constants.RANK_TEAM);
                            p.sendMessage(Constants.COLOR_RED_BOLD+"Du hast erfolgreich "+args[2]+" entfernt.");
                        } else {
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_RED_BOLD+Constants.RANK_NOT_FOUND);
                        }
                    } else {
                        p.sendMessage("Falsche Benutzung des Befehls.");
                    }
            }
            }
            return true;
        }
    }
