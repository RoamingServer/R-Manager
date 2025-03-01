package org.rserver.rmanager.synctime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;
import java.util.TimeZone;

public class main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Ender-现实时间 已启用!");

        // Schedule a repeating task to sync time every 10 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                syncTime();
            }
        }.runTaskTimer(this, 0L, 200L); // 200 ticks = 10 seconds
    }

    @Override
    public void onDisable() {
        getLogger().info("Ender-现实时间 已禁用!");
    }

    private void syncTime() {
        // Get the current real-world time
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        int hours = calendar.get(Calendar.HOUR_OF_DAY); // 0-23
        int minutes = calendar.get(Calendar.MINUTE); // 0-59
        int seconds = calendar.get(Calendar.SECOND); // 0-59

        // Convert real-world time to Minecraft time
        // Minecraft has 24000 ticks per day, so each hour is 1000 ticks
        long minecraftTime = (hours * 1000L + (minutes * 1000L / 60L) + (seconds * 1000L / 3600L)) % 24000L;

        // Set the time on the server
        for (var world : Bukkit.getWorlds()) {
            world.setTime(minecraftTime);
        }

        // Send a message to all players
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        String message = ChatColor.GOLD + "" + ChatColor.WHITE + timeString;
        for (var player : Bukkit.getOnlinePlayers()) {
            System.out.println("服务器娘 >> 服务器时间已同步至现实时间：" + message);
        }
    }
}