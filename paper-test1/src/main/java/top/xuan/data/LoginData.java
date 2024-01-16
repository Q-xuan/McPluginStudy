package top.xuan.data;

import java.util.*;

/**
 * Created on 2024/1/12.
 *
 * @author py
 */
public final class LoginData {

    private static final List<String> ONLINE_PLAYERS = new LinkedList<>();

    public static void addPlayer(String name) {
        if (!ONLINE_PLAYERS.contains(name.toLowerCase())) {
            ONLINE_PLAYERS.add(name.toLowerCase());
        }
    }

    public static void removePlayer(String name) {
        ONLINE_PLAYERS.remove(name.toLowerCase());
    }

    public static boolean isOnline(String name) {
        return ONLINE_PLAYERS.contains(name.toLowerCase());
    }

}
