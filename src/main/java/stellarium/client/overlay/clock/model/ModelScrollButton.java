package stellarium.client.overlay.clock.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import stellarapi.lib.gui.IRectangleBound;
import stellarapi.lib.gui.IRenderModel;
import stellarapi.lib.gui.model.basic.ModelSimpleRect;
import stellarapi.lib.gui.model.basic.ModelSimpleTextured;
import stellarium.StellarSkyResources;

public class ModelScrollButton implements IRenderModel {
	
	private static final ModelScrollButton instance = new ModelScrollButton();
	
	public static ModelScrollButton getInstance() {
		return instance;
	}
	
	private ModelSimpleRect selectModel = ModelSimpleRect.getInstance();
	private ModelSimpleTextured scrollbtn;
	
	public ModelScrollButton() {
		this.scrollbtn = new ModelSimpleTextured(StellarSkyResources.scrollbtn);
	}

	@Override
	public void renderModel(String info, IRectangleBound totalBound, IRectangleBound clipBound, Tessellator tessellator,
			TextureManager textureManager, float[] color) {
		GL11.glPushMatrix();
		if(info.equals("select")) {
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			color[3] *= 0.2f;
			selectModel.renderModel(info, totalBound, clipBound, tessellator, textureManager, color);
		} else if(info.equals("button")) {
			scrollbtn.renderModel(info, totalBound, clipBound, tessellator, textureManager, color);
		}
		GL11.glPopMatrix();
	}

}