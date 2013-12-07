package dryrun.game.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import static dryrun.game.network.NetConstants.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.GameStatePacket;

public class ServerSender extends Thread {
	private ServerThread myOwner;
	
	public ServerSender(ServerThread myOwn){myOwner=myOwn;}
	
	public void run() {
		while(!interrupted()) {
			GameStatePacket gsp=new GameStatePacket();
			GameObjectValues[] temp = null;
			
			try {
				temp = myOwner.getSendBuffer().pop();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(GameObjectValues v : temp)
				gsp.put(v);
			byte [] sendPacket = GameStatePacket.write(gsp);
			DatagramPacket packet = new DatagramPacket(sendPacket,sendPacket.length,myOwner.clientAddress(),UDP_GSCL_PORT);
			
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
