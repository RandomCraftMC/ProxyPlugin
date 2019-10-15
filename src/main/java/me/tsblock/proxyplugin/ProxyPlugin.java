package me.tsblock.proxyplugin;

import me.tsblock.proxyplugin.Events.ProxyPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public final class ProxyPlugin extends Plugin {
    private static Configuration config;
    private boolean configWrote;
    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            setupConfig();
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            if (configWrote) {
                config.set("motd", Arrays.asList("MOTD #1", "MOTD #2"));
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        getProxy().getPluginManager().registerListener(this, new ProxyPing(config));
        getProxy().getConsole().sendMessage(new TextComponent("Done! (for pterodactyl panel)"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupConfig() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
                writer.append("");
                writer.close();
                configWrote = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
