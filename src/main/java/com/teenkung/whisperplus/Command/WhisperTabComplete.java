package com.teenkung.whisperplus.Command;

import com.teenkung.whisperplus.WhisperPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WhisperTabComplete implements TabCompleter {
    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().contains(args[0])) {
                    if (((Player) sender).canSee(player)) {
                        result.add(player.getName());
                    }
                }
            }
        } else if (args.length > 1) {
            Player receiver = Bukkit.getPlayer(args[0]);
            if (receiver == null) {
                result.add("Player Not Found");
            } else if (!((Player) sender).canSee(receiver)) {
                result.add("Player Not Found");
            } else {
                if (WhisperPlus.getConfigLoaderInstance().getEnabledHistory()) {
                    for (String r : WhisperPlus.getWhisperStorageInstance().getMessageHistory((Player) sender)) {
                        if (r.contains(args[1])) {
                            result.add(r);
                        }
                    }
                }
            }
        }
        return result;
    }
}
