package io.github.pheonixvx.taterclient.taterclient.config;

import io.github.pheonixvx.taterclient.taterclient.TaterClient;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = TaterClient.MOD_ID)
public class ModConfig implements ConfigData {
	public boolean renderPlayersAsTaters = false;
}
