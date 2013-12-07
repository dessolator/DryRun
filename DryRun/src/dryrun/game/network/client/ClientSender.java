package dryrun.game.network.client;
import java.io.IOException;
import java.net.*;

import dryrun.game.network.GameStatePacket;
import dryrun.game.network.client.*;

import dryrun.game.common.GameObjectValues;

public class ClientSender extends Thread {
	
	private Client myOwner;
	
	
	public ClientSender(Client myOwn) {
		myOwner = myOwn;
	}
	
	public void run() {
		while(!interrupted()) {
			GameStatePacket gsp=new GameStatePacket();
			GameObjectValues[] temp = null;
			
			try {
				temp = myOwner.getSenderBuffer().pop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(GameObjectValues v : temp)
				gsp.put(v);
			byte [] sendPacket = GameStatePacket.write(gsp);
			DatagramPacket packet = new DatagramPacket(sendPacket,sendPacket.length,myOwner.serverAddress(),myOwner.serverPort());
			
			try {
				myOwner.getUDPSocket().send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i=0; i<sendPacket.length; i++) {
				sendPacket[i] = 0;
			}
		}
	}
	
	public void obavesti() {
		interrupt();
	}
	
	
}
