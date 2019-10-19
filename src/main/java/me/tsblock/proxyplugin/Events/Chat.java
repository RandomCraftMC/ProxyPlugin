package me.tsblock.proxyplugin.Events;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.tsblock.proxyplugin.ProxyPlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class Chat implements Listener {
    private Configuration config;
    private LuckPermsApi luckPermsApi;
    private ProxyServer server;

    public Chat(Configuration config, ProxyServer server) {
        this.config = config;
        luckPermsApi = LuckPerms.getApi();
        this.server = server;
    }

    @EventHandler
    public void onChatEvent(ChatEvent chatEvent) {
        if (chatEvent.getSender() instanceof ProxiedPlayer && !chatEvent.isCommand()) {
            ProxiedPlayer player = (ProxiedPlayer) chatEvent.getSender();
            User luckPermsUser = luckPermsApi.getUser(player.getUniqueId());
            String groupName = luckPermsApi.getGroup(luckPermsUser.getPrimaryGroup()).getDisplayName();
            String message = config.getString("discord.messages.DiscordChat");
            if (groupName == null) {
                message = message.replace("{group} ", "");
            } else {
                message = message.replace("{group}", "**" + groupName + "**");
            }
            message = message.replace("{name}", player.getName()).replace("{message}", chatEvent.getMessage());
            ProxyPlugin.bot.getJda().getTextChannelById(config.getString("discord.channelID")).sendMessage(message).queue();
        }
    }
}
