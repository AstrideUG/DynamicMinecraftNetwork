package me.devsnox.dynamicminecraftnetwork.client;

import com.boydti.fawe.object.schematic.Schematic;
import de.d3adspace.skylla.client.SkyllaClient;
import me.devsnox.dynamicminecraftnetwork.api.DynamicNetworkAPI;
import me.devsnox.dynamicminecraftnetwork.client.handlers.ClientSchematicHandler;
import me.devsnox.dynamicminecraftnetwork.client.io.DataManager;

import java.util.UUID;
import java.util.function.Consumer;

public class DynamicNetworkAPIHandler implements DynamicNetworkAPI
{
	private final DataManager dataManager;
	private final ClientSchematicHandler schematicHandler;
	private final SkyllaClient skyllaClient;
	
	public DynamicNetworkAPIHandler(ClientSchematicHandler schematicHandler, SkyllaClient skyllaClient)
	{
		this.dataManager = new DataManager("DynamicStorage");
		this.schematicHandler = schematicHandler;
		this.skyllaClient = skyllaClient;
	}
	
	@Override
	public void hasSchematic(UUID uuid, Consumer<Boolean> result)
	{
		result.accept(this.dataManager.exists(uuid));
//		this.schematicHandler.addToBooleanReceiveQuery(uuid, result);
//		this.skyllaClient.sendPacket(new RequestSchematicPacket(uuid));
	}
	
	@Override
	public void getSchematic(UUID uuid, Consumer<Schematic> request)
	{
		System.out.println("getSchematic" + uuid);
		request.accept(this.dataManager.load(uuid));
	}
	
	@Override
	public void saveSchematic(UUID uuid, Schematic schematic)
	{
		this.dataManager.save(uuid, schematic);
	}

    /*@Override
    public void getSchematic(UUID uuid, Consumer<Schematic> request) {
        this.schematicHandler.addToReceiveQuery(uuid, request);
        this.skyllaClient.sendPacket(new RequestSchematicPacket(uuid));
    }

    @Override
    public void saveSchematic(UUID uuid, Schematic schematic) {
        this.skyllaClient.sendPacket(new SchematicPacket(uuid, schematic));

    }*/
}
