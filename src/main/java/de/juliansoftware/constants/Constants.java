package de.juliansoftware.constants;

import de.juliansoftware.commands.Commands;
import dev.dbassett.skullcreator.SkullCreator;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.bind.TypeAdapters.URL;
import static de.juliansoftware.commands.Commands.cfg;

public class Constants {
    public static List<String> PLAYER_TEAM = new ArrayList<>();
    public static List<String> RANK_TEAM = new ArrayList<>();

    public static final String STRING_TEAM = "Team";
    public static final String STRING_RANK = "Rank";
    public static final String STRING_CORE = "Core_Team";
    public static final String STRING_DEVELOPMENT = "Development_Team";
    public static final String STRING_TRIAGE = "Triage_Team";
    public static final String STRING_LEVELDESIGN = "Leveldesign_Team";
    public static final String STRING_ARCHITECT = "Architect_Team";
    public static final String STRING_BUILDER = "Builder_Team";
    public static final String STRING_COMMUNITY = "Community_Team";
    public static final String PLAYER_NOT_FOUND = "Diese Person wurde leider nicht gefunden.";
    public static final String RANK_ALREADY_FOUND = "Dieser Rang ist bereits hinterlegt.";
    public static final String RANK_NOT_FOUND = "Dieser Rang wurde leider nicht gefunden.";
    public static final String PLAYER_ALREADY_FOUND = "Diese Person ist bereits hinterlegt.";
    public static final String COLOR_GOLD_BOLD = "§6§l";
    public static final String COLOR_GREEN_BOLD = "§a§l";
    public static final String COLOR_RED_BOLD = "§c§l";
    public static final String COLOR_GOLD = "§6";
    public static int sizer = cfg.getList("Team").size();

    public static void get_team() {
        PLAYER_TEAM = (List<String>) cfg.getList("Team");
    }
    public static void get_rank() {
        RANK_TEAM = (List<String>) cfg.getList("Rank");
    }





    }


