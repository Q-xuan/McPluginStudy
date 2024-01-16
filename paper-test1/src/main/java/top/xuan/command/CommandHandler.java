package top.xuan.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.xuan.config.ConfigReader;
import top.xuan.data.LoginData;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

/**
 * Created on 2024/1/12.
 *
 * @author py
 */
public class CommandHandler implements CommandExecutor {


    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            // 如果 sender 是 Player 的实例，那么这条命令是玩家发送的，反之则不是
            return false;
            // 服主也有可能从服务器控制台使用命令，先确认命令来自于玩家
        }
        if (!LoginData.isOnline(sender.getName())) {
            sender.sendMessage(Component.text("你已经登录了！", Style.style(NamedTextColor.DARK_RED)));
            return true;
            // 已经登录了，就没必要验证了
        }
        if (args.length == 0) {
            sender.sendMessage(Component.text("必须输入密码！", Style.style(NamedTextColor.DARK_RED)));
            // sendMessage 用于向该终端（玩家或控制台）发送消息
            return false;
            // 参数不完整，拒绝处理
        }
        String pwdConcat = String.join("<space>", args);
        // 玩家的密码可能含有空格，join 将它们粘在一起，<space> 只是定义的分隔符，换成别的也行
        if (ConfigReader.isPlayerRegistered(sender.getName())) {
            // 已经注册
            if (ConfigReader.verifyPassword(sender.getName(), pwdConcat)) {
                // 验证密码
                LoginData.removePlayer(sender.getName());
                // 解锁玩家
                sender.sendMessage(Component.text("登录成功，欢迎回来！", Style.style(NamedTextColor.GREEN)));
            } else {
                sender.sendMessage(Component.text("密码错误！", Style.style(NamedTextColor.RED)));
            }
            return true;
            // true 代表语法正确，虽然密码错误，但语法正确即可返回 true

        } else {
            // 玩家没注册，准备注册
            ConfigReader.addPlayer(sender.getName(), pwdConcat);
            // 注册玩家
            LoginData.removePlayer(sender.getName());
            // 解锁玩家
            sender.sendMessage(ChatColor.GREEN + "注册成功！");
            return true;
        }
    }
}
