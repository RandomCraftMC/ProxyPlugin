package me.tsblock.proxyplugin.Events;

import me.tsblock.proxyplugin.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnect implements Listener {
    private ProxyServer proxyServer;

    public ServerConnect(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @EventHandler
    public void onServerConnect(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        for (ProxiedPlayer player1 : proxyServer.getPlayers()) {
            player1.sendMessage(Utils.parseLegacyText("&7[&a+&7] &f" + player.getName())); //im lazy lol
        }
    }
}
