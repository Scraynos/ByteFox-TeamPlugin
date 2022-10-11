package de.juliansoftware.constants;

import de.juliansoftware.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<String> PLAYER_CORE_TEAM = new ArrayList<>();
    public static List<String> PLAYER_DEVELOPMENT_TEAM = new ArrayList<>();
    public static List<String> PLAYER_TRIAGE_TEAM = new ArrayList<>();
    public static List<String> PLAYER_LEVELDESIGN_TEAM = new ArrayList<>();
    public static List<String> PLAYER_ARCHITECT_TEAM = new ArrayList<>();
    public static List<String> PLAYER_BUILDER_TEAM = new ArrayList<>();
    public static List<String> PLAYER_COMMUNITY_TEAM = new ArrayList<>();
    public static final String STRING_CORE = "Core_Team";
    public static final String STRING_DEVELOPMENT = "Development_Team";
    public static final String STRING_TRIAGE = "Triage_Team";
    public static final String STRING_LEVELDESIGN = "Leveldesign_Team";
    public static final String STRING_ARCHITECT = "Architect_Team";
    public static final String STRING_BUILDER = "Builder_Team";
    public static final String STRING_COMMUNITY = "Community_Team";
    public static final String TEAM_SUCCESS_ADD_MESSAGE_1 = Constants.COLOR_GREEN_BOLD+"Du hast erfolgreich ";
    public static final String TEAM_SUCCESS_REMOVE_MESSAGE_1 = Constants.COLOR_RED_BOLD+"Du hast erfolgreich ";;
    public static final String PLAYER_NOT_FOUND = "Diese Person wurde leider nicht gefunden.";
    public static final String PLAYER_ALREADY_FOUND = "Diese Person ist bereits hinterlegt.";
    public static final String COLOR_GOLD_BOLD = "§6§l";
    public static final String COLOR_GREEN_BOLD = "§a§l";
    public static final String COLOR_RED_BOLD = "§c§l";
    public static final String COLOR_GOLD = "§6";






    public static Inventory inventory = Bukkit.createInventory(null, 3*9, "Team Viewer");
    public static void get_development() {
        PLAYER_DEVELOPMENT_TEAM = (List<String>) Commands.cfg.getList("Development_Team");
    }
    public static void get_triage() {
        PLAYER_TRIAGE_TEAM = (List<String>) Commands.cfg.getList("Triage_Team");
    }

    public static void get_core() {
        PLAYER_CORE_TEAM = (List<String>) Commands.cfg.getList("Core_Team");
    }
    public static void get_leveldesign() {
        PLAYER_LEVELDESIGN_TEAM = (List<String>) Commands.cfg.getList("Leveldesign_Team");
    }
    public static void get_architect() {
        PLAYER_ARCHITECT_TEAM = (List<String>) Commands.cfg.getList("Architect_Team");
    }
    public static void get_builder() {
        PLAYER_BUILDER_TEAM = (List<String>) Commands.cfg.getList("Builder_Team");
    }
    public static void get_community() {
        PLAYER_COMMUNITY_TEAM = (List<String>) Commands.cfg.getList("Community_Team");
    }

}
