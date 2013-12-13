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
				System.out.println("trying to receive on "+myOwner.getUDPSocket().getPort());
				myOwner.getUDPSocket().receive(receivePacket);
			 	System.out.println("received");
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
			receiveByteArray = receivePacket.getData();
			
			GameStatePacket gsp = GameStatePacket.read(receiveByteArray);
	
			GameObjectValues [] temp = gsp.get();
			for(int i=0; i<temp.length;i++)
				if(temp[i]!=null)System.out.print("name:"+temp[i].getName()+"x:"+temp[i].getCoordX()+"y:"+temp[i].getCoordY());
			myOwner.getReceiveBuffer().push(temp);
			
		}
	}
	
	public void obavesti() {
		myOwner.getUDPSocket().close();
		interrupt();
	}
}
