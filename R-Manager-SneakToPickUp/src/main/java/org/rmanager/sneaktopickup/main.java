package org.rmanager.sneaktopickup;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("[R-Manager-拾取管理] 拾取管理已开启!");
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("[R-Manager-拾取管理] 拾取管理已禁用!");
    }
}