package ProjectFinal;

import javax.swing.JLabel;


public class Unit extends Thread{
	
	JLabel elb, hplb;

	static boolean pauseCheckFlag = true;
	int x;
	int y;
	int hp;
	int movespeed;
	int attkspeed;
	int attk;
	int attkrange;
	boolean aliveFlag = true;
	public Unit() {
		
//		System.out.println("cnt : "+cnt);
//		cnt++;
		
	}
	
	
	void doAtk(UserUnit u){}
	void doMove(){}
	void doHit(){}
	void doStatus(){}
	
	static synchronized boolean checkFlag(boolean pauseFlag){
		if (pauseFlag == false) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pauseFlag;
	}
}

