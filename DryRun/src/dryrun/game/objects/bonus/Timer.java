package dryrun.game.objects.bonus;

import java.util.ArrayList;




public class Timer {
	private static final int BONUS_DURATION = 10;
	private static ArrayList<Timer> timers;
	long time;//vreme isteka bonusa i nanosekundama
	private Bonus myBonus;//bonus na koji utice timer
	//inicijalizacija liste tajmera
	static{
		timers= new ArrayList<Timer>();
	}

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
	//dohvatamo private static polje liste
	private static ArrayList<Timer> getTimers() {
		return timers;
	}
	
	//izbacivanje timera zadatkog bonusa iz liste
	private static void removeTypedTimer(Bonus bonus) { 
		for (int i=0; i<Timer.getTimers().size();i++) {
			if(Timer.getTimers().get(i).myBonus==bonus){
				Timer.getTimers().remove(i);
				i--;
			}
		}
	
	}

	public Bonus getMyBonus() {
		return myBonus;
	}

	public void setMyBonus(Bonus myBonus) {
		this.myBonus = myBonus;
	}

	
}
