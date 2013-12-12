package dryrun.game.engine.sounds;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import paulscode.sound.SoundSystem;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;
import paulscode.sound.libraries.SourceLWJGLOpenAL;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.Library;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException; 


public class SEngine {
	private static SoundSystem s=null;
	
	public static SoundSystem getSoundSystem(){
		if (s==null) {init();}
		return s;
	}
	
	public static void init(){
		try
		{
		SoundSystemConfig.addLibrary( LibraryLWJGLOpenAL.class );
		}
		catch( SoundSystemException e )
		{
		System.err.println( "error linking with lib" );
		}
		
		try
		{
		SoundSystemConfig.setCodec("ogg", CodecJOgg.class);
		
			
		}catch( SoundSystemException e ){System.err.println("error linking with the CodecWav plug-in" );} 
		
		if(s==null)	s=new SoundSystem();
		
		s.setListenerOrientation( 0, 0, -1, 0, -1, 0 );
		loadSounds();
	}
	
	public static void loadSounds(){
		s.newSource(false, "buttonclick", "buttonclick.ogg", false, 0, 0, 0, 0, 0);
		
	}
	
	
	
	public static void dispose(){s.cleanup();}
	
	
	
	

}
