package dryrun.game.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.GameStatePacket;

;

public class ServerReceiver extends Thread {
	private ServerThread myOwner;

	
	public ServerReceiver(ServerThread myOwn) {
		myOwner = myOwn;
	}
	
	public void run() {
		while(!interrupted()) {
			byte [] receiveByteArray = new byte[1500];
			DatagramPacket receivePacket = null;
			try {
				receivePacket = new DatagramPacket(receiveByteArray,receiveByteArray.length);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				myOwner.getUDPSocket().receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			receiveByteArray = receivePacket.getData();
			
			GameStatePacket gsp = GameStatePacket.read(receiveByteArray);
			
			GameObjectValues [] temp = gsp.get();
			myOwner.getReceiveBuffer().push(temp);
			
		}
	}
	
	public void obavesti() {
		myOwner.getUDPSocket().close();
		interrupt();
	}

}
