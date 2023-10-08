package org.example.elwarriorcito.zoofee.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatUtils {
    public static String setColorName(String name){
        return ChatColor.translateAlternateColorCodes('&', name);
    }

    public static String getStringProgressBar(int Max, int Current){

        String builder = ChatUtils.setColorName("&7I").repeat(Max);

        String subtstring = ChatUtils.setColorName("&aI").repeat(Current);

        return subtstring + builder.substring(Current * 3);

    }
}
