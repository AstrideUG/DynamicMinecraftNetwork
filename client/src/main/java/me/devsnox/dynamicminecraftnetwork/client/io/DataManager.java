package me.devsnox.dynamicminecraftnetwork.client.io;

import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {

    private File directory;

    public DataManager(String name) {
        this.directory = new File(name);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public boolean exists(UUID uuid) {
        return new File(this.directory, uuid + ".schematic").exists();
    }

    public void save(UUID uuid, Schematic schematic) {
        System.out.println(schematic.toString());
        try {
            schematic.save(new File(this.directory, uuid + ".schematic"), ClipboardFormat.SCHEMATIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Schematic load(UUID uuid) {
        if (exists(uuid)) {
            return load(new File(this.directory, uuid.toString() + ".schematic"));
        }

        return getDefaultSchematic();
    }

    private Schematic getDefaultSchematic() {
        File file = new File(this.directory, "default.schematic");

        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("IO NetworkSchematic Exception - No default file found!"); //TODO Better message
        }

        return load(file);
    }

    private Schematic load(File file) {
        try {
            return ClipboardFormat.SCHEMATIC.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
