package org.rserver.rmanager.stayday;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;

public class main extends JavaPlugin implements CommandExecutor {
    private boolean isDayLocked = false;

    @Override
    public void onEnable() {
        getLogger().info("R-Manager-StayDay 已启用!");
        getCommand("stayday").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("R-Manager-StayDay 已禁用!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("stayday")) {
            // 检查权限
            if (!sender.hasPermission("rserver.stayday.*")) {
                sender.sendMessage("你没有权限执行此命令.");
                return true;
            }

            if (isDayLocked) {
                isDayLocked = false;
                sender.sendMessage("\u00a7f\u00A7l[R-Manager-保持白天] \u00A7f已经取消保持白天!");
                for (World world : getServer().getWorlds()) {
                    world.setGameRuleValue("doDaylightCycle", "true");
                }
            } else {
                isDayLocked = true;
                sender.sendMessage("\u00a7f\u00A7l[R-Manager-保持白天] \u00A7f已经设置好保持白天了!");
                for (World world : getServer().getWorlds()) {
                    // 检查当前时间，如果不是白天，则调整到白天
                    long time = world.getTime();
                    if (time < 1000 || time > 13000) {
                        world.setTime(1000); // 设置为白天时间
                    }
                    world.setGameRuleValue("doDaylightCycle", "false");
                }
            }
            return true;
        }
        return false;
    }
}
