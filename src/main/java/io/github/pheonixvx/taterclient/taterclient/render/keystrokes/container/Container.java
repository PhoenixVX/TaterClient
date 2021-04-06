package io.github.pheonixvx.taterclient.taterclient.render.keystrokes.container;

import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.util.math.MatrixStack;

public interface Container extends ClientPlayerTickable {
	void tick();

	void render(MatrixStack stack);
}
