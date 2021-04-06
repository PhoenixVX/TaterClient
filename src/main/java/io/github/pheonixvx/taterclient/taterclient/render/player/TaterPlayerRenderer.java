package io.github.pheonixvx.taterclient.taterclient.render.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import io.github.pheonixvx.taterclient.taterclient.TaterClient;

import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.TexturedRenderLayers;

import static io.github.pheonixvx.taterclient.taterclient.TaterClient.id;

public class TaterPlayerRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	public static void register() {
		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper) -> {
			registrationHelper.register(new TaterPlayerRenderer((PlayerEntityRenderer) entityRenderer));
		});
	}

	private static final Identifier TATER_MODEL = id("tater");

	public TaterPlayerRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (!TaterClient.CONFIG.getConfig().renderPlayersAsTaters)
			return;

		BakedModel model = MinecraftClient.getInstance().getBakedModelManager().getModel((ModelIdentifier) TATER_MODEL);

		matrices.push();

		MinecraftClient.getInstance().getBlockRenderManager().getModelRenderer().render(
			matrices.peek(),
			vertexConsumers.getBuffer(TexturedRenderLayers.getEntityCutout()),
			null,
			model,
			1.0F, 1.0F, 1.0F,
			light,
			OverlayTexture.DEFAULT_UV
		);

		matrices.pop();
	}
}
