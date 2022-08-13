package de.brownie.clanstats.utils;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
    public static void sendMessagePAF(OnlinePAFPlayer pSender, String message) {
        pSender.sendMessage(ChatColor.translateAlternateColorCodes('&' , message));
    }
}
