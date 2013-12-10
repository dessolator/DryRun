package dryrun.game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class LoadTex {
	public static Texture playerTex;
	public static Texture mainMenuBackground;
	public static Texture basicFrame;
	public static Texture buttonTextures;
	public static Texture pushedButtonTextures;
	public static Texture loading1;

	public static void init(){
		try {
			playerTex=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png")));
			mainMenuBackground=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
			basicFrame=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/basicFrame.png")));
			buttonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			pushedButtonTextures=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png")));
			loading1=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/loading.png")));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
