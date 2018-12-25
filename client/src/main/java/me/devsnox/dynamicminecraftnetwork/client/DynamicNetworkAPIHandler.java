package me.devsnox.dynamicminecraftnetwork.client;

import com.boydti.fawe.object.schematic.Schematic;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkAPI;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkFactory;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.util.UUID;
import java.util.function.Consumer;

public class DynamicNetworkAPIHandler implements DynamicNetworkAPI {

    private DynamicClient dynamicClient;
    private ClientSchematicHandler schematicHandler;

    public DynamicNetworkAPIHandler(DynamicClient dynamicClient, ClientSchematicHandler schematicHandler) {
        this.dynamicClient = dynamicClient;
        this.schematicHandler = schematicHandler;
        DynamicNetworkFactory.dynamicNetworkAPI = this;
    }

    @Override
    public void getSchematic(UUID uuid, Consumer<Schematic> request) {
        this.schematicHandler.addToQuery(uuid, request);
    }

    @Override
    public void saveSchematic(UUID uuid, Schematic schematic, Consumer<Boolean> result) {
        this.dynamicClient.getSkyllaClient().sendPacket(new SchematicPacket(uuid, schematic));
        //TODO: Add handler for result
    }
}
