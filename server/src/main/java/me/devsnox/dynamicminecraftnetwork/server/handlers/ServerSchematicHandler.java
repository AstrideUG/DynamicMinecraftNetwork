package me.devsnox.dynamicminecraftnetwork.server.handlers;

import de.d3adspace.skylla.commons.connection.SkyllaConnection;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandler;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandlerMethod;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

public class ServerSchematicHandler implements PacketHandler {

    @PacketHandlerMethod
    public void onServerSchematicReceive(SkyllaConnection skyllaConnection, SchematicPacket schematicPacket) {
        //TODO: Handle packet!
    }
}
