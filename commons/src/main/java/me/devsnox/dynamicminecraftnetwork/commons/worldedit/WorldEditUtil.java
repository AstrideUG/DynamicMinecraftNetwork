package me.devsnox.dynamicminecraftnetwork.commons.worldedit;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.world.World;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorldEditUtil {
    private static @Getter WorldEditPlugin worldEditPlugin;
    private static @Getter WorldEdit worldEdit;

    private static @Getter(value = AccessLevel.PROTECTED) Map<org.bukkit.World, World> worldCache = new HashMap<>();

    protected static boolean initWorldEdit() {
        if(worldEditPlugin == null) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");

            if(plugin != null) {
                worldEditPlugin = (WorldEditPlugin) plugin;
                worldEdit = worldEditPlugin.getWorldEdit();
            } else {
                Logger.getLogger("KageCore").info("WorldEdit is not installed!");

                return false;
            }
        }

        return true;
    }

    public static World getWEWorld(org.bukkit.World world) {
        if(!initWorldEdit()) {
            return null;
        }

        if(worldCache.containsKey(world)) {
            return worldCache.get(world);
        }

        World weWorld = new BukkitWorld(world);
        worldCache.put(world, weWorld);

        return weWorld;
    }

    public static EditSession createSession(World world) {
        return worldEdit.getEditSessionFactory().getEditSession(world, Integer.MAX_VALUE);
    }
}
