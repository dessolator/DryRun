package dryrun.game.network;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import dryrun.game.common.GameObjectValues;

public class ConcurrentCircularBuffer {
	private Lock mutex;
	private Semaphore spaceLeft;
	private ArrayList<GameObjectValues> data [];
	private int currentHead;
	private int currentTail;
	
	public ConcurrentCircularBuffer(int p=50) {
		data = new ArrayList<GameObjectValues> [p];
		
	}
}
