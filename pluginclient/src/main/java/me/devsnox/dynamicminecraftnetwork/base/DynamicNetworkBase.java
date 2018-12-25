package me.devsnox.dynamicminecraftnetwork.base;

import me.devsnox.dynamicminecraftnetwork.client.DynamicClient;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicNetworkBase extends JavaPlugin {

    private DynamicClient dynamicClient;

    public DynamicNetworkBase() {
        this.dynamicClient = new DynamicClient();
    }
}