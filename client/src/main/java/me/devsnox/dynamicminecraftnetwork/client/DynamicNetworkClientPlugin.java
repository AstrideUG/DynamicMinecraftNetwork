package me.devsnox.dynamicminecraftnetwork.client;

import org.bukkit.plugin.java.JavaPlugin;

public class DynamicNetworkClientPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new DynamicClient();
    }
}
