package me.devsnox.dynamicminecraftnetwork.commons.worldedit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SchematicSaver {
    /**
     * Saves a schematic to a file (Ignores any transforms right now)
     * @param schematic The schematic
     * @param schematicFile The file to save the schematic to
     */
    public static boolean save(Schematic schematic, File schematicFile) throws IOException {
        if(!WorldEditUtil.initWorldEdit()) {
            return false;
        }

        ClipboardHolder clipboardHolder = schematic.getClipboardHolder();
        Clipboard clipboard = clipboardHolder.getClipboard();

        Closer closer = Closer.create();

        File parent = schematicFile.getParentFile();

        if(parent != null && !parent.exists()) {
            if(!parent.mkdirs()) {
                throw new IOException("Could not create folder for the schematic!");
            }
        }

        FileOutputStream fileOutputStream = closer.register(new FileOutputStream(schematicFile));
        BufferedOutputStream bufferedOutputStream = closer.register(new BufferedOutputStream(fileOutputStream));
        ClipboardWriter clipboardWriter = closer.register(ClipboardFormat.SCHEMATIC.getWriter(bufferedOutputStream));
        clipboardWriter.write(clipboard, clipboardHolder.getWorldData());

        try {
            closer.close();
        } catch(IOException ignored) {
        }

        return true;
    }
}
