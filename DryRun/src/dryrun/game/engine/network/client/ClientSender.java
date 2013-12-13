package dryrun.game.engine.network.client;
import java.io.IOException;
import java.net.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.GameStatePacket;


public class ClientSender extends Thread {
	
	private Client myOwner;
	
	
	public ClientSender(Client myOwn) {
		myOwner = myOwn;
	}
	
	public void run() {
		while(!interrupted()) {
			GameStatePacket gsp=new GameStatePacket();
			GameObjectValues[] temp = null;
			
			
			temp = myOwner.getSenderBuffer().pop();
			if(temp!=null){
				for(GameObjectValues v : temp)
					gsp.put(v);
			}
			byte [] sendPacket = GameStatePacket.write(gsp);
			DatagramPacket packet = new DatagramPacket(sendPacket,sendPacket.length,myOwner.serverAddress(),myOwner.getUdpServerPort());
			
			try {
				myOwner.getUDPSocket().send(packet);
			
//			System.out.println("sentsentsent fuck youuuuu");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				sleep(18);
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
