package dryrun.game.gui.menus.misc.dropdown;

import dryrun.game.engine.Drawable;
import dryrun.game.engine.Updateable;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public abstract class DropDown implements Drawable, Updateable{
	ArrayList<DropDownEntry> myEntries;
	DropDownEntry def;
	boolean isDropped=false;
	private float dimX;
	private float dimY;
	private float coordX;
	private float coordY;

	
	public DropDown(float coordX, float coordY,float dimX, float dimY ) {
		super();
		this.dimX = dimX;
		myEntries=new ArrayList<DropDownEntry>();
		this.dimY = dimY;
		this.coordX = coordX;
		this.coordY = coordY;
		populate();
	}

	
	
	
	
	protected abstract void populate();
	@Override
	public void update() {
		if(isDropped){
			for(DropDownEntry d:myEntries){
				d.update();
			}
		}
		else{
			if(def.isInBounds(Mouse.getX(), Mouse.getY())&&Mouse.isButtonDown(0)){
				isDropped=true;
			}
		}
		
	}

	@Override
	public void render() {
		def.render();
		if(isDropped){
			for(DropDownEntry d:myEntries){
				d.render();
			}
		}		
	}

	@Override
	public Texture getTexture() {
		return null;
	}

	public float getX() {
		return coordX;
	}

	@Override
	public float getDimX() {
		return dimX;
	}

	public float getY() {
		return coordY;
	}

	@Override
	public float getDimY() {
		return dimY;
	}
	

}
