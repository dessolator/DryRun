package dryrun.game.engine.network.server;

import java.io.IOException;
import java.net.DatagramPacket;

import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.GameStatePacket;
import dryrun.game.objects.Player;

;

public class ServerReceiver extends Thread {
	private ServerThreadpool myOwner; //remembering who's my daddy

	
	public ServerReceiver(ServerThreadpool myOwn) {
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
				sleep(20);
				myOwner.getUDPSocket().receive(receivePacket); //actually receive
				if(Player.printUDP.get())
					System.out.println("received");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			receiveByteArray = receivePacket.getData(); 
			
			GameStatePacket gsp = GameStatePacket.read(receiveByteArray); //convert bytes to a Packet
			
			GameObjectValues [] temp = gsp.get(); //get data from it
			if(Player.printServerReceive.get()){
				if(temp[0]!=null)System.out.println(""+temp[0].getName()+" : "+temp[0].getCoordX()+" : "+temp[0].getCoordY());
				if(temp[1]!=null)System.out.println(""+temp[1].getName()+" : "+temp[1].getCoordX()+" : "+temp[1].getCoordY());
			}
			
//			if(temp[0]!=null)
//				System.out.println(temp[0].getName()+temp[0].getCoordX()+"  "+temp[0].getCoordY());
//			else
//				System.out.println("moo");
			myOwner.getReceiveBuffer().push(temp); //push the data to the receiveBuffer
			if(Player.printServerState.get()){
				System.out.println("pushed to buffer");
				}
			
		}
	}
	
	public void obavesti() { //I wanna shut down method
		myOwner.getUDPSocket().close();
		interrupt();
	}

}
