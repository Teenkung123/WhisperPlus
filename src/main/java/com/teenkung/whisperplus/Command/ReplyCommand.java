package com.teenkung.whisperplus.Command;

import com.teenkung.whisperplus.WhisperPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.teenkung.whisperplus.WhisperPlus.colorize;

public class ReplyCommand implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (WhisperPlus.getReplyStorageInstance().getReplyPlayer(player) != null) {
                if (args.length == 0) {
                    return false;
                }
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(arg);
                    message.append(" ");
                }
                WhisperPlus.getInstance().whisperTo(player, WhisperPlus.getReplyStorageInstance().getReplyPlayer(player), message.toString(), false);
            } else {
                player.sendMessage(colorize(WhisperPlus.getConfigLoaderInstance().getPlayerNotFound()));
            }
        }
        return false;
    }
}
