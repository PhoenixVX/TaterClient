package io.github.pheonixvx.taterclient.taterclient.render.keystrokes;

import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container.MovementKeyContainer;
import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container.SprintOrSneakContainer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;

public class KeystrokesRenderer {
	private static final int OFFSET = 10;
	private static final int DISTANCE = 20;
	private static final int GAP = 2;
	private static final MovementKeyContainer KEY_FORWARD_CONTAINER = new MovementKeyContainer(MinecraftClient.getInstance().options.keyForward, DISTANCE + GAP + OFFSET, DISTANCE, OFFSET, DISTANCE, "\u25B2");
	private static final MovementKeyContainer KEY_LEFT_CONTAINER = new MovementKeyContainer(MinecraftClient.getInstance().options.keyLeft, OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE, "\u25C0");
	private static final MovementKeyContainer KEY_BACK_CONTAINER = new MovementKeyContainer(MinecraftClient.getInstance().options.keyBack, DISTANCE + GAP + OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE, "\u25BC");
	private static final MovementKeyContainer KEY_RIGHT_CONTAINER = new MovementKeyContainer(MinecraftClient.getInstance().options.keyRight, DISTANCE + GAP + DISTANCE + GAP + OFFSET, DISTANCE, DISTANCE + GAP + OFFSET, DISTANCE, "\u25B6");
	private static final SprintOrSneakContainer KEY_SPRINT_CONTAINER = new SprintOrSneakContainer(OFFSET, DISTANCE + (DISTANCE / 2) + 1, DISTANCE + GAP + DISTANCE + GAP + OFFSET, DISTANCE, "taterclient.key.sprint", p -> ((PlayerEntity) p).isSprinting(), () -> 0);
	private static final SprintOrSneakContainer KEY_SNEAK_CONTAINER = new SprintOrSneakContainer(OFFSET + DISTANCE + (DISTANCE / 2) + 1 + GAP, DISTANCE + (DISTANCE / 2) + 1, DISTANCE + GAP + DISTANCE + GAP + OFFSET, DISTANCE, "taterclient.key.sneak", p -> ((PlayerEntity) p).isSneaking(), KeystrokesRenderer::getSneakXOffsetThing);

	public static void onHudRender(MatrixStack matrices, float tickDelta) {
		KEY_FORWARD_CONTAINER.render(matrices);
		KEY_LEFT_CONTAINER.render(matrices);
		KEY_BACK_CONTAINER.render(matrices);
		KEY_RIGHT_CONTAINER.render(matrices);
		KEY_SPRINT_CONTAINER.render(matrices);
		KEY_SNEAK_CONTAINER.render(matrices);
	}

	public static void onEndTick(ClientWorld world) {
		KEY_FORWARD_CONTAINER.tick();
		KEY_LEFT_CONTAINER.tick();
		KEY_BACK_CONTAINER.tick();
		KEY_RIGHT_CONTAINER.tick();
		KEY_SPRINT_CONTAINER.tick();
		KEY_SNEAK_CONTAINER.tick();
	}

	private static double getSneakXOffsetThing() {
		int i = 1;
		i++;
		i--;
		return i;
	}
}
