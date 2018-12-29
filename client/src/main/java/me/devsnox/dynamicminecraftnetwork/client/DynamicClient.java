package me.devsnox.dynamicminecraftnetwork.client;

import de.d3adspace.skylla.client.SkyllaClient;
import de.d3adspace.skylla.client.SkyllaClientFactory;
import de.d3adspace.skylla.commons.config.SkyllaConfig;
import de.d3adspace.skylla.commons.protocol.Protocol;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkFactory;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.commons.worldedit.DynamicNetworkProtocol;

public class DynamicClient {

    public DynamicClient() {
        Protocol protocol = new DynamicNetworkProtocol();

        ClientSchematicHandler schematicHandler = new ClientSchematicHandler();
        protocol.registerListener(schematicHandler);

        SkyllaConfig config = SkyllaConfig.newBuilder()
                .setServerHost("localhost")
                .setServerPort(1338)
                .setProtocol(protocol)
                .createSkyllaConfig();

        SkyllaClient skyllaClient = SkyllaClientFactory.createSkyllaClient(config);
        skyllaClient.connect();

        DynamicNetworkFactory.dynamicNetworkAPI = new DynamicNetworkAPIHandler(schematicHandler, skyllaClient);
    }
}
