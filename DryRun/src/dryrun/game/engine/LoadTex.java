package dryrun.game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.objects.TextureHolder;

public class LoadTex {
	public static Texture bmwM5;
	public static Texture mainMenuBackground;
	public static Texture basicFrame;
	public static Texture buttonTextures;
	public static Texture pushedButtonTextures;
	public static Texture loading1;
	public static TextureHolder tex;
	public static LoadingScreen ls;
	public static Texture levelBackground;
	
	
	public static void init(){
		try {
			ls=new LoadingScreen();
			
			loading1=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/loading.png")));
			
			tex=new TextureHolder(loading1,new Tex(0f,0f,1/8f,1f));//TODO for the love of god make this logic a method
			ls.render();
			
			bmwM5=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png")));
			
			tex=new TextureHolder(loading1,new Tex(1/8f,0f,2/8f,1f));
			ls.render();
			
			mainMenuBackground=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
			
			tex=new TextureHolder(loading1,new Tex(2/8f,0f,3/8f,1f));
			ls.render();
			
			basicFrame=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/basicFrame.png")));
			
			tex=new TextureHolder(loading1,new Tex(3/8f,0f,4/8f,1f));
			ls.render();
			
			buttonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			
			tex=new TextureHolder(loading1,new Tex(4/8f,0f,5/8f,1f));
			ls.render();
			
			pushedButtonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			
			tex=new TextureHolder(loading1,new Tex(5/8f,0f,6/8f,1f));
			ls.render();
			levelBackground=TextureLoader.getTexture("JPG", new FileInputStream(new File("res/debug.jpg")));
			tex=new TextureHolder(loading1,new Tex(6/8f,0f,7/8f,1f));
			ls.render();
			tex=new TextureHolder(loading1,new Tex(7/8f,0f,8/8f,1f));
			ls.render();
			tex=new TextureHolder(loading1,new Tex(0/8f,0f,1/8f,1f));
			ls.render();
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
