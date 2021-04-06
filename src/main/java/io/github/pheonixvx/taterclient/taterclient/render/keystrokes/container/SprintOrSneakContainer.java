package io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container;

import java.util.function.DoubleSupplier;

import io.github.pheonixvx.taterclient.taterclient.render.keystrokes.RenderableBlock;
import it.unimi.dsi.fastutil.objects.Object2BooleanFunction;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

public class SprintOrSneakContainer implements Container {
	private final String key;
	private final Object2BooleanFunction<PlayerEntity> func;
	private final DoubleSupplier xOffset;
	private final RenderableBlock block;
	private final TextRenderer textRenderer;
	private int color;

	public SprintOrSneakContainer(int left, int width, int top, int height, String key, Object2BooleanFunction<PlayerEntity> func, DoubleSupplier xOffset) {
		this.key = key;
		this.func = func;
		this.xOffset = xOffset;
		this.block = new RenderableBlock(left, left + width, top, top + height);
		this.textRenderer = MinecraftClient.getInstance().textRenderer;
	}

	@Override
	public void tick() {
		PlayerEntity player = MinecraftClient.getInstance().player;
		if (func.apply(player)) {
			color = 0x7ffcba03;
		} else {
			color = 0x7f000000;
		}
	}

	@Override
	public void render(MatrixStack matrices) {
		matrices.push();
		matrices.scale(1.1F, 1.1F, 1.1F);
		this.block.fill(matrices, color);
		matrices.translate(14.5 + xOffset.getAsDouble(), 6.5, 0);
		this.block.drawString(
				matrices,
				I18n.translate(key),
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
