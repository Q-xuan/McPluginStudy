package top.xuan.pbasics;

import org.bukkit.plugin.java.JavaPlugin;
import top.xuan.event.PlayerListener;

import java.util.logging.Logger;

public final class PBasics extends JavaPlugin {

    private static final Logger BUKKIT_LOGGER = Logger.getLogger("PBasics");

    @Override
    public void onEnable() {
        // Plugin startup logic
        BUKKIT_LOGGER.info("PBasics is enabled!");
        new PlayerListener(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
