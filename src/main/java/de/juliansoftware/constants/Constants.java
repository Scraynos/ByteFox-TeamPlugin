package de.juliansoftware.constants;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class Constants {

    public static final String STRING_TEAM = "Team";
    public static final String STRING_RANK = "Rank";
    public static final String PLAYER_NOT_FOUND = "Diese Person wurde leider nicht gefunden.";
    public static final String RANK_ALREADY_FOUND = "Dieser Rang ist bereits hinterlegt.";
    public static final String RANK_NOT_FOUND = "Dieser Rang wurde leider nicht gefunden.";
    public static final String PLAYER_ALREADY_FOUND = "Diese Person ist bereits hinterlegt.";
    public static final String COLOR_GREEN_BOLD = "§a§l";
    public static final String COLOR_AQUA_BOLD = ChatColor.AQUA+"§l";
    public static final String COLOR_RED_BOLD = ChatColor.RED+"§l";
    public static final String RED_BOLD_EXCLE = ChatColor.RED+"§l>> ";
    public static final HashMap<Player, BukkitTask> tasks = new HashMap<>();






    }


