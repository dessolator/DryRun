package dryrun.game.engine.network.client;

public class DestroyGetServersThread extends Thread {
	private GetServers thread;
	
	public DestroyGetServersThread (GetServers t) {
		thread = t;
	}
	
	public void run() {
		long currentTime = System.nanoTime();
		while (System.nanoTime()-currentTime < 3000000000l) {}
		thread.obavesti();
		thread.interrupt();
	}
}
