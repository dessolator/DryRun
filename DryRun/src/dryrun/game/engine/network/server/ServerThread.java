package dryrun.game.engine.network.server;

import java.io.*;
import java.net.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.network.ConcurrentCircularBuffer;
import dryrun.game.engine.network.GameStatePacket;
import dryrun.game.mechanics.Game;
import dryrun.game.objects.Player;

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
	private TCPThread tcp;
	


	public ServerThread(int i,
						String[] split,
						InetAddress cladr,
						ConcurrentCircularBuffer srvB,
						TCPThread tcp) throws SocketException {
		
		
		mySocket=new DatagramSocket(i);
		
		myRecBuffer=new ConcurrentCircularBuffer();
		mySendBuffer=new ConcurrentCircularBuffer();
		myPlayer=Game.createPlayer(split[1],split[2]);
		
		sender = new ServerSender(this);
		receiver = new ServerReceiver(this);
		ldr = new ServerLoader(srvB,myRecBuffer);
		
		this.tcp = tcp;
	}
	
	//public ConcurrentCircularBuffer getBuffer(){return myBuffer;}
	
	public void startGame(GameStatePacket p){
		byte b[]=GameStatePacket.write(p);
		try {
			tos.write(b);
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
		
	public void start(){sender.start();receiver.start(); ldr.start();} //Starting all threads
	
	
	public void close() throws IOException{tis.close();tos.close();} //Close TCP
	
	public void terminate() throws IOException{
		mySocket.close();
		ldr.interrupt();
		sender.interrupt();
		receiver.interrupt();
		myTcpSocket.close();
		} //Close UDP and interrupt threads
	
	public void send(GameObjectValues[] p) { //Queue data to be sent
		mySendBuffer.push(p);
	}
	
	public void initGame(){
		
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
