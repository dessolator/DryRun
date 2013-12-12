package dryrun.game.engine.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.GameStatePacket;

public class ServerSender extends Thread {
	private ServerThread myOwner; //remembering who's my daddy
	
	public ServerSender(ServerThread myOwn){myOwner=myOwn;}
	
	public void run() {
		while(!interrupted()) { //while im not requested to end
			GameStatePacket gsp=new GameStatePacket();
			GameObjectValues[] temp = null;
			
			try {
				temp = myOwner.getSendBuffer().pop(); //get the shit im supposed to send
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(GameObjectValues v : temp)
				gsp.put(v); //build a gameStatePacket
			byte [] sendPacket = GameStatePacket.write(gsp); //turn it into a byteArray 
			DatagramPacket packet = new DatagramPacket(sendPacket,sendPacket.length,myOwner.clientAddress(),UDP_GSCL_PORT);
			//Assemble a datagramPacket from it 
			try {
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
