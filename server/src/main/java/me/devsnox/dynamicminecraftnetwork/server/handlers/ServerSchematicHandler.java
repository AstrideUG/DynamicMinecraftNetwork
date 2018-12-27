package me.devsnox.dynamicminecraftnetwork.server.handlers;

import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import de.d3adspace.skylla.commons.connection.SkyllaConnection;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandler;
import de.d3adspace.skylla.commons.protocol.handler.PacketHandlerMethod;
import de.d3adspace.skylla.server.SkyllaServer;
import me.devsnox.dynamicminecraftnetwork.commons.packets.RequestSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.io.File;
import java.io.IOException;

public class ServerSchematicHandler implements PacketHandler {

    @PacketHandlerMethod
    public void onServerSchematicRequest(SkyllaConnection skyllaConnection, RequestSchematicPacket requestSchematicPacket) {
        /*File file = new File(requestSchematicPacket.getUuid().toString());
        try {
            skyllaConnection.sendPackets(new SchematicPacket(requestSchematicPacket.getUuid(), ClipboardFormat.findByFile(file).load(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("request");
    }

    @PacketHandlerMethod
    public void onServerSchematicReceive(SkyllaConnection skyllaConnection, SchematicPacket schematicPacket) {
        /*try {
            schematicPacket.getSchematic().save(new File(schematicPacket.getUuid().toString()), ClipboardFormat.SCHEMATIC);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.printf("save");
    }
}
