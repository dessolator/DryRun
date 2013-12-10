package dryrun.game.common;

public class BonusThread extends Thread {
	private BonusCreator myBonusCreator;
	
	
	public BonusThread(){myBonusCreator=new BonusCreator();}
	
	public void run(){
		while(!interrupted()){
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				myBonusCreator.check();
			}
		}
	}
	
	public void dispose(){interrupt();}
}
