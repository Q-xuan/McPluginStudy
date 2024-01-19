package top.xuan.pbasics;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.xuan.command.CommandHere;
import top.xuan.command.CommandTp;
import top.xuan.event.PlayerListener;

import java.util.Objects;
import java.util.logging.Logger;

public final class PBasics extends JavaPlugin {

    private static final Logger BUKKIT_LOGGER = Logger.getLogger("PBasics");

    @Override
    public void onEnable() {
        // Plugin startup logic
        BUKKIT_LOGGER.info("PBasics is enabled!");
        new PlayerListener(this);
        Objects.requireNonNull(Bukkit.getPluginCommand("tpa")).setExecutor(new CommandTp(this));
        Objects.requireNonNull(Bukkit.getPluginCommand("here")).setExecutor(new CommandHere(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
