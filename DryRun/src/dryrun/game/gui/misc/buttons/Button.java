package dryrun.game.gui.misc.buttons;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.misc.text.Text;
import dryrun.game.gui.menus.misc.text.TimesNewRomanText;
import dryrun.game.objects.TextureHolder;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public abstract class Button implements Drawable, Updateable{
	protected TextureHolder myTexture;
	protected TextureHolder pressedTexture;
	Text myText;
	protected boolean clicked=false;
	private boolean previousMouseState=false;
	
	
	public Button(float coordX, float coordY, float dimX, float dimY,String myText) {
		this.myText=new TimesNewRomanText(coordX,coordY,myText);
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
		try {
			myTexture=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,0f,1f,1f));
			pressedTexture=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(1f,1f,1f,1f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private float coordX;
	private float coordY;
	private float dimX;
	private float dimY;
	public void render(){
		DrawObject.draw(this);
		myText.render();
	}
	public void click(){
		clicked=true;
	}
	public void unClick(){
		clicked=false;
	}
	public boolean isClicked(){
		return clicked;
	}
	public boolean isInBounds(float x,float y){
		if (x<(coordX-(dimX/2)))
			return false;
		if (x>(coordX+(dimX/2)))
			return false;
		if (y>(coordY+(dimY/2)))
			return false;
		if (y<(coordY-(dimY/2)))
			return false;
		return true;
	}
	@Override
	public Texture getTexture() {
		if(clicked)
			return pressedTexture.getMyTexture();
		return myTexture.getMyTexture();
	}
	@Override
	public float getX() {
		return coordX;
	}
	@Override
	public float getDimX() {
		return dimX;
	}
	@Override
	public float getY() {
		return coordY;
	}
	@Override
	public float getDimY() {
		return dimY;
	}
	public abstract void pressed();
	@Override
	public void update() {
		if(Mouse.getEventButtonState()&&isInBounds(Mouse.getX(), Mouse.getY())){
			click();
			previousMouseState=true;
			return;
		}
		if(previousMouseState&&!Mouse.getEventButtonState()&&isInBounds(Mouse.getX(), Mouse.getY()) && isClicked()){
			pressed();
			previousMouseState=false;
			return;
		}
		unClick();
		
	}

}
