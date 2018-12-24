package me.devsnox.dynamicminecraftnetwork.client.handlers;

import de.d3adspace.skylla.commons.connection.SkyllaConnection;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandler;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandlerMethod;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

public class ClientSchematicHandler implements PacketHandler {

    @PacketHandlerMethod
    public void onClientSchematicReceive(SkyllaConnection skyllaConnection, SchematicPacket schematicPacket) {
        //TODO: Handle packet!
    }
}
