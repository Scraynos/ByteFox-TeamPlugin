package de.juliansoftware.commands;

import de.juliansoftware.constants.Constants;

import de.juliansoftware.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
public class Commands implements CommandExecutor {

    public static File f;
    public static FileConfiguration cfg;

    static {
        Commands.f = new File("plugins/Team", "config.yml");
        Commands.cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(Commands.f);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if (!(sender instanceof Player)) return false;
        if (cmd.getName().equalsIgnoreCase("team")) {
            if (args.length == 0) {
                p.sendMessage("============ Team ============");
                p.sendMessage("/team view");
                p.sendMessage("/team add [name] [role]");
                p.sendMessage("/team remove [name]");
                p.sendMessage("============ Team ============");
            }
            if (args.length == 1) {
                if(args[0].equalsIgnoreCase("view")) {
                    p.sendMessage("Erfolg");
                    Inventory inventory = Bukkit.createInventory(null, 1*9, "Test");
                    inventory.setItem(0, new ItemBuilder(Material.PAPER).setDisplayname("Test").build());
                    p.openInventory(inventory);
                }



























































            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("add")) {
                    switch (args[1].toLowerCase()) {
                        case "core_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_CORE) == null)) {Constants.get_core();}
                            if (!(Constants.PLAYER_CORE_TEAM.contains(args[2]))) {
                                Constants.PLAYER_CORE_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_CORE, Constants.PLAYER_CORE_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1 + args[2] + " zum " + Constants.STRING_CORE + " hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "development_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_DEVELOPMENT) == null)) {Constants.get_development();}
                            if (!(Constants.PLAYER_DEVELOPMENT_TEAM.contains(args[2]))) {
                                Constants.PLAYER_DEVELOPMENT_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_DEVELOPMENT, Constants.PLAYER_DEVELOPMENT_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.STRING_DEVELOPMENT+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "triage_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_TRIAGE) == null)) {Constants.get_triage();}
                            if (!(Constants.PLAYER_TRIAGE_TEAM.contains(args[2]))) {
                                Constants.PLAYER_TRIAGE_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_TRIAGE, Constants.PLAYER_TRIAGE_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.PLAYER_TRIAGE_TEAM+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "leveldesign_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_LEVELDESIGN) == null)) {Constants.get_leveldesign();}
                            if (!(Constants.PLAYER_LEVELDESIGN_TEAM.contains(args[2]))) {
                                Constants.PLAYER_LEVELDESIGN_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_LEVELDESIGN, Constants.PLAYER_LEVELDESIGN_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.STRING_LEVELDESIGN+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "architect_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_ARCHITECT) == null)) {Constants.get_architect();}
                            if (!(Constants.PLAYER_ARCHITECT_TEAM.contains(args[2]))) {
                                Constants.PLAYER_ARCHITECT_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_ARCHITECT, Constants.PLAYER_ARCHITECT_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.STRING_ARCHITECT+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "builder_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_BUILDER) == null)) {Constants.get_builder();}
                            if (!(Constants.PLAYER_BUILDER_TEAM.contains(args[2]))) {
                                Constants.PLAYER_BUILDER_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_BUILDER, Constants.PLAYER_BUILDER_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.STRING_BUILDER+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        case "community_team" :
                            if (!(Commands.cfg.getList(Constants.STRING_COMMUNITY) == null)) {Constants.get_community();}
                            if (!(Constants.PLAYER_COMMUNITY_TEAM.contains(args[2]))) {
                                Constants.PLAYER_COMMUNITY_TEAM.add(args[2]);
                                Commands.cfg.set(Constants.STRING_COMMUNITY, Constants.PLAYER_COMMUNITY_TEAM);
                                p.sendMessage("");
                                p.sendMessage(Constants.TEAM_SUCCESS_ADD_MESSAGE_1+args[2]+" zum "+Constants.STRING_COMMUNITY+" hinzugefügt.");
                            }else {
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_ALREADY_FOUND);
                            }
                            break;

                        default:
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_GOLD_BOLD+"============ Team Add Error ============");
                            p.sendMessage("");
                            p.sendMessage("  "+Constants.COLOR_GOLD+"Du hast die Auswahl zwischen:");
                            p.sendMessage("");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_CORE+",");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_TRIAGE+",");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_DEVELOPMENT+",");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_BUILDER+",");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_LEVELDESIGN+",");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_ARCHITECT+" und dem");
                            p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_COMMUNITY+".");
                            p.sendMessage("");
                            p.sendMessage(Constants.COLOR_GOLD_BOLD+"============ Team Add Error ============");
                    }
                        try {
                            Commands.cfg.save(Commands.f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        switch (args[1].toLowerCase()) {
                            case "core_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_CORE) == null)) {Constants.get_core();}
                                if (Constants.PLAYER_CORE_TEAM.contains(args[2])) {
                                    Constants.PLAYER_CORE_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_CORE, Constants.PLAYER_CORE_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_CORE+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "development_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_DEVELOPMENT) == null)) {Constants.get_development();}
                                if (Constants.PLAYER_DEVELOPMENT_TEAM.contains(args[2])) {
                                    Constants.PLAYER_DEVELOPMENT_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_DEVELOPMENT, Constants.PLAYER_DEVELOPMENT_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_DEVELOPMENT+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "triage_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_TRIAGE) == null)) {Constants.get_triage();}
                                if (Constants.PLAYER_TRIAGE_TEAM.contains(args[2])) {
                                    Constants.PLAYER_TRIAGE_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_TRIAGE, Constants.PLAYER_TRIAGE_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_TRIAGE+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "leveldesign_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_LEVELDESIGN) == null)) {Constants.get_leveldesign();}
                                if (Constants.PLAYER_LEVELDESIGN_TEAM.contains(args[2])) {
                                    Constants.PLAYER_LEVELDESIGN_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_LEVELDESIGN, Constants.PLAYER_LEVELDESIGN_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+Constants.STRING_LEVELDESIGN+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "architect_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_ARCHITECT) == null)) {Constants.get_architect();}
                                if (Constants.PLAYER_ARCHITECT_TEAM.contains(args[2])) {
                                    Constants.PLAYER_ARCHITECT_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_ARCHITECT, Constants.PLAYER_ARCHITECT_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_ARCHITECT+" entfernt.");
                                }else {p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "builder_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_BUILDER) == null)) {Constants.get_builder();}
                                if (Constants.PLAYER_BUILDER_TEAM.contains(args[2])) {
                                    Constants.PLAYER_BUILDER_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_BUILDER, Constants.PLAYER_BUILDER_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_BUILDER+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            case "community_team" :
                                if (!(Commands.cfg.getList(Constants.STRING_COMMUNITY) == null)) {Constants.get_community();}
                                if (Constants.PLAYER_COMMUNITY_TEAM.contains(args[2])) {
                                    Constants.PLAYER_COMMUNITY_TEAM.remove(args[2]);
                                    Commands.cfg.set(Constants.STRING_COMMUNITY, Constants.PLAYER_COMMUNITY_TEAM);
                                    p.sendMessage("");
                                    p.sendMessage(Constants.TEAM_SUCCESS_REMOVE_MESSAGE_1+args[2]+" vom "+Constants.STRING_COMMUNITY+" entfernt.");
                                }else {
                                    p.sendMessage("");
                                    p.sendMessage(Constants.COLOR_RED_BOLD+Constants.PLAYER_NOT_FOUND);}
                                break;

                            default:
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_GOLD_BOLD+"============ Team Remove Error ============");
                                p.sendMessage("");
                                p.sendMessage("  "+Constants.COLOR_GOLD+"Du hast die Auswahl zwischen:");
                                p.sendMessage("");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_CORE+",");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_TRIAGE+",");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_DEVELOPMENT+",");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_BUILDER+",");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_LEVELDESIGN+",");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_ARCHITECT+" und dem");
                                p.sendMessage("  "+Constants.COLOR_GOLD+Constants.STRING_COMMUNITY+".");
                                p.sendMessage("");
                                p.sendMessage(Constants.COLOR_GOLD_BOLD+"============ Team Remove Error ============");
                        }
                        try {
                            Commands.cfg.save(Commands.f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);

                        }
                    }
                }

            }
            return true;
        }
    }
