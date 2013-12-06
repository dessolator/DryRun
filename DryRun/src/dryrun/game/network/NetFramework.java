package dryrun.game.network;

import java.util.*;

public interface NetFramework {
	
	public void send(GameStatePacket gState);
	
	public ArrayList<GameStatePacket> receive();
	

}
