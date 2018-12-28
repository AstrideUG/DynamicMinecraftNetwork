package me.devsnox.dynamicminecraftnetwork.server.handlers;

import de.d3adspace.skylla.commons.connection.SkyllaConnection;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandler;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandlerMethod;
import me.devsnox.dynamicminecraftnetwork.commons.packets.ExistSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.RequestSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;
import me.devsnox.dynamicminecraftnetwork.server.data.DataManager;

public class ServerSchematicHandler implements PacketHandler {

    private DataManager dataManager;

    public ServerSchematicHandler() {
        this.dataManager = new DataManager("DynamicStorage");
    }

    @PacketHandlerMethod
    public void onServerSchematicRequest(SkyllaConnection skyllaConnection, RequestSchematicPacket requestSchematicPacket) {
        skyllaConnection.sendPackets(new SchematicPacket(requestSchematicPacket.getUuid(), this.dataManager.load(requestSchematicPacket.getUuid())));
    }

    @PacketHandlerMethod
    public void onServerSchematicReceive(SkyllaConnection skyllaConnection, SchematicPacket schematicPacket) {
        this.dataManager.save(schematicPacket.getUuid(), schematicPacket.getSchematic());
    }

    @PacketHandlerMethod
    public void onServerSchematicExistsReceive(SkyllaConnection skyllaConnection, ExistSchematicPacket existPacket) {
        skyllaConnection.sendPackets(new ExistSchematicPacket(existPacket.getUuid(), this.dataManager.exists(existPacket.getUuid())));
    }
}
