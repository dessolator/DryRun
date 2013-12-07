package dryrun.game.gui.menus.misc.dropdown;

import org.newdawn.slick.opengl.Texture;
import dryrun.game.gui.misc.buttons.Button;



public abstract class DropDownEntry extends Button {

	@Override
	public Texture getTexture() {
		return myTexture.getMyTexture();
	}

	public DropDownEntry(float coordX, float coordY,String myResolution) {
		super(coordX, coordY,myResolution);
	}

	

}
