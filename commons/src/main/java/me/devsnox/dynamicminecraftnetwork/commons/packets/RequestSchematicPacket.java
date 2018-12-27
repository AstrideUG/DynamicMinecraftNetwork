package me.devsnox.dynamicminecraftnetwork.commons.packets;

import de.d3adspace.skylla.commons.buffer.SkyllaBuffer;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacket;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacketMeta;

import java.util.UUID;

@SkyllaPacketMeta(id = 1)
public final class RequestSchematicPacket extends SkyllaPacket {

    private UUID uuid;

    public RequestSchematicPacket() {
    }

    public RequestSchematicPacket(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void write(SkyllaBuffer skyllaBuffer) {
        skyllaBuffer.writeUniqueId(this.uuid);
    }

    @Override
    public void read(SkyllaBuffer skyllaBuffer) {
        this.uuid = skyllaBuffer.readUniqueId();
    }

    public UUID getUuid() {
        return uuid;
    }
}
