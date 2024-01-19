package top.xuan.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.xuan.pbasics.PBasics;

/**
 * Created on 2024/1/16.
 *
 * @author py
 */
public class CommandTp implements CommandExecutor {

    private final PBasics pbs;

    public CommandTp(PBasics pbs) {
        this.pbs = pbs;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length==0) {
            return false;
        }
        if (sender instanceof Player player) {
            if (args.length==1) {
                Player target = pbs.getServer().getPlayer(args[0]);
                if (target!=null) {
                    player.teleport(target);
                    return true;
                }
            }
            if (args.length==3) {
                try {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    player.teleport(player.getWorld().getBlockAt((int) x, (int) y, (int) z).getLocation());
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }


}
