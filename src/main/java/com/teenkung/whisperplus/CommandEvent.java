package com.teenkung.whisperplus;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static com.teenkung.whisperplus.WhisperPlus.colorize;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().contains("minecraft:") && !event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(colorize("&cCommand " + event.getMessage() + " is not allowed!"));
            event.setCancelled(true);
        }
    }

}
