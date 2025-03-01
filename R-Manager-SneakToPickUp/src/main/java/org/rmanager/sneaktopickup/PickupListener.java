package org.rmanager.sneaktopickup;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupListener implements Listener {
    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        // 检查拾取物品的实体是否为玩家
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // 检查玩家是否处于潜行状态
            if (!player.isSneaking()) {
                // 如果玩家未潜行，取消拾取事件
                event.setCancelled(true);
            }else{
                event.setCancelled(false);
            }
        }
    }
}