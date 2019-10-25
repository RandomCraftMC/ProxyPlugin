package me.tsblock.proxyplugin.Events;

import net.md_5.bungee.api.ChatColor;
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
        /* random motds */
        List<String> motdList = config.getStringList("motd");
        Random random = new Random();
        String motd = motdList.get(random.nextInt(motdList.size())).replace("&", "§");
        ServerPing serverPing = event.getResponse();
        BaseComponent[] components = TextComponent.fromLegacyText(motd);
        TextComponent motdComponent = new TextComponent();
        for (BaseComponent component : components) {
            motdComponent.addExtra(component);
        }
        serverPing.setDescriptionComponent(motdComponent);
        // todo: hover text on player count
        event.setResponse(serverPing);
    }
}
