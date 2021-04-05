package io.github.pheonixvx.taterclient.taterclient;

import io.github.pheonixvx.taterclient.taterclient.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

public class TaterClient implements ModInitializer {

	public static final String MOD_ID = "taterclient";

	public static ConfigHolder<ModConfig> CONFIG = AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize () {

	}
}
