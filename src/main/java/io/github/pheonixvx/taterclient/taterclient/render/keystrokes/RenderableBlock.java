package io.github.pheonixvx.taterclient.taterclient.render.keystrokes;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

@SuppressWarnings("unused")
public class RenderableBlock extends DrawableHelper {
	private int left;
	private int right;
	private int top;
	private int bottom;
	private boolean expandRight;
	private boolean printRight;

	public RenderableBlock(int left, int right, int top, int bottom) {
		this.expandRight = true;
		this.printRight = false;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public RenderableBlock multiply(double scale) {
		return new RenderableBlock((int)(this.left * scale), (int)(this.right * scale), (int)(this.top * scale), (int)(this.bottom * scale));
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("left", left)
				.append("right", right)
				.append("top", top)
				.append("bottom", bottom)
				.append("expandRight", expandRight)
				.append("printRight", printRight)
				.toString();
	}

	public int getWidth() {
		return this.right - this.left;
	}

	public int getHeight() {
		return this.bottom - this.top;
	}

	public void ensureWidth(int width, boolean scaleRight) {
		if (getWidth() < width)
			if (scaleRight) {
				this.right = this.left + width;
			} else {
				this.left = this.right - width;
			}
	}

	public void ensureHeight(int height, boolean scaleBottom) {
		if (getHeight() < height)
			if (scaleBottom) {
				this.bottom = this.top + height;
			} else {
				this.top = this.bottom - height;
			}
	}

	public int getLeft() {
		return this.left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return this.right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getTop() {
		return this.top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public boolean isMouseOver(int x, int y) {
		return (x >= this.left && x <= this.right && y >= this.top && y <= this.bottom);
	}

	public int getBottom() {
		return this.bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public boolean drawString(MatrixStack matrices, List<String> strings, TextRenderer textRenderer, boolean shadow, boolean center, int xOffset, int yOffset, boolean scaleToFitX, boolean scaleToFixY, int color, boolean sideLeft) {
		boolean suc = true;
		for (String string : strings)
			suc = (suc && drawString(matrices, string, textRenderer, shadow, center, xOffset, yOffset, scaleToFitX, scaleToFixY, color, sideLeft));
		return suc;
	}

	public void fill(MatrixStack matrices, int color) {
		this.fillGradient(matrices, left, top, right, bottom, color, color);
	}

	public void translate(int x, int y) {
		this.left += x;
		this.right += x;
		this.top += y;
		this.bottom += y;
	}

	public void scalePosition(float amount) {
		this.left *= (int)amount;
		this.right *= (int)amount;
		this.top *= (int)amount;
		this.bottom *= (int)amount;
	}

	public boolean drawString(MatrixStack matrices, String string, TextRenderer textRenderer, boolean shadow, boolean center, int xOffset, int yOffset, boolean scaleToFitX, boolean scaleToFixY, int color, boolean sideLeft) {
		int x, stringWidth = textRenderer.getWidth(string);
		if (sideLeft) {
			x = this.left + xOffset;
		} else {
			x = this.right - stringWidth - xOffset;
		}
		int y = this.top + yOffset;
		if (center)
			x -= stringWidth / 2;
		if (sideLeft) {
			if (x + stringWidth > this.right) {
				if (!scaleToFitX)
					return false;
				if (this.expandRight) {
					this.right = x + stringWidth + xOffset;
				} else {
					this.left = this.right - stringWidth - xOffset;
					x = this.left;
				}
			}
		} else if (this.right - stringWidth < this.left) {
			if (!scaleToFitX)
				return false;
			if (this.expandRight) {
				this.right = x + stringWidth + xOffset;
				x = this.right;
			} else {
				this.left = this.right - stringWidth - xOffset;
			}
		}
		if (y + 10 > this.bottom) {
			if (!scaleToFixY)
				return false;
			this.bottom = y + 10;
		}
		if (y < this.top) {
			if (!scaleToFixY)
				return false;
			this.top = y;
		}
		if (shadow) {
			textRenderer.drawWithShadow(matrices, string, x, y, color);
		} else {
			textRenderer.draw(matrices, string, x, y, color);
		}

		return true;
	}

	public boolean isExpandRight() {
		return this.expandRight;
	}

	public void setExpandRight(boolean expandRight) {
		this.expandRight = expandRight;
	}

	public boolean isPrintRight() {
		return this.printRight;
	}

	public void setPrintRight(boolean printRight) {
		this.printRight = printRight;
	}
}
