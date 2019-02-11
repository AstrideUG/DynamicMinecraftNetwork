package me.devsnox.dynamicminecraftnetwork.server;

import de.d3adspace.skylla.commons.config.SkyllaConfig;
import de.d3adspace.skylla.commons.protocol.Protocol;
import de.d3adspace.skylla.server.SkyllaServer;
import de.d3adspace.skylla.server.SkyllaServerFactory;
import me.devsnox.dynamicminecraftnetwork.commons.DynamicNetworkProtocol;
import me.devsnox.dynamicminecraftnetwork.server.handlers.ServerSchematicHandler;

public class DynamicServer {

    public DynamicServer() {
        Protocol protocol = new DynamicNetworkProtocol();
        protocol.registerListener(new ServerSchematicHandler());

        SkyllaConfig config = SkyllaConfig.newBuilder()
                .setServerHost("localhost")
                .setServerPort(1338)
                .setProtocol(protocol)
                .createSkyllaConfig();

        SkyllaServer skyllaServer = SkyllaServerFactory.createSkyllaServer(config);
        skyllaServer.start();
    }
}
