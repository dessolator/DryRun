package dryrun.game.network.server;

import static dryrun.game.network.NetConstants.FIND_SERVER;
import static dryrun.game.network.NetConstants.FIND_SERVER_R;

import java.io.IOException;
import java.net.*;

public class RefreshReplyThread extends Thread {
	private DatagramSocket myUdpSocket;
	
	
	public RefreshReplyThread(DatagramSocket UdpSocket){myUdpSocket=UdpSocket;} //this is Where I listen
	
	
	public void run(){
		byte x[] = new byte [100];
		while(!interrupted()){ //while im not requested to stop
			DatagramPacket receive = new DatagramPacket(x, x.length); 
			try {
				myUdpSocket.receive(receive);
			} catch (IOException e) {e.printStackTrace(); break;}
			String s = new String(receive.getData()).trim();
			if(s.equals(FIND_SERVER)){//TODO string.equals
				s=FIND_SERVER_R;
				x=s.getBytes();
				DatagramPacket reply = new DatagramPacket(x, x.length, receive.getAddress(), receive.getPort());
				try {
					myUdpSocket.send(reply);
				} catch (IOException e) { e.printStackTrace(); break;}
			}
		}
		
	}
	
	
	public void obavesti(){
		myUdpSocket.close();
		interrupt();
	}
	
	

}
