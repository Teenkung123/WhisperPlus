package com.teenkung.whisperplus.Command;

import com.teenkung.whisperplus.WhisperPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReplyTabComplete implements TabCompleter {
    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> result = new ArrayList<>();
        if (sender instanceof Player player) {
            if (args[1] == null) { return null; }
            if (WhisperPlus.getReplyStorageInstance().getReplyPlayer(player) == null) {
                result.add("No Reply Player Found");
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
