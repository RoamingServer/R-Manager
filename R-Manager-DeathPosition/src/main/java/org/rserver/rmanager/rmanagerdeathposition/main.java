//定义包org.rserver.rmanager.rmanagerdeathposition
package org.rserver.rmanager.rmanagerdeathposition;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("死亡信息公布插件已启用(International Edition)");
    }

    @Override
    public void onDisable() {
        getLogger().info("死亡信息公布插件已禁用(International Edition)");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // 获取死亡玩家
        Player player = event.getEntity();
        // 获取死亡玩家的名称
        String playerName = player.getName();
        // 获取死亡玩家的坐标
        Location location = player.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        // 构建tellraw命令的JSON字符串
        ComponentBuilder componentBuilder = new ComponentBuilder(String.format("\u00A7f\u00A7l[R-Manager-DeathMessage] \u00a7l\u00A7a%s \u00a7fat coordinates \u00a7a(%.2f, %.2f, %.2f) \u00A74\u00A7ldead! \u00a7a（The smell of death and decay hung over the town.)", playerName, x, y, z));
        // 获取服务器中的所有玩家
        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            // 使用tellraw命令向每个玩家发送格式化的死亡信息
            onlinePlayer.spigot().sendMessage(componentBuilder.create());
        }
        // 同时向控制台发送死亡信息（控制台不支持tellraw格式化）
        getLogger().info(String.format("%s在坐标(%.2f, %.2f, %.2f)死亡了", playerName, x, y, z));
    }
}