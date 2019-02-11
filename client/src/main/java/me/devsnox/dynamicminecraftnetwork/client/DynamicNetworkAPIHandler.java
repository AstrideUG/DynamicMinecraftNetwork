package me.devsnox.dynamicminecraftnetwork.client;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.client.SkyllaClient;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkAPI;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.client.io.DataManager;
import me.devsnox.dynamicminecraftnetwork.commons.packets.RequestSchematicPacket;

import java.util.UUID;
import java.util.function.Consumer;

public class DynamicNetworkAPIHandler implements DynamicNetworkAPI {

    private DataManager dataManager;
    private ClientSchematicHandler schematicHandler;
    private SkyllaClient skyllaClient;

    public DynamicNetworkAPIHandler(ClientSchematicHandler schematicHandler, SkyllaClient skyllaClient) {
        this.dataManager = new DataManager("DynamicStorage");
        this.schematicHandler = schematicHandler;
        this.skyllaClient = skyllaClient;
    }



    @Override
    public void hasSchematic(UUID uuid, Consumer<Boolean> result) {
        this.schematicHandler.addToBooleanReceiveQuery(uuid, result);
        this.skyllaClient.sendPacket(new RequestSchematicPacket(uuid));
    }

    @Override
    public void getSchematic(UUID uuid, Consumer<Schematic> request) {
        request.accept(this.dataManager.load(uuid));
    }

    @Override
    public void saveSchematic(UUID uuid, Schematic schematic) {
        //this.dataManager.save(uuid, schematic);
    }

    /*@Override
    public void getSchematic(UUID uuid, Consumer<Schematic> request) {
        this.schematicHandler.addToReceiveQuery(uuid, request);
        this.skyllaClient.sendPacket(new RequestSchematicPacket(uuid));
    }

    @Override
    public void saveSchematic(UUID uuid, Schematic schematic) {
        this.skyllaClient.sendPacket(new SchematicPacket(uuid, schematic));

    }*/


}
