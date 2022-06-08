package com.teenkung.whisperplus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.teenkung.whisperplus.WhisperPlus.colorize;

public class ConnectionHandler implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("WhisperPlus.SocialSpy")) {
            WhisperPlus.getSocialSpyStorageInstance().setSocialSpy(event.getPlayer(), WhisperPlus.getConfigLoaderInstance().getDefaultSocialOn());
            event.getPlayer().sendMessage(colorize(WhisperPlus.getConfigLoaderInstance().getSocialSpyOn()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        WhisperPlus.getReplyStorageInstance().removeReplyPlayer(event.getPlayer());
        if (WhisperPlus.getWhisperStorageInstance().getMessageHistory(event.getPlayer()) != null) {
            WhisperPlus.getWhisperStorageInstance().clearMessage(event.getPlayer());
        }
    }
}
