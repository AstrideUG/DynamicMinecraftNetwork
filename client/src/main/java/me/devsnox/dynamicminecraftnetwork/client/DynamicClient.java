package me.devsnox.dynamicminecraftnetwork.client;

import de.d3adspace.skylla.client.SkyllaClient;
import de.d3adspace.skylla.client.SkyllaClientFactory;
import de.d3adspace.skylla.commons.config.SkyllaConfig;
import de.d3adspace.skylla.commons.protocol.Protocol;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.util.UUID;

public class DynamicClient {


    public static void main(String[] args) {
        Protocol protocol = new Protocol();
        protocol.registerPacket(SchematicPacket.class);
        protocol.registerListener(new ClientSchematicHandler());

        SkyllaConfig config = SkyllaConfig.newBuilder()
                .setServerHost("localhost")
                .setServerPort(1337)
                .setProtocol(protocol)
                .createSkyllaConfig();

        SkyllaClient skyllaClient = SkyllaClientFactory.createSkyllaClient(config);
        skyllaClient.connect();

        skyllaClient.sendPacket(new SchematicPacket(UUID.randomUUID(), null));
    }
}
