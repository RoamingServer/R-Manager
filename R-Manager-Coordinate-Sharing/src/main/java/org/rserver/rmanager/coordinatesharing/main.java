package org.rserver.rmanager.coordinatesharing;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class main extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        getLogger().info("R-Manager-位置共享 已启用!");
        // 注册命令
        getCommand("cs").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("R-Manager-位置共享 已禁用!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // 获取玩家坐标
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            // 构建消息
            String message = String.format("\u00A7l[R-Manager-位置共享] \u00A7f%s 的坐标是: (\u00A7l\u00A73 X: %.2f, Y: %.2f, Z: %.2f \u00A7f)", player.getName(), x, y, z);
            // 发送给所有在线玩家
            for (Player onlinePlayer : getServer().getOnlinePlayers()) {
                onlinePlayer.sendMessage(message);
            }
            return true;
        } else {
            sender.sendMessage("此命令只能由玩家执行！");
            return false;
        }
    }
}