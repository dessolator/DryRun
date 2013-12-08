package dryrun.game.network;

import java.util.*;
import java.net.*;

import dryrun.game.common.GameObjectValues;



public interface NetFramework {
	
	public void send(GameObjectValues [] p);
	
	public GameObjectValues[] receive();

}
