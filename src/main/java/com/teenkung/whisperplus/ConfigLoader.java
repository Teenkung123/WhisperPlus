package com.teenkung.whisperplus;

import static com.teenkung.whisperplus.WhisperPlus.colorize;

public class ConfigLoader {

    private String prefix;
    private String receive;
    private String send;
    private String socialSpy;
    private String socialSpyOn;
    private String socialSpyOff;
    private String consoleUser;
    private String playerNotFound;
    private String sameMessageBlockMessage;

    private Boolean defaultSocialOn;
    private Boolean enabledHistory;
    private Boolean sameMessageBlocker;

    public void loadAllConfig() {
        WhisperPlus.getInstance().reloadConfig();
        WhisperPlus.getInstance().getConfig().options().copyDefaults(true);
        WhisperPlus.getInstance().saveDefaultConfig();
        prefix = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Prefix", "&7[&bWhisperPlus&7]&r"));
        receive = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Whisper-Receive", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        send = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Whisper-Send", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        socialSpy = colorize(WhisperPlus.getInstance().getConfig().getString("Message.SocialSpy", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        socialSpyOn = colorize(WhisperPlus.getInstance().getConfig().getString("Message.SocialSpy-On", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        socialSpyOff = colorize(WhisperPlus.getInstance().getConfig().getString("Message.SocialSpy-Off", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        consoleUser = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Console-User", "<prefix> &cConfiguration Error"));
        playerNotFound = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Player-Not-Found", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);
        sameMessageBlockMessage = colorize(WhisperPlus.getInstance().getConfig().getString("Message.Same-Message-Block", "<prefix> &cConfiguration Error")).replace("<prefix>", prefix);

        defaultSocialOn = WhisperPlus.getInstance().getConfig().getBoolean("Settings.Default-SocialSpy-On", false);
        enabledHistory = WhisperPlus.getInstance().getConfig().getBoolean("Settings.Enabled-History", true);
        sameMessageBlocker = WhisperPlus.getInstance().getConfig().getBoolean("Settings.Same-Message-Blocker", true);
    }

    public String getPrefixMessage() { return prefix; }
    public String getReceiveMessage() { return receive; }
    public String getSendMessage() { return send; }
    public String getSocialSpy() { return socialSpy; }
    public String getSocialSpyOn() { return socialSpyOn; }
    public String getSocialSpyOff() { return socialSpyOff; }
    public String getConsoleUser() { return consoleUser; }
    public String getPlayerNotFound() { return playerNotFound; }
    public String getSameMessageBlockMessage() { return sameMessageBlockMessage; }
    public Boolean getDefaultSocialOn() { return defaultSocialOn; }
    public Boolean getEnabledHistory() { return enabledHistory; }
    public Boolean getSameMessageBlocker() { return sameMessageBlocker; }

}
