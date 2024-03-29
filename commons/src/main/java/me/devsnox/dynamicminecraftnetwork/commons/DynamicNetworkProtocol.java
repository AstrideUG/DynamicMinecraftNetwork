package me.devsnox.dynamicminecraftnetwork.commons;

import de.d3adspace.skylla.commons.protocol.Protocol;
import me.devsnox.dynamicminecraftnetwork.commons.packets.ExistSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.RequestSchematicPacket;
import me.devsnox.dynamicminecraftnetwork.commons.packets.SchematicPacket;

import java.io.Serializable;

public class DynamicNetworkProtocol extends Protocol {

    public DynamicNetworkProtocol() {
        this.registerPacket(SchematicPacket.class);
        this.registerPacket(RequestSchematicPacket.class);
        this.registerPacket(ExistSchematicPacket.class);
    }
}
