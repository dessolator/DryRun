package dryrun.game.network.server;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.ConcurrentCircularBuffer;

public class ServerLoader extends Thread { //Loader class for bridging 3 buffer to 1 buffer
	private ConcurrentCircularBuffer srvBuffer;
	private ConcurrentCircularBuffer recBuffer;
	
	
	public ServerLoader(ConcurrentCircularBuffer srvB,ConcurrentCircularBuffer recB) {
		srvBuffer=srvB; recBuffer=recB;
	}

	
	public void run(){
		GameObjectValues arr[]=null;
		try {
			arr=recBuffer.pop();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		srvBuffer.push(arr);
		
	}
	

}
