package dryrun.game.network.server;

import java.io.*;
import java.net.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import dryrun.game.network.ConcurrentCircularBuffer;

public class ServerThread  {
	private DatagramSocket mySocket;
	private Player myPlayer;
	private InetAddress clientAddr;
	
	private ServerSender sender;
	private ServerReceiver receiver;
	private ConcurrentCircularBuffer myRecBuffer;
	private ConcurrentCircularBuffer mySendBuffer;
	private ServerLoader ldr;
	DataInputStream tis;
	DataOutputStream tos;
	


	public ServerThread(int i, String[] split, InetAddress cladr, ConcurrentCircularBuffer srvB, DataInputStream tcpin, DataOutputStream tcpout) throws SocketException {
		mySocket=new DatagramSocket(i);
		ldr = new ServerLoader(srvB,myRecBuffer);
		myRecBuffer=new ConcurrentCircularBuffer();
		mySendBuffer=new ConcurrentCircularBuffer();
		//TODO SET MYPLAYER DATA
	}
	
	//public ConcurrentCircularBuffer getBuffer(){return myBuffer;}
	
	public void start(){sender.start();receiver.start(); ldr.start();}
	
	
	public void close() throws IOException{tis.close();tos.close();}
	
	public void terminate(){mySocket.close();ldr.interrupt(); sender.interrupt(); receiver.interrupt();}
	
	public void send(GameObjectValues[] p) {
		mySendBuffer.push(p);
	}

	/*public GameObjectValues[] receive() {
		try {
			return myRecBuffer.pop();
		} catch (InterruptedException e) {e.printStackTrace();}
		return null;
	}*/

	public ConcurrentCircularBuffer getReceiveBuffer() {return myRecBuffer;}
	
	public ConcurrentCircularBuffer getSendBuffer() {return mySendBuffer;}

	public InetAddress clientAddress() {return clientAddr;}
	
	public DatagramSocket getUDPSocket(){return mySocket;}

}
