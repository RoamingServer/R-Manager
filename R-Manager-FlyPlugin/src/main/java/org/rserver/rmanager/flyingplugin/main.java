package org.rserver.rmanager.flyingplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin {

    @Override
    public void onEnable() {
        // 注册命令
        getCommand("fly").setExecutor(new FlyCommandExecutor());
        getCommand("fly").setTabCompleter(new FlyTabCompleter());
        getLogger().info("[R-Manager-FlyPlugin]已启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("[R-Manager-FlyPlugin]已禁用！");
    }

    public class FlyCommandExecutor implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            // 检查是否是玩家执行命令
            if (!(sender instanceof Player)) {
                sender.sendMessage("控制台能飞是吧~");
                return true;
            }

            Player player = (Player) sender;

            // 检查命令参数
            if (args.length == 0) {
                player.sendMessage("使用: /fly <enable|disable>");
                return true;
            }

            String subCommand = args[0].toLowerCase();

            switch (subCommand) {
                case "enable":
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage("§f§l[R-Manager-飞行控制] §f飞行模式§a已开启");
                    break;
                case "disable":
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage("§f§l[R-Manager-飞行控制] §f飞行模式§a已关闭");
                    break;
                default:
                    player.sendMessage("§4§l子命令错误. 正确使用方法为: /fly <enable|disable>");
                    break;
            }

            return true;
        }
    }

    public class FlyTabCompleter implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            if (!(sender instanceof Player)) {
                return new ArrayList<>();
            }

            List<String> completions = new ArrayList<>();
            if (args.length == 1) {
                completions.add("enable");
                completions.add("disable");
            }

            return completions;
        }
    }
}