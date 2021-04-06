package io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container;

import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.RenderableBlock;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;

public class MovementKeyContainer implements Container {
	private final RenderableBlock block;
	private final KeyBinding key;
	private final String text;
	private final TextRenderer textRenderer;
	private int color;

	public MovementKeyContainer(KeyBinding key, int left, int width, int top, int height, String text) {
		this.key = key;
		this.text = text;
		this.block = new RenderableBlock(left, left + width, top, top + height);
		this.textRenderer = MinecraftClient.getInstance().textRenderer;
	}

	public void tick() {
		if (key.isPressed()) {
			color = 0x7ffcba03;
		} else {
			color = 0x7f000000;
		}
	}

	public void render(MatrixStack matrices) {
		matrices.push();
		matrices.scale(1.1F, 1.1F, 1.1F);
		this.block.fill(matrices, color);
		matrices.translate(10.5, 6.5, 0);
		this.block.drawString(
				matrices,
				text,
				textRenderer,
				false,
				true,
				0,
				0,
				false,
				false,
				0xFFFFFF,
				true
				);
		matrices.pop();
	}
}
