package me.devsnox.dynamicminecraftnetwork.commons.packets;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.commons.buffer.SkyllaBuffer;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacket;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacketMeta;

import java.util.UUID;

@SkyllaPacketMeta(id = 0)
public final class SchematicPacket extends SkyllaPacket {

    private UUID uuid;
    private Schematic schematic;

    public SchematicPacket() {}

    public SchematicPacket(UUID uuid, Schematic schematic) {
        this.uuid = uuid;
        this.schematic = schematic;
    }

    @Override
    public final void write(SkyllaBuffer skyllaBuffer) {
        skyllaBuffer.writeUniqueId(uuid);
        skyllaBuffer.writeObject(this.schematic);
    }

    @Override
    public final void read(SkyllaBuffer skyllaBuffer) {
        this.uuid = skyllaBuffer.readUniqueId();
        this.schematic = (Schematic) skyllaBuffer.readObject();
    }

    public final UUID getUuid() {
        return uuid;
    }

    public Schematic getSchematic() {
        return schematic;
    }
}
