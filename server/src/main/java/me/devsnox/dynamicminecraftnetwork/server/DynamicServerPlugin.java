package me.devsnox.dynamicminecraftnetwork.server;

import org.bukkit.plugin.java.JavaPlugin;

public class DynamicServerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new DynamicServer();
    }
}
