package me.tsblock.proxyplugin.Events;

import me.tsblock.proxyplugin.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.Random;

public class ProxyPing implements Listener {
    private Configuration config;
    public ProxyPing(Configuration config) {
        this.config = config;
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        List<String> motdList = config.getStringList("motd");
        Random random = new Random();
        String motd = motdList.get(random.nextInt(motdList.size())).replace("&", "§");
        ServerPing serverPing = event.getResponse();
        BaseComponent motdComponent = Utils.parseLegacyText(motd);
        serverPing.setDescriptionComponent(motdComponent);
        event.setResponse(serverPing);
    }
}
