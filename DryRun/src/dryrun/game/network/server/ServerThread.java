package dryrun.game.network.server;

import java.io.*;
import java.net.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import dryrun.game.network.ConcurrentCircularBuffer;

public class ServerThread  { //Client instance on the server
	private DatagramSocket mySocket; //I send my UDP data to the client here
	private Player myPlayer;		//this is my clients player info (will be used by TCP?)
	private InetAddress clientAddr; //My clients address
	private Socket myTcpSocket;
	
	private ServerSender sender;	//This little guy creates a thread which does all the sending by puting data in the sendBuffer.
	private ServerReceiver receiver;//And this little guy creates a thread which does all the receiving by picking data from the recBuffer.
	private ConcurrentCircularBuffer myRecBuffer;//buffer self-fucking-explanatory
	private ConcurrentCircularBuffer mySendBuffer;//buffer self-fucking-explanatory
	private ServerLoader ldr; //A loader which takes data from the recBuffer and puts it in the ServerBuffer
	DataInputStream tis; //TCP In-Stream
	DataOutputStream tos;//TCP Out-Stream
	


	public ServerThread(int i, String[] split, InetAddress cladr, ConcurrentCircularBuffer srvB, DataInputStream tcpin, DataOutputStream tcpout, Socket s) throws SocketException {
		mySocket=new DatagramSocket(i);
		ldr = new ServerLoader(srvB,myRecBuffer);
		myRecBuffer=new ConcurrentCircularBuffer();
		mySendBuffer=new ConcurrentCircularBuffer();
		myTcpSocket = s;
		//TODO SET MYPLAYER DATA
	}
	
	//public ConcurrentCircularBuffer getBuffer(){return myBuffer;}
	
	public void start(){sender.start();receiver.start(); ldr.start();} //Starting all threads
	
	
	public void close() throws IOException{tis.close();tos.close();} //Close TCP
	
	public void terminate() throws IOException{
		mySocket.close();
		ldr.interrupt();
		//sender.interrupt();
		//receiver.interrupt();
		myTcpSocket.close();
		} //Close UDP and interrupt threads
	
	public void send(GameObjectValues[] p) { //Queue data to be sent
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
