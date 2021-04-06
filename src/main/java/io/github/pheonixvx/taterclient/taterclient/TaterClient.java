package io.github.pheonixvx.taterclient.taterclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.KeystrokesRenderer;
import io.github.pheonixvx.taterclient.taterclient.render.player.TaterPlayerRenderer;
import io.github.pheonixvx.taterclient.taterclient.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TaterClient implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("TaterClient");
	public static final String MOD_ID = "taterclient";
	public static ConfigHolder<ModConfig> CONFIG = AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

	@Override
	public void onInitializeClient () {
		LOGGER.info("This is a Minecraft Mod");
		LOGGER.info("This is *NOT* a bitcoin miner");
		LOGGER.info("This is a PVP client");

//		TaterPlayerRenderer.register();
		HudRenderCallback.EVENT.register(KeystrokesRenderer::onHudRender);
		ClientTickEvents.END_WORLD_TICK.register(KeystrokesRenderer::onEndTick);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
