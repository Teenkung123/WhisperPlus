package com.teenkung.whisperplus.Command;

import com.teenkung.whisperplus.WhisperPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhisperCommand implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player receiver = Bukkit.getPlayer(args[0]);
                if (receiver == null) { return false; }
                StringBuilder message = new StringBuilder();
                for(int i = 1 ; i < args.length ; i++) {
                    message.append(args[i]);
                    message.append(" ");
                }
                if (sender instanceof Player player) {
                    if (player.canSee(receiver)) {
                        WhisperPlus.getInstance().whisperTo((Player) sender, Bukkit.getPlayer(args[0]), message.toString(), true);
                    } else {
                        sender.sendMessage(WhisperPlus.getConfigLoaderInstance().getPlayerNotFound());
                    }
                } else {
                    WhisperPlus.getInstance().whisperTo(null, Bukkit.getPlayer(args[0]), message.toString(), true);
                }
            } else {
                sender.sendMessage(WhisperPlus.getConfigLoaderInstance().getPlayerNotFound());
            }
        }
        return false;
    }
}
