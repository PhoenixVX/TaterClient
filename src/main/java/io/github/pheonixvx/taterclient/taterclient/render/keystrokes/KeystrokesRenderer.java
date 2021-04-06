package io.github.pheonixvx.taterclient.taterclient.render.keystrokes;

import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container.KeyContainer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;

public class KeystrokesRenderer {
	private static final int OFFSET = 10;
	private static final int DISTANCE = 20;
	private static final int GAP = 3;
	private static final KeyContainer KEY_FORWARD_CONTAINER = new KeyContainer(MinecraftClient.getInstance().options.keyForward, DISTANCE + GAP + OFFSET, DISTANCE, OFFSET, DISTANCE);
	private static final KeyContainer KEY_LEFT_CONTAINER = new KeyContainer(MinecraftClient.getInstance().options.keyLeft, OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE);
	private static final KeyContainer KEY_BACK_CONTAINER = new KeyContainer(MinecraftClient.getInstance().options.keyBack, DISTANCE + GAP + OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE);
	private static final KeyContainer KEY_RIGHT_CONTAINER = new KeyContainer(MinecraftClient.getInstance().options.keyRight, DISTANCE + GAP + DISTANCE + GAP + OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE);

	public static void onHudRender(MatrixStack matrices, float tickDelta) {
		KEY_FORWARD_CONTAINER.render(matrices);
		KEY_LEFT_CONTAINER.render(matrices);
		KEY_BACK_CONTAINER.render(matrices);
		KEY_RIGHT_CONTAINER.render(matrices);
	}

	public static void onEndTick(ClientWorld world) {
		KEY_FORWARD_CONTAINER.tick();
		KEY_LEFT_CONTAINER.tick();
		KEY_BACK_CONTAINER.tick();
		KEY_RIGHT_CONTAINER.tick();
	}
}
