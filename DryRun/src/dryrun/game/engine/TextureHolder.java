package dryrun.game.engine;

import org.newdawn.slick.opengl.Texture;


public class TextureHolder {	
	private Texture myTexture; //moja tekstura	
	private Tex myCoords; //moje koordinate
	
	public TextureHolder(Texture t, Tex coords){
		myTexture=t;
		myCoords=coords;
	}

	public Texture getMyTexture() {
		return myTexture;
	}

	public void setMyTexture(Texture myTexture) {
		this.myTexture = myTexture;
	}

	public Tex getMyCoords() {
		return myCoords;
	}

	public void setMyCoords(Tex myCoords) {
		this.myCoords = myCoords;
	}
	
	

}
