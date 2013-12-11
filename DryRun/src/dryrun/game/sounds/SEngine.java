package dryrun.game.sounds;

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
	private SoundSystem s=null;
	
	public SoundSystem getSoundSystem(){
		if (s==null) {init();}
		return s;
	}
	
	public void init(){
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
	}
	
	
	
	public void dispose(){s.cleanup();}
	
	
	
	

}
