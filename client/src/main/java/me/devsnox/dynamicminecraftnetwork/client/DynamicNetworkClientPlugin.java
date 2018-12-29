package me.devsnox.dynamicminecraftnetwork.client;

import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicNetworkClientPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new DynamicClient();

        /*this.getDataFolder().mkdirs();

        File file = new File(this.getDataFolder(), "default.schematic");

        if(file.exists()) {
            try {
                DynamicNetworkFactory.dynamicNetworkAPI.saveSchematic(UUID.randomUUID(), ClipboardFormat.SCHEMATIC.load(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
