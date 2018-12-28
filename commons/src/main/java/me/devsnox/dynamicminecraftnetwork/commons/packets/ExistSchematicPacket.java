package me.devsnox.dynamicminecraftnetwork.commons.packets;

import de.d3adspace.skylla.commons.buffer.SkyllaBuffer;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacket;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacketMeta;

import java.util.UUID;

@SkyllaPacketMeta(id = 2)
public class ExistSchematicPacket extends SkyllaPacket {

    private UUID uuid;
    private boolean exists;

    public ExistSchematicPacket() {
    }

    public ExistSchematicPacket(UUID uuid, boolean exists) {
        this.uuid = uuid;
        this.exists = exists;
    }

    @Override
    public void write(SkyllaBuffer skyllaBuffer) {
        skyllaBuffer.writeUniqueId(uuid);
        skyllaBuffer.writeBoolean(this.exists);
    }

    @Override
    public void read(SkyllaBuffer skyllaBuffer) {
        this.uuid = skyllaBuffer.readUniqueId();
        this.exists = skyllaBuffer.readBoolean();
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean doesExists() {
        return exists;
    }
}
