package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import dryrun.game.common.*;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.*;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.client.Client;
import dryrun.game.network.server.Server;
import dryrun.game.objects.TextureHolder;
import static dryrun.game.engine.LoadTex.*;

import java.util.*;
import java.io.IOException;
import java.net.*;

public class Game {
	private static MainMenu myMainMenu;
	private static GameMenu myGameMenu;
	private static HostMenu myHostMenu;
	private static JoinMenu myLobbyMenu;
	private static boolean terminate=false;	
	private static GameState currentGameState=GameState.MainMenu;
	
	private static List<InetAddress> serverAddresses;
	private static GameStatePacket firstPlayersPositions;
	
	private static Level myLvl;
	public static boolean theEnd = false;
	
static{		
		
	}
	

	public static void startGame(){

		Player p = new Player("Kesler", "Lamburghini", Display.getWidth()/2, Display.getHeight()/2, Display.getWidth()/6, Display.getHeight()/10);

		while((!Display.isCloseRequested())&& !terminate) {
			glClear(GL_COLOR_BUFFER_BIT);
			getCurrentUpdate().update();
			getCurrentDraw().render();
			//gameloop yet to be done 

			p.render();
			p.playerInput();
			

			Display.sync(60);
			Display.update();			
		}		
	}
	
	
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
			case PlayMenu:
				return myGameMenu ;
			case SplashScreen:
				return null;
			default:
				return null;
		}
	}
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


	public static Player createPlayer(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
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
				tex=new TextureHolder(loading1,new Tex(1/8f,0f,2/8f,1f));
				ls.render();
				myMainMenu = new MainMenu();
				tex=new TextureHolder(loading1,new Tex(2/8f,0f,3/8f,1f));
				ls.render();
				myGameMenu = new GameMenu();
				tex=new TextureHolder(loading1,new Tex(3/8f,0f,4/8f,1f));
				ls.render();
				myHostMenu = new HostMenu();
				tex=new TextureHolder(loading1,new Tex(4/8f,0f,5/8f,1f));
				ls.render();
				myLobbyMenu = new JoinMenu();
				tex=new TextureHolder(loading1,new Tex(5/8f,0f,6/8f,1f));
				ls.render();
				//mySettingsMenu=new SettingsMenu();
				myLvl = new Level();
				ls.render();
				theEnd = true;
		// TODO Auto-generated method stub
		
	}

}
