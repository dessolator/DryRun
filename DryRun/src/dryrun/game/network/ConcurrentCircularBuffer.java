package dryrun.game.network;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static dryrun.game.network.NetConstants.*;
import dryrun.game.common.GameObjectValues;

public class ConcurrentCircularBuffer {
	private Lock mutex;
	private Semaphore bufferEmpty;
	private GameObjectValues[][] data ;
	private int currentHead;
	private int currentTail;
	
	public ConcurrentCircularBuffer(int p) {
		data = new GameObjectValues[SIZE_OF_BUFFER][MAX_PLAYERS-1];
		bufferEmpty = new Semaphore(0);
		mutex = new ReentrantLock();
		currentHead = 0;
		currentTail = 0;
	}
	
	public void push(GameObjectValues[] gov) {
		mutex.lock();
		data[(currentHead++)%SIZE_OF_BUFFER] = gov;
		mutex.unlock();
		bufferEmpty.release();
		
	}
	
	public GameObjectValues[] pop() throws InterruptedException {
		bufferEmpty.acquire();
		mutex.lock();
		GameObjectValues [] gov = data[(currentTail++)%SIZE_OF_BUFFER];
		mutex.unlock();
		return gov;
	}
	
}
