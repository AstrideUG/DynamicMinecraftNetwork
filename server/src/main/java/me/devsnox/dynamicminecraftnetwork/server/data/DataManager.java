package me.devsnox.dynamicminecraftnetwork.server.data;

import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {

    private File directory;

    public DataManager(String name) {
        this.directory = new File(name);

        if(!directory.exists()) {
            directory.mkdirs();
        }

    }

    public boolean exists(UUID uuid) {
        return new File(this.directory, uuid + ".schematic").exists();
    }

    public void save(UUID uuid, Schematic schematic) {
        try {
            schematic.save(new File(directory, uuid.toString()), ClipboardFormat.SCHEMATIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Schematic load(UUID uuid) {
       if(exists(uuid)) {
           File file = new File(this.directory, uuid + ".schematic");
           try {
               return ClipboardFormat.findByFile(file).load(file);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        return getDefaultSchematic();
    }

    private Schematic getDefaultSchematic() {
        File file = new File(this.directory.getParentFile(), "default.schematic");

        if(!file.exists() || file.isDirectory()) {
           throw new RuntimeException("IO Schematic Exception - No default file found!"); //TODO Better message
        } else {
            try {
                return ClipboardFormat.findByFile(file).load(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
