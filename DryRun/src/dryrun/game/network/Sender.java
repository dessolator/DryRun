package dryrun.game.network;


public class Sender extends Thread {
	private Client myOwner;
	
	public Sender(Client cli) {
		myOwner = cli;
	}
}
