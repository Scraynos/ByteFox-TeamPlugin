package de.juliansoftware.constants;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Constants {

    public static final String PLAYER_NOT_FOUND = "Diese Person wurde leider nicht gefunden.";
    public static final String RANK_ALREADY_FOUND = "Dieser Rang ist bereits hinterlegt.";
    public static final String RANK_NOT_FOUND = "Dieser Rang wurde leider nicht gefunden.";
    public static final String PLAYER_ALREADY_FOUND = "Diese Person ist bereits hinterlegt.";
    public static final String ByteFox = "§f§lByteFox §8§l| §r§7";
    public static final String COLOR_GREEN = ChatColor.GREEN+"";
    public static final String COLOR_RED = ChatColor.RED+"";
    public static final HashMap<Player, BukkitTask> tasks = new HashMap<>();
    public static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    }


