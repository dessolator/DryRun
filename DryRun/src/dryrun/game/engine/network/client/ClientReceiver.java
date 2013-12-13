package dryrun.game.engine.network.client;

import java.io.IOException;
import java.net.*;

import dryrun.game.common.*;
import dryrun.game.engine.network.*;

public class ClientReceiver extends Thread {
	private Client myOwner;

	
	public ClientReceiver(Client myOwn) {
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
				System.out.println("primio");
			} catch (IOException e) {
				e.printStackTrace();
				continue;
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
