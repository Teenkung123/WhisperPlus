package com.teenkung.whisperplus.Storage;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ReplyStorage {

    private HashMap<Player, Player> replyPlayer = new HashMap<>();

    public void setReplyPlayer(Player player, Player player2) {
        if (replyPlayer.containsKey(player)) {
            replyPlayer.replace(player, player2);
        } else {
            replyPlayer.put(player, player2);
        }
    }

    public Player getReplyPlayer(Player player) {
        return replyPlayer.getOrDefault(player, null);
    }

    public void removeReplyPlayer(Player player) {
        replyPlayer.remove(player);
    }

}
