package dryrun.game.engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class DrawObject {
		
		public static void draw(Drawable d){
			if(d.getTexture() != null){
				d.getTexture().bind();
			}
			
			glPushMatrix();
			glTranslatef(d.getX(),Display.getHeight()-d.getY(), 0);
			glColor3f(1, 1, 1);
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			//mapiranje teksture
			glBegin(GL_QUADS);
				glTexCoord2f(d.getTexX1(), d.getTexY1());
				glVertex2f(-d.getDimX()/2, -d.getDimY()/2);
				glTexCoord2f(d.getTexX2(),d.getTexY1());
				glVertex2f(d.getDimX()/2,-d.getDimY()/2);
				glTexCoord2f(d.getTexX2(), d.getTexY2());
				glVertex2f(d.getDimX()/2, d.getDimY()/2);
				glTexCoord2f(d.getTexX1(), d.getTexY2());
				glVertex2f(-d.getDimX()/2, d.getDimY()/2);						
			glEnd();
			glPopMatrix();
			
		}
}
