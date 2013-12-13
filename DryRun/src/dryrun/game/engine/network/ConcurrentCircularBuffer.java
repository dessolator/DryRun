package dryrun.game.engine.network;



import java.util.concurrent.locks.*;

import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.common.GameObjectValues;

public class ConcurrentCircularBuffer {
	private Lock mutex;
//	private Semaphore bufferEmpty;
	private GameObjectValues[][] data ;
	private int currentHead;
	private int currentTail;
	private GameObjectValues[] data1;
	
	public ConcurrentCircularBuffer() {
//		data = new GameObjectValues[SIZE_OF_BUFFER][MAX_PLAYERS-1];
//		bufferEmpty = new Semaphore(0);
		data1 = new GameObjectValues[5];
		mutex = new ReentrantLock();
		currentHead = 0;
		currentTail = 0;
	}
	
	public void push(GameObjectValues[] gov) {
		mutex.lock();
//		currentHead%=SIZE_OF_BUFFER;
//		data[++currentHead] = gov;
		data1=gov;
		mutex.unlock();
//		bufferEmpty.release();
		
	}
	
	public GameObjectValues[] pop() {
//		try {
//			bufferEmpty.acquire();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		mutex.lock();
//		GameObjectValues [] gov = data[currentHead];
		GameObjectValues [] gov = data1;
		data1=null;
		mutex.unlock();
		return gov;
	}
	
}
