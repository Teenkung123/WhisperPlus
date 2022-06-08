package com.teenkung.whisperplus;

import com.teenkung.whisperplus.Command.*;
import com.teenkung.whisperplus.Storage.ReplyStorage;
import com.teenkung.whisperplus.Storage.SocialSpyStorage;
import com.teenkung.whisperplus.Storage.WhisperStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WhisperPlus extends JavaPlugin {

    public static WhisperPlus Instance;
    public static ConfigLoader ConfigLoaderInstance;
    public static WhisperStorage WhisperStorageInstance;
    public static ReplyStorage ReplyStorageInstance;
    public static SocialSpyStorage SocialSpyStorageInstance;

    @Override
    public void onEnable() {
        Instance = this;
        ConfigLoaderInstance = new ConfigLoader();
        WhisperStorageInstance = new WhisperStorage();
        ReplyStorageInstance = new ReplyStorage();
        SocialSpyStorageInstance = new SocialSpyStorage();

        Objects.requireNonNull(getCommand("whisper")).setExecutor(new WhisperCommand());
        Objects.requireNonNull(getCommand("whisper")).setTabCompleter(new WhisperTabComplete());
        Objects.requireNonNull(getCommand("reply")).setExecutor(new ReplyCommand());
        Objects.requireNonNull(getCommand("reply")).setTabCompleter(new ReplyTabComplete());
        Objects.requireNonNull(getCommand("socialspy")).setExecutor(new SocialSpyCommand());

        Bukkit.getPluginManager().registerEvents(new ConnectionHandler(), this);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(), this);

        ConfigLoaderInstance.loadAllConfig();
    }

    @Override
    public void onDisable() {

    }

    public static WhisperPlus getInstance() { return Instance; }
    public static ConfigLoader getConfigLoaderInstance() { return ConfigLoaderInstance; }
    public static WhisperStorage getWhisperStorageInstance() { return WhisperStorageInstance; }
    public static SocialSpyStorage getSocialSpyStorageInstance() { return SocialSpyStorageInstance; }
    public static ReplyStorage getReplyStorageInstance() { return ReplyStorageInstance; }

    public void sendSocialSpyMessage(String message, Player sender, Player receiver) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != sender && player != receiver) {
                if (player.hasPermission("WhisperPlus.SocialSpy")) {
                    if (SocialSpyStorageInstance.getSocialSpyEnabled(player)) {
                        player.sendMessage(ConfigLoaderInstance.getSocialSpy().replaceAll("<message>", colorize(message)).replaceAll("<sender>", sender.getName()).replaceAll("<receiver>", receiver.getName()));
                    }
                }
            }
        }
    }

    public void whisperTo(@Nullable Player sender, Player receiver, String message, @Nullable Boolean setReply) {
        if (receiver == null) { return; }
        if (message == null) { message = ""; }
        if (sender == null) {
            receiver.sendMessage(getConfigLoaderInstance().getReceiveMessage().replaceAll("<sender>", getConfigLoaderInstance().getConsoleUser()).replace("<receiver>", receiver.getName()).replace("<message>", colorize(message)));
        } else {
            if (ConfigLoaderInstance.getSameMessageBlocker() != null && ConfigLoaderInstance.getSameMessageBlocker()) {
                if (WhisperStorageInstance.getMessageHistory(sender).size() != 0 && WhisperStorageInstance.getMessageHistory(sender).get(WhisperStorageInstance.getMessageHistory(sender).size() - 1).equalsIgnoreCase(message)) {
                    sender.sendMessage(ConfigLoaderInstance.getSameMessageBlockMessage());
                    return;
                } else {
                    WhisperStorageInstance.addMessage(sender, message);
                }
            }
            receiver.sendMessage(getConfigLoaderInstance().getReceiveMessage().replace("<sender>", sender.getName()).replace("<receiver>", receiver.getName()).replace("<message>", colorize(message)));
            sender.sendMessage(getConfigLoaderInstance().getSendMessage().replace("<sender>", sender.getName()).replace("<receiver>", receiver.getName()).replace("<message>", colorize(message)));
            sendSocialSpyMessage(message, sender, receiver);
            if (setReply == null || setReply) {
                ReplyStorageInstance.setReplyPlayer(receiver, sender);
            }
        }
    }

    public static String colorize(String message) {
        if (message == null) {
            message = "";
        }
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
