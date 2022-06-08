package com.teenkung.whisperplus.Storage;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class WhisperStorage {

    private HashMap<Player, ArrayList<String>> msgHistory = new HashMap<>();

    public void addMessage(Player player, String message) {
        if (msgHistory.containsKey(player)) {
            if (!msgHistory.get(player).contains(message)) {
                msgHistory.get(player).add(message);
            }
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(message);
            msgHistory.put(player, temp);
        }
    }
    public void removeMessage(Player player, String message) {
        msgHistory.get(player).remove(message);
    }
    public void clearMessage(Player player) {
        if (msgHistory.containsKey(player)) {
            msgHistory.get(player).clear();
        }
    }
    public void clearAllMessage() {
        msgHistory.clear();
    }
    public ArrayList<String> getMessageHistory(Player player) {
        return msgHistory.getOrDefault(player, new ArrayList<>());
    }

}
