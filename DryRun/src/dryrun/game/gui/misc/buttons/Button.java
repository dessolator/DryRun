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
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import static dryrun.game.gui.misc.buttons.ButtonConstants.*;

public abstract class Button implements Drawable, Updateable{
	protected TextureHolder myTexture;
	protected TextureHolder pressedTexture;
	Text myText;
	protected boolean clicked=false;
	private boolean previousMouseState=false;
	
	
	public Button(float coordX, float coordY,String myText) {
		this.myText=new TimesNewRomanText(coordX,coordY,myText);
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = displayWidth/5;
		this.dimY = displayHeight/10;
		try {
			myTexture=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)3/10+(float)1/200),1f,((float)4/10+(float)1/200)));
			pressedTexture=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)9/10),1f,((float)1)));
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
	
	@Override
	public float getTexX1() {
		if(clicked)
			return pressedTexture.getMyCoords().getX1();
		return myTexture.getMyCoords().getX1();
	}
	@Override
	public float getTexX2() {
		if(clicked)
			return pressedTexture.getMyCoords().getX2();
		return myTexture.getMyCoords().getX2();
	}
	@Override
	public float getTexY1() {
		if(clicked)
			return pressedTexture.getMyCoords().getY1();
		return myTexture.getMyCoords().getY1();
	}
	@Override
	public float getTexY2() {
		if(clicked)
			return pressedTexture.getMyCoords().getY2();
		return myTexture.getMyCoords().getY2();
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
