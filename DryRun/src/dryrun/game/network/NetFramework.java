package dryrun.game.network;


import dryrun.game.common.GameObjectValues;



public interface NetFramework {
	
	public void send(GameObjectValues [] p);
	
	public GameObjectValues[] receive();

	public void startGame(GameObjectValues[] p);

}
