package me.devsnox.dynamicminecraftnetwork.client;

import de.d3adspace.skylla.client.SkyllaClient;
import de.d3adspace.skylla.client.SkyllaClientFactory;
import de.d3adspace.skylla.commons.config.SkyllaConfig;
import de.d3adspace.skylla.commons.protocol.Protocol;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

public class DynamicClient {

    private SkyllaClient skyllaClient;

    public DynamicClient() {
        Protocol protocol = new Protocol();
        protocol.registerPacket(SchematicPacket.class);

        ClientSchematicHandler clientSchematicHandler = new ClientSchematicHandler();
        protocol.registerListener(clientSchematicHandler);

        SkyllaConfig config = SkyllaConfig.newBuilder()
                .setServerHost("localhost")
                .setServerPort(1337)
                .setProtocol(protocol)
                .createSkyllaConfig();

        this.skyllaClient = SkyllaClientFactory.createSkyllaClient(config);
        skyllaClient.connect();

        new DynamicNetworkAPIHandler(this, clientSchematicHandler);
    }

    public boolean isConnected() {
        //TODO: PingPacket
        return false;
    }

    public SkyllaClient getSkyllaClient() {
        return skyllaClient;
    }
}
