package dryrun.game.network.client;

import java.io.IOException;
import java.net.*;
import dryrun.game.network.*;
import dryrun.game.common.*;

public class ClientReceiver extends Thread {
	private Client myOwner;
	private boolean connected = false;
	
	public ClientReceiver(Client myOwn) {
		myOwner = myOwn;
		connected = true;
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
