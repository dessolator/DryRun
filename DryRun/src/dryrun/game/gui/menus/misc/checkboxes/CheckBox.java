package dryrun.game.gui.menus.misc.checkboxes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.engine.Tex;
import dryrun.game.gui.misc.buttons.Button;
import dryrun.game.objects.TextureHolder;


public abstract class CheckBox extends Button{
	


	boolean isChecked=false;
	TextureHolder checked;
	public CheckBox(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"");
		try {
			myTexture=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(1f,1f,1f,1f));
			checked=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(1f,1f,1f,1f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	@Override
	public Texture getTexture() {
		if(isChecked)
			return checked.getMyTexture();
		return myTexture.getMyTexture();
	}



	@Override
	public void pressed() {
		isChecked=!isChecked;
		
	}
	
}
