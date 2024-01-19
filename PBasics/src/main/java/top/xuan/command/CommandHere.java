package top.xuan.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.xuan.data.PlayerData;
import top.xuan.pbasics.PBasics;

/**
 * Created on 2024/1/18.
 *
 * @author py
 */
public class CommandHere implements CommandExecutor {

    private final PBasics pbs;

    public CommandHere(PBasics pbs) {
        this.pbs = pbs;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            Server server = pbs.getServer();
            TextComponent txt = Component.text("i am here!!!")
                    .append(Component.text(player.getName()))
                    .append(Component.text("at"))
                    .append(Component.text("x:"+player.getLocation().getX() + " y:" + player.getLocation().getY() + " z:" + player.getLocation().getZ(), Style.style(NamedTextColor.AQUA)))
                    .append(Component.text("."));
            server.broadcast(txt);
            PlayerData.hereRequests.put(player.getUniqueId(), player.getLocation());
            return true;
        }
        return false;
    }
}
