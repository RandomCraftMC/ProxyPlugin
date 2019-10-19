package me.tsblock.proxyplugin.Discord;

import lombok.Getter;
import me.tsblock.proxyplugin.Discord.Event.GuildMessageReceived;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;

import javax.security.auth.login.LoginException;

public class Bot {
    @Getter
    public JDA jda;
    private Configuration config;
    private ProxyServer proxyServer;

    public Bot(Configuration config, ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
        this.config = config;
        try {
            jda = new JDABuilder()
                    .setToken(config.getString("discord.token"))
                    .setActivity(Activity.of(Activity.ActivityType.DEFAULT, "RandomCraft"))
                    .addEventListeners(new GuildMessageReceived(config, proxyServer))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
