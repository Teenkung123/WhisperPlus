package com.teenkung.whisperplus.Storage;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class SocialSpyStorage {

    private HashMap<Player, Boolean> SocialSpy = new HashMap<>();

    public void setSocialSpy(Player player, Boolean enabled) {
        if (SocialSpy.containsKey(player)) {
            SocialSpy.replace(player, enabled);
        } else {
            SocialSpy.put(player, enabled);
        }
    }

    public Boolean getSocialSpyEnabled(Player player) {
        return SocialSpy.getOrDefault(player, false);
    }
}
