package dryrun.game.engine.network;


import dryrun.game.common.GameObjectValues;



public interface NetFramework {
	
	public void send(GameObjectValues [] p);
	
	public GameObjectValues[] receive();

	public void startGame(GameObjectValues[] p);
	
	public GameObjectValues[] startGame();

}
