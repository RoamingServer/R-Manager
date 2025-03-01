package org.rserver.rmanager.esc.etbasbad;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("[R-Manager-极限生存] 插件已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("[R-Manager-极限生存] 插件已禁用");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // 获取玩家
        var player = event.getPlayer();

        // 清空玩家背包
        player.getInventory().clear();

        // 清空玩家末影箱
        var enderChest = player.getEnderChest();
        enderChest.clear();

        // 可选：发送消息给玩家
        player.sendMessage("\u00a7f\u00a7l[R-Manager-极限生存] \u00a74很遗憾，因为死亡，您的背包和末影箱已被清空。");
    }
}