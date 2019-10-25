package me.tsblock.proxyplugin;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class Utils {
    public static TextComponent parseLegacyText(String text) {
        BaseComponent[] components = TextComponent.fromLegacyText(text.replace("&", "§"));
        TextComponent component = new TextComponent();
        for (BaseComponent _component : components) {
            component.addExtra(_component);
        }
        return component;
    }
}
