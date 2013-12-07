package dryrun.game.network;

import java.util.*;

public interface NetFramework {
	
	public void send(Packet p);
	
	public ArrayList<GameStatePacket> receive();
	

}
