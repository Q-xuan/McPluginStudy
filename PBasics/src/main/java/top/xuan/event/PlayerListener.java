package top.xuan.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.xuan.pbasics.PBasics;

/**
 * Created on 2024/1/15.
 *
 * @author py
 */
public class PlayerListener implements Listener {

    private final PBasics pbs;

    public PlayerListener(PBasics pbs) {
        this.pbs = pbs;
        this.pbs.getServer().getPluginManager().registerEvents(this, pbs);
    }



    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        TextComponent text = Component
                .text("[+]", Style.style(NamedTextColor.GREEN))
                .append(Component.text(event.getPlayer().getName(),Style.style(NamedTextColor.WHITE)));
        pbs.getServer().broadcast(text);
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        TextComponent text = Component
                .text("[-]", Style.style(NamedTextColor.GRAY))
                .append(Component.text(event.getPlayer().getName(),Style.style(NamedTextColor.DARK_GRAY)));
        pbs.getServer().broadcast(text);
    }
}
