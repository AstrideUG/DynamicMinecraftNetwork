package me.devsnox.dynamicminecraftnetwork.commons.packets;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.commons.buffer.SkyllaBuffer;
import de.d3adspace.skylla.commons.protocol.packet.SkyllaPacket;

import java.util.UUID;

public class SchematicPacket extends SkyllaPacket {

    private UUID uuid;
    private Schematic schematic;

    public SchematicPacket(UUID uuid, Schematic schematic) {
        this.uuid = uuid;
        this.schematic = schematic;
    }

    @Override
    public void write(SkyllaBuffer skyllaBuffer) {
        skyllaBuffer.writeUniqueId(uuid);
        skyllaBuffer.writeObject(schematic);
    }

    @Override
    public void read(SkyllaBuffer skyllaBuffer) {
        this.uuid = skyllaBuffer.readUniqueId();
        this.schematic = (Schematic) skyllaBuffer.readObject();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Schematic getSchematic() {
        return schematic;
    }
}
