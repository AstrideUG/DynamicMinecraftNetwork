package me.devsnox.dynamicminecraftnetwork.api;

import com.boydti.fawe.object.schematic.Schematic;

import java.util.UUID;
import java.util.function.Consumer;

public interface DynamicNetworkAPI {

    void hasSchematic(UUID uuid, Consumer<Boolean> result);
    void getSchematic(UUID uuid, Consumer<Schematic> request);
    void saveSchematic(UUID uuid, Schematic schematic);
}
