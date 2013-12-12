package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import dryrun.game.common.GameState;
import dryrun.game.common.cars.bmwM5;
import dryrun.game.engine.Tex;
import dryrun.game.engine.TextureHolder;
import dryrun.game.engine.interfaces.Drawable;
import dryrun.game.engine.interfaces.Updateable;
import dryrun.game.engine.network.GameStatePacket;
import dryrun.game.engine.network.client.Client;
import dryrun.game.engine.network.server.Server;
import dryrun.game.gui.menus.GameMenu;
import dryrun.game.gui.menus.HostMenu;
import dryrun.game.gui.menus.JoinMenu;
import dryrun.game.gui.menus.MainMenu;
import static dryrun.game.engine.LoadTex.tex;
import static dryrun.game.engine.LoadTex.loading1;
import static dryrun.game.engine.LoadTex.ls;

import java.util.*;
import java.io.IOException;
import java.net.*;

import dryrun.game.gui.menus.*;
import dryrun.game.objects.Player;

//here all the magic happens :) 
public class Game {
	private static MainMenu myMainMenu;//my main menu
	private static GameMenu myGameMenu;//my game menu
	private static HostMenu myHostMenu;//my host menu
	private static JoinMenu myLobbyMenu;//my join menu
	private static WaitServerMenu waitServerReply;
	private static boolean terminate=false;	//window kill flag
	private static GameState currentGameState=GameState.MainMenu;//current game state
	private static List<InetAddress> serverAddresses;//list of known server addresses
	private static GameStatePacket firstPlayersPositions;//TODO someone else added this I do not understand it @Ivan
	private static Level myLvl;//myLevel
	
	
	public static void initState(){
		myLvl.initialState();
		currentGameState=GameState.Game;
	}
	
	
	
	
	
	//main game loop
	public static void startGame(){
		Player p = new Player("Kesler",
				new bmwM5(),
				Display.getWidth()/2,
				Display.getHeight()/2);//@Vuk Test
		myLvl.setMyPlayer(p);
		
		
		
		Player d = new Player("Ksler", 
				new bmwM5(),
				Display.getWidth()/2,
				Display.getHeight()/2-150);//@Vuk Test
		System.out.println("quickplay");
		
		//SEngine.getSoundSystem().quickStream(false, "b.ogg", false, p.getX(), p.getY(), 0, SoundSystemConfig.ATTENUATION_LINEAR, 1000f);
				
		//d.myBody.m_type=BodyType.STATIC;//@Vuk Test
		while((!Display.isCloseRequested())&& !terminate) {
			glClear(GL_COLOR_BUFFER_BIT);//clear the screen
			getCurrentUpdate().update();//update what needs to be updated
			getCurrentDraw().render();//render what needs to be rendered
			//gameloop yet to be done 
//			p.update();
//			d.update();
//			p.render();//@Vuk Test
//			d.render();//@Vuk Test
//			p.playerInput();//@Vuk Test
		//	System.out.println("P is at:"+ p.getX()+","+p.getY());
		//	System.out.println("D is at:"+ d.getX()+","+d.getY());
//			SEngine.getSoundSystem().setListenerPosition( p.getX(), p.getY(), -100 ); //@Nikola Sound Test
			Display.sync(60);//limit fps to 60
			Display.update();//draw the GLContext
		}		
	}
	
	//get current gemeState ... 	
	private static Updateable getCurrentUpdate() {
		switch(currentGameState){
			case MainMenu:
				return myMainMenu;
			case Game:
				return myLvl;
			case HostGameScreen:
				return myHostMenu;
			case HostJoinMenu:
				return null;
			case JoinGame:
				return null;
			case LobbyScreen:
				return myLobbyMenu;
			case WaitServerReply:
				return waitServerReply;
			case PlayMenu:
				return myGameMenu ;
			case SplashScreen:
				return null;
			default:
				return null;
		}
	}
	
	//return to previous game state
	public static void goBack(){
		switch(currentGameState){
			case MainMenu:
				currentGameState= GameState.MainMenu;
				break;
			case Game:
				currentGameState= GameState.Game;
				break;
			case HostGameScreen:
				currentGameState= GameState.PlayMenu;
			try {
				Server.getServer().terminate();
			} catch (IOException e) {
				e.printStackTrace();
			}
				Server.disposeServer();
				break;
			case HostJoinMenu:
				currentGameState= GameState.PlayMenu;
				break;
			case JoinGame:
				currentGameState= GameState.HostJoinMenu;
				break;
			case LobbyScreen:
				currentGameState= GameState.PlayMenu;
				Client.disposeClient();
				myLobbyMenu.setServerFrame(null);
				Game.getMyLobbyMenu().deleteServerButtons();
				break;
			case PlayMenu:
				currentGameState= GameState.MainMenu;
				break;
			case SplashScreen:
				currentGameState= GameState.SplashScreen;
				break;
			default:
				currentGameState= GameState.MainMenu;
				break;
}
}

	private static Drawable getCurrentDraw() {
		switch(currentGameState){
		case MainMenu:
			return myMainMenu;
		case Game:
			return myLvl;
		case HostGameScreen:
			return myHostMenu;
		case HostJoinMenu:
			return null;
		case JoinGame:
			return null;
		case LobbyScreen:
			return myLobbyMenu;
		case PlayMenu:
			return myGameMenu;
		case SplashScreen:
			return null;
		case WaitServerReply:
			return waitServerReply;
		default:
			return null;
	}
	}
	public static void setTerminate(boolean b) {
		terminate = true;
	}
	
	public static boolean isTerminate() {
		return terminate;
	}

	public static GameState getCurrentGameState() {
		return currentGameState;
	}

	public static void setCurrentGameState(GameState currentGameState) {
		Game.currentGameState = currentGameState;
	}

	
	public static List<InetAddress> getPossibleServers() {
		return serverAddresses;
	}




	public static JoinMenu getMyLobbyMenu() {
		return myLobbyMenu;
	}


	public static void setMyLobbyMenu(JoinMenu myLobbyMenu) {
		Game.myLobbyMenu = myLobbyMenu;
	}
	
	public static Level getMyLevel(){
		return myLvl;
	}


	public static void createPlayer(String name) {
		myLvl.players.add(new Player(name, new bmwM5(), Display.getWidth()/2,Display.getHeight()/2));
	}


	


	public static GameStatePacket getFirstPlayersPositions() {
		return firstPlayersPositions;
	}


	public static void setFirstPlayersPositions(GameStatePacket firstPlayPos) {
		firstPlayersPositions = firstPlayPos;
	}


	public static void initGame() {
				//myLevel=new Level(currentLevel);
				serverAddresses = Collections.synchronizedList(new ArrayList<InetAddress>());
				tex=new TextureHolder(loading1,new Tex(0f,0f,0f,0f));
				ls.render();
				myMainMenu = new MainMenu();

				ls.render();
				myGameMenu = new GameMenu();
				ls.render();
				waitServerReply = new WaitServerMenu();

				ls.render();
				myHostMenu = new HostMenu();

				ls.render();
				myLobbyMenu = new JoinMenu();

				ls.render();
				//mySettingsMenu=new SettingsMenu();
				//myLvl = new Level();
				ls.render();
		// TODO Auto-generated method stub
		
	}

}
