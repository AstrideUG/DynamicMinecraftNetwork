package me.devsnox.dynamicminecraftnetwork.commons.worldedit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import lombok.Getter;
import lombok.ToString;

@ToString
public class BoundingBox implements ConfigurationSerializable {
    private @Getter Location min;
    private @Getter Location max;

    public BoundingBox(Map<String, Object> map) {
        this((Location) map.get("min"), (Location) map.get("max"));
    }

    public BoundingBox(CuboidSelection selection) {
        this(selection.getMinimumPoint(), selection.getMaximumPoint());
    }

    public BoundingBox(Location min, Location max) {
        if(min == null || max == null) {
            throw new IllegalArgumentException("The min/max locations can't be null");
        }

        if(min.getWorld() != max.getWorld()) {
            throw new IllegalArgumentException("Both provided locations need to be in the same world");
        }

        this.min = new Location(min.getWorld(), Math.min(min.getX(), max.getX()), Math.min(min.getY(), max.getY()), Math.min(min.getZ(), max.getZ()));
        this.max = new Location(min.getWorld(), Math.max(min.getX(), max.getX()), Math.max(min.getY(), max.getY()), Math.max(min.getZ(), max.getZ()));
    }

    public boolean contains(Location loc) {
        return (min.getWorld() == null || loc.getWorld() == min.getWorld()) && min.getX() <= loc.getX() && max.getX() >= loc.getX() - 1 && min.getY() <= loc.getY() && max.getY() >= loc.getY() - 1 && min.getZ() <= loc.getZ() && max.getZ() >= loc.getZ() - 1;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("min", min);
        map.put("max", max);

        return map;
    }
}
