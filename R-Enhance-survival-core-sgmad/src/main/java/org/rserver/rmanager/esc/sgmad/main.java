package org.rserver.rmanager.esc.sgmad;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("[R-Manger-极限生存] &#ff0000插&#f80702件&#f00f05已&#e91607启&#e21d0a用&#db240c。&#d32c0f &#cc3311本&#c53a13插&#bd4216件&#b64918添&#af501b加&#a8571d &#a05f201&#996622 &#926d24监&#8a7527听&#837c29器&#7c832c &#758a2e、&#6d9231 &#6699331&#5fa035 &#57a838命&#50af3a令&#49b63d处&#42bd3f理&#3ac542单&#33cc44元&#2cd346 &#24db49、&#1de24b &#16e94e1&#0ff050 &#07f853功&#00ff55能。");
    }

    @Override
    public void onDisable() {
        getLogger().info("[R-Manger-极限生存] &#ff0000插&#eb0014件&#d80027已&#c4003b禁&#b1004e用&#9d0062。&#890076 &#760089欢&#62009d迎&#4e00b1下&#3b00c4次&#2700d8使&#1400eb用&#0000ff!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // 获取死亡的玩家
        String playerName = event.getEntity().getName();
        // 执行 /litebans:ban 玩家 7d 命令
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi:tempban " + playerName + " 7d " + "您在极限生存中死亡，因此被封禁!");
    }
}