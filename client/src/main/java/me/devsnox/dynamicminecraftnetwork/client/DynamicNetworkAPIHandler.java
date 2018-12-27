package me.devsnox.dynamicminecraftnetwork.client;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.client.SkyllaClient;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkAPI;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.commons.packets.RequestSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.util.UUID;
import java.util.function.Consumer;

public class DynamicNetworkAPIHandler implements DynamicNetworkAPI {

    private ClientSchematicHandler schematicHandler;
    private SkyllaClient skyllaClient;

    public DynamicNetworkAPIHandler(ClientSchematicHandler schematicHandler, SkyllaClient skyllaClient) {
        this.schematicHandler = schematicHandler;
        this.skyllaClient = skyllaClient;
    }

    @Override
    public void getSchematic(UUID uuid, Consumer<Schematic> request) {
        this.schematicHandler.addToQuery(uuid, request);
        this.skyllaClient.sendPacket(new RequestSchematicPacket(uuid));
    }

    @Override
    public void saveSchematic(UUID uuid, Schematic schematic) {
        this.skyllaClient.sendPacket(new SchematicPacket(uuid, schematic));
    }
}
