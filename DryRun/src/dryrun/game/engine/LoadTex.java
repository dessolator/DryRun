package dryrun.game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

//class used to load textures
public class LoadTex {
	public static Texture bmwM5;
	public static Texture mainMenuBackground;
	public static Texture basicFrame;
	public static Texture buttonTextures;
	public static Texture pushedButtonTextures;
	public static Texture loading1;
	public static Texture waitFrame;
	public static TextureHolder tex;
	public static LoadingScreen ls;
	public static Texture levelBackground;
	public static Texture machineGun;
	 
	/**
	 * used to load textures, some texstures for loading are inside ... :)
	 */
	public static void init(){
		try {
			ls=new LoadingScreen();
			
			loading1=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/loading.png")));
			
			tex=new TextureHolder(loading1,new Tex(0f,0f,0f,0f));//TODO for the love of god make this logic a method
			ls.render();
			
			bmwM5=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png")));
			

			ls.render();
			
			mainMenuBackground=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
			

			ls.render();
			
			basicFrame=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/basicFrame.png")));
			

			ls.render();
			
			buttonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			

			ls.render();
			
			pushedButtonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			

			ls.render();
			
			levelBackground=TextureLoader.getTexture("JPG", new FileInputStream(new File("res/debug.jpg")));
			

			ls.render();
			
			waitFrame = TextureLoader.getTexture("PNG" ,new FileInputStream(new File("res/waitFrame.png")));
			

			ls.render();
			
			machineGun = TextureLoader.getTexture("PNG" ,new FileInputStream(new File("res/minigun.png")));
			

			ls.render();
			

			ls.render();
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
