package me.devsnox.dynamicminecraftnetwork.commons.worldedit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;
import com.sk89q.worldedit.world.registry.WorldData;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SchematicLoader {
    /**
     * Loads a schematic from the plugins/WorldEdit/schematics folder
     * @param world The world in which the schematic will be used
     * @param schematicName The file name in the schematics folder
     */
    public static Schematic load(World world, String schematicName) throws IOException {
        if(!WorldEditUtil.initWorldEdit()) {
            return null;
        }

        return load(world, WorldEditUtil.getWorldEdit().getWorkingDirectoryFile("schematics/" + schematicName));
    }

    /**
     * Loads a schematic from a file
     * @param world The world in which the schematic will be used
     * @param schematicFile
     */
    public static Schematic load(World world, File schematicFile) throws IOException {
        if(!WorldEditUtil.initWorldEdit()) {
            return null;
        }

        com.sk89q.worldedit.world.World weWorld = WorldEditUtil.getWEWorld(world);
        WorldData worldData = weWorld.getWorldData();

        ClipboardFormat format = ClipboardFormat.findByFile(schematicFile);

        Closer closer = Closer.create();

        BufferedInputStream inputStream = closer.register(new BufferedInputStream(closer.register(new FileInputStream(schematicFile))));
        ClipboardReader clipboardReader = format.getReader(inputStream);

        Clipboard clipboard = clipboardReader.read(worldData);
        ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard, worldData);

        closer.close();

        return new Schematic(WorldEditUtil.createSession(weWorld), new ClipboardHolder(clipboardHolder.getClipboard(), clipboardHolder.getWorldData()));
    }

    /**
     * Loads an area in the world into a schematic (for saving it)
     * @param world The world in which the schematic will be used
     * @param boundingBox The bounding box of the area
     * @param origin An origin, used for rotating and when pasting the schematic again. Usually set to (0,0) or the (bottom-)center of the area
     * @return a {@link Schematic}
     */
    public static Schematic loadArea(World world, BoundingBox boundingBox, org.bukkit.util.Vector origin) {
        if(!WorldEditUtil.initWorldEdit()) {
            return null;
        }

        com.sk89q.worldedit.world.World weWorld = WorldEditUtil.getWEWorld(world);

        CuboidRegion region = new CuboidRegion(toVector(boundingBox.getMin()), toVector(boundingBox.getMax()));

        EditSession editSession = WorldEditUtil.createSession(weWorld);

        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(new Vector(origin.getBlockX(), origin.getBlockY(), origin.getBlockZ()));
        ForwardExtentCopy copy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

        try {
            Operations.completeLegacy(copy);
        } catch(MaxChangedBlocksException ex) {
            return null;
        }

        return new Schematic(editSession, new ClipboardHolder(clipboard, editSession.getWorld().getWorldData()));
    }

    private static Vector toVector(Location location) {
        return new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
