package com.teenkung.whisperplus.Command;

import com.teenkung.whisperplus.WhisperPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.teenkung.whisperplus.WhisperPlus.colorize;

public class SocialSpyCommand implements CommandExecutor {
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player && sender.hasPermission("WhisperPlus.SocialSpy")) {
            if (args.length == 0) {
                WhisperPlus.getSocialSpyStorageInstance().setSocialSpy(player, !WhisperPlus.getSocialSpyStorageInstance().getSocialSpyEnabled(player));
                if (WhisperPlus.SocialSpyStorageInstance.getSocialSpyEnabled(player)) {
                    player.sendMessage(colorize(WhisperPlus.getConfigLoaderInstance().getSocialSpyOn()));
                } else {
                    player.sendMessage(colorize(WhisperPlus.getConfigLoaderInstance().getSocialSpyOff()));
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(colorize(WhisperPlus.getConfigLoaderInstance().getPrefixMessage() + "&aReloading All Config"));
                WhisperPlus.getConfigLoaderInstance().loadAllConfig();
            }
        }
        return false;
    }
}
