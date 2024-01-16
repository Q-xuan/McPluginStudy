package top.xuan.config;

import org.bukkit.configuration.file.FileConfiguration;
import top.xuan.Paper_test1;

/**
 * Created on 2024/1/12.
 *
 * @author py
 */
public final class ConfigReader {

    public static FileConfiguration config = Paper_test1.instance.getConfig();
    // 由于三个方法都要使用，我们将这个变量抽取出来到最外层
    public static boolean isPlayerRegistered(String playerName) {

        return config.contains(playerName.toLowerCase());
    }

    public static boolean verifyPassword(String playerName, String password) {
        return password.equals(config.getString(playerName.toLowerCase()));
        // 三步合成一行：转换小写，读取字符串，返回是否相等
    }

    public static void addPlayer(String playerName, String password) {
        Paper_test1.instance.getConfig().set(playerName.toLowerCase(), password);
        Paper_test1.instance.saveConfig();
    }
}
