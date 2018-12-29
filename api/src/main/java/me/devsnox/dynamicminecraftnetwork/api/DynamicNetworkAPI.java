package me.devsnox.dynamicminecraftnetwork.api;

import me.devsnox.dynamicminecraftnetwork.commons.worldedit.Schematic;

import java.util.UUID;
import java.util.function.Consumer;

public interface DynamicNetworkAPI {

    void hasSchematic(UUID uuid, Consumer<Boolean> result);
    void getSchematic(UUID uuid, Consumer<Schematic> request);
    void saveSchematic(UUID uuid, Schematic schematic);
}
