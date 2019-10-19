package me.tsblock.proxyplugin.Discord.Event;

import me.tsblock.proxyplugin.ProxyPlugin;
import me.tsblock.proxyplugin.Utils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import javax.annotation.Nonnull;

public class GuildMessageReceived extends ListenerAdapter {
    private final Configuration config;
    private ProxyServer proxyServer;

    public GuildMessageReceived(Configuration config, ProxyServer proxyServer) {
        this.config = config;
        this.proxyServer = proxyServer;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getChannel().getId().equals(config.getString("discord.channelID"))) {
            if (!event.getAuthor().getId().equals(ProxyPlugin.bot.getJda().getSelfUser().getId())) {
                String name = event.getMember().getEffectiveName();
                String discordMessage = event.getMessage().getContentStripped();
                String minecraftMessage = config.getString("discord.messages.MinecraftChat").replace("{nickname}", name).replace("{message}", discordMessage);
                for (ProxiedPlayer player : proxyServer.getPlayers()) {
                    player.sendMessage(Utils.parseLegacyText(minecraftMessage));
                }
                proxyServer.getConsole().sendMessage(Utils.parseLegacyText(minecraftMessage));
            }
        }
    }
}
