package dryrun.game.sound;

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
	public static SoundSystem s=null;
	
	public static void init(){
		if(s==null)	s=new SoundSystem();
		boolean alCompatible = SoundSystem.libraryCompatible(LibraryLWJGLOpenAL.class);
		boolean jSCompatible = SoundSystem.libraryCompatible(LibraryJavaSound.class);
		
		Class libraryType;
		
		if(alCompatible)
			libraryType = LibraryLWJGLOpenAL.class;
		else if (jSCompatible)
			{libraryType = LibraryJavaSound.class;System.err.print("LWJGLOpenAL not Supported on this machine");}
		else {libraryType= Library.class;System.err.print("LWJGLOpenAL and JavaSound not Supported on this machine");}
		
		try
		{
		SoundSystemConfig.addLibrary( libraryType );
		}
		catch( SoundSystemException e )
		{
		System.err.println( "error linking with \"libraryType\" Class" );
		}
		
		try
		{
			SoundSystemConfig.setCodec("ogg", CodecJOgg.class);
			
		}catch( SoundSystemException e ){System.err.println("error linking with the CodecWav plug-in" );} 
		
	}
	
	public static void dispose(){s.cleanup();}
	
	
	
	

}
