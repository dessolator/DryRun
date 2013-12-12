package dryrun.game.engine.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.GameStatePacket;

;

public class ServerReceiver extends Thread {
	private ServerThread myOwner; //remembering who's my daddy

	
	public ServerReceiver(ServerThread myOwn) {
		myOwner = myOwn;
	}
	
	public void run() {
		while(!interrupted()) { //while im not requested to end
			byte [] receiveByteArray = new byte[1500]; //take a byte array
			DatagramPacket receivePacket = null;
			try {
				receivePacket = new DatagramPacket(receiveByteArray,receiveByteArray.length); //prepare a packet for receiving
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				myOwner.getUDPSocket().receive(receivePacket); //actually receive
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			receiveByteArray = receivePacket.getData(); 
			
			GameStatePacket gsp = GameStatePacket.read(receiveByteArray); //convert bytes to a Packet
			
			GameObjectValues [] temp = gsp.get(); //get data from it
			myOwner.getReceiveBuffer().push(temp); //push the data to the receiveBuffer
			
		}
	}
	
	public void obavesti() { //I wanna shut down method
		myOwner.getUDPSocket().close();
		interrupt();
	}

}
