package top.xuan.event;

import net.kyori.adventure.text.Component;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import top.xuan.data.LoginData;

/**
 * Created on 2024/1/12.
 *
 * @author py
 */
public class EventListenter implements Listener {



    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        LoginData.addPlayer(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerEvent(PlayerEvent event) {
        if(event instanceof PlayerLoginEvent e) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text("你已经登录了"));
        }
        if(
                event instanceof PlayerMoveEvent ||
                        event instanceof PlayerInteractEvent
        ) {
            ((Cancellable) event).setCancelled(false);
        }
    }

    @EventHandler
    public void restrictOpenInventory(InventoryOpenEvent e) {
        // 打开物品栏
        cancelIfNotLoggedIn(e);
    }

    public static void cancelIfNotLoggedIn(Cancellable event) {
        // 这里写着 Cancellable，和上面的 List 是一个原理，说到底我们只需要「可以取消」这个功能就可以了，至于到底是哪个类，不重要


        if (event instanceof PlayerEvent e) {
            // instanceof 关键字指示 Java 重新判断左边对象的类型是不是右边的类或者右边类的子类，也就是判断能否进行强制类型转换
            if (LoginData.isOnline(e.getPlayer().getName())) {
                // if 语句用于看看玩家是不是在限制列表中
                // (PlayerEvent) e 进行类型转换
                event.setCancelled(true);
            }
        } else if (event instanceof InventoryOpenEvent e) {
            // else if 表示「上一条 if 的条件为假」并且「当前括号中的条件为真」时才执行大括号里面的内容，相当于「如果不是那样，而是这样，就做……」

            // 限制玩家打开物品栏，需要 InventoryOpenEvent
            if (LoginData.isOnline(e.getPlayer().getName())) {

                e.setCancelled(true);
            }
        }
    }



    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        LoginData.removePlayer(event.getPlayer().getName());
    }


}
