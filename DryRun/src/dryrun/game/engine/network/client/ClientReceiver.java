package dryrun.game.engine.network.client;

import java.io.IOException;
import java.net.*;

import dryrun.game.common.*;
import dryrun.game.engine.network.*;
import dryrun.game.objects.Player;

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
	//			System.out.println("trying to receive on "+myOwner.getUDPSocket().getPort());
				try {
					sleep(18);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				myOwner.getUDPSocket().receive(receivePacket);
				if(Player.printUDP.get())
					System.out.println("received");
				
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
			receiveByteArray = receivePacket.getData();
			
			GameStatePacket gsp = GameStatePacket.read(receiveByteArray);
	
			GameObjectValues [] temp = gsp.get();
			//if(temp[0]==null)System.out.println("null");
			if(Player.printClientReceive.get()&&temp[0]!=null&&temp[1]!=null){
				System.out.println(""+temp[0].getName()+" : "+temp[0].getCoordX()+" : "+temp[0].getCoordY());
				System.out.println(""+temp[1].getName()+" : "+temp[1].getCoordX()+" : "+temp[1].getCoordY());
			}
//			for(int i=0; i<temp.length;i++)
//				if(temp[i]!=null)System.out.print("name:"+temp[i].getName()+"x:"+temp[i].getCoordX()+"y:"+temp[i].getCoordY());
			myOwner.getReceiveBuffer().push(temp);
			
		}
	}
	
	public void obavesti() {
		myOwner.getUDPSocket().close();
		interrupt();
	}
}
