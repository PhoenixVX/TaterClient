package io.github.pheonixvx.taterclient.taterclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class TaterClientClient implements ClientModInitializer {
	private static final Logger LOGGER = LogManager.getLogger("TaterClient");

	@Override
	public void onInitializeClient () {
		LOGGER.info("This is a Minecraft Mod");
		LOGGER.info("This is *NOT* a bitcoin miner");
		LOGGER.info("This is a PVP client");

		TaterPlayerRenderer.register();
	}
}
