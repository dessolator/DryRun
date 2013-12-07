package dryrun.game.objects.bonus;

import java.util.ArrayList;




public class Timer {
	private static final int BONUS_DURATION = 10;
	long time;//vreme isteka bonusa i nanosekundama
	private Bonus myBonus;//bonus na koji utice timer
	//inicijalizacija liste tajmera


	//kostruktor koji uzima 2 parametra 
	public Timer(int seconds,Bonus b){
		this.myBonus=b;//set bonus type to the passed value.
		time=System.nanoTime();//set time to current system time.
		time+=(long)seconds*1000000000;//add the number of seconds to be waited.
	}
	
	//proveri da li je zavrsen timer
	public boolean isPassed(){
		return (System.nanoTime()>time?true:false);
	}

	public Bonus getMyBonus() {
		return myBonus;
	}

	public void setMyBonus(Bonus myBonus) {
		this.myBonus = myBonus;
	}

	
}
