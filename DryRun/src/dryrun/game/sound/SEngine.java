package dryrun.game.sound;

import paulscode.sound.SoundSystem;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;
import paulscode.sound.libraries.SourceLWJGLOpenAL;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException; 

public class SEngine {
	public static SoundSystem s=null;
	
	public static void init(){
		if(s==null)	s=new SoundSystem();
		try
		{
		SoundSystemConfig.addLibrary( LibraryLWJGLOpenAL.class );
		}
		catch( SoundSystemException e )
		{
		System.err.println( "error linking with the LibraryLWJGLOpenAL plug-in" );
		}
		
		
	}
	
	public static void dispose(){s.cleanup();}
	
	
	
	

}
