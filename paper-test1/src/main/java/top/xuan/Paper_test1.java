package top.xuan;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.xuan.command.CommandHandler;
import top.xuan.event.EventListenter;

import java.util.Objects;

public final class Paper_test1 extends JavaPlugin {

    public static JavaPlugin instance;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventListenter(), this);
        // 注册事件处理器，这里必须实例化，this 表明注册到本插件上
        Objects.requireNonNull(Bukkit.getPluginCommand("login")).setExecutor(new CommandHandler());
        // 注册事件处理器，也要实例化，requireNonNull 是不必要的，但是万一插件损坏了或者 Bukkit 出错了，我们还能知道是这里出问题
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
