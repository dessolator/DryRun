package dryrun.game.engine.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.GameStatePacket;

public class ServerSender extends Thread {
	private ServerThreadpool myOwner; //remembering who's my daddy
	
	public ServerSender(ServerThreadpool myOwn){myOwner=myOwn;}
	
	public void run() {
		while(!interrupted()) { //while im not requested to end
			GameStatePacket gsp=new GameStatePacket();
			GameObjectValues[] temp = null;
			
			temp = myOwner.getSendBuffer().pop(); //get the shit im supposed to send
			if (temp!=null) {
				for(GameObjectValues v : temp)
					gsp.put(v); //build a gameStatePacket
			}
			byte [] sendPacket = GameStatePacket.write(gsp); //turn it into a byteArray 
			DatagramPacket packet = new DatagramPacket(sendPacket,sendPacket.length,myOwner.clientAddress(),UDP_GSCL_PORT);
			//Assemble a datagramPacket from it 
			try {
				System.out.println("Sent a packet");
				myOwner.getUDPSocket().send(packet); //Send the packet
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				sleep(200); //sleep for a bit so I don't poison the network
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i=0; i<sendPacket.length; i++) {
				sendPacket[i] = 0;  //reset byte[]
			}
		}
	}
	
	public void obavesti() {
		interrupt(); //stop pls
	}
	

}
