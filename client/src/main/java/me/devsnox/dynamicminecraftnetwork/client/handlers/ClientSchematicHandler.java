package me.devsnox.dynamicminecraftnetwork.client.handlers;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.commons.connection.SkyllaConnection;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandler;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandlerMethod;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ClientSchematicHandler implements PacketHandler {

    private Map<UUID, Consumer<Schematic>> requests;

    public ClientSchematicHandler() {
        this.requests = new ConcurrentHashMap<>();
    }

    @PacketHandlerMethod
    public void onClientSchematicReceive(SkyllaConnection skyllaConnection, SchematicPacket schematicPacket) {
        this.requests.get(schematicPacket.getUuid()).accept(schematicPacket.getSchematic());
    }

    public void addToQuery(UUID uuid, Consumer<Schematic> request) {
        this.requests.put(uuid, request);
    }
}
