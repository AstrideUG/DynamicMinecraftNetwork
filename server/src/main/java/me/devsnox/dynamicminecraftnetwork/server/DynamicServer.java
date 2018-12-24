package me.devsnox.dynamicminecraftnetwork.server;

import de.d3adspace.skylla.commons.config.SkyllaConfig;
import de.d3adspace.skylla.commons.protocol.Protocol;
import de.d3adspace.skylla.server.SkyllaServer;
import de.d3adspace.skylla.server.SkyllaServerFactory;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;
import me.devsnox.dynamicminecraftnetwork.server.handlers.ServerSchematicHandler;

public class DynamicServer {

    public static void main(String[] args) {
        Protocol protocol = new Protocol();
        protocol.registerPacket(SchematicPacket.class);
        protocol.registerListener(new ServerSchematicHandler());

        SkyllaConfig config = SkyllaConfig.newBuilder()
                .setServerHost("localhost")
                .setServerPort(1337)
                .setProtocol(protocol)
                .createSkyllaConfig();

        SkyllaServer skyllaServer = SkyllaServerFactory.createSkyllaServer(config);
        skyllaServer.start();
    }
}
