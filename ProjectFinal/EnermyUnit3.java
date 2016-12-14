package ProjectFinal;

import javax.swing.JLabel;

public class EnermyUnit3 extends Unit implements Runnable{
	JLabel elb, aelb, hplb, userHp, attkwp;
	int val;
	int stopx;
	UserUnit user;
	boolean bool=true;
//	int cnt=0;
	
	public EnermyUnit3(JLabel elb, JLabel aelb, JLabel hplb, JLabel userHp, JLabel attkwp, UserUnit user){	
		super();
		this.elb = elb;
		this.aelb = aelb;
		this.hplb = hplb;
		this.userHp = userHp;
		this.attkwp = attkwp;
		this.user = user;
		hp = 120;
		movespeed = 3;
		attkspeed = 10;
		attkrange = 400;
		
	}
	
	@Override
	public void run() {
		while (x < 700 && x >= -100) {
			pauseCheckFlag = checkFlag(Game.pauseFlag);

			if (pauseCheckFlag) {
				x = elb.getX();
				y = elb.getY();

				
				val = Game.cnt;
				Game.elblocation[val][0] = x;
				Game.elblocation[val][1] = y;
				Game.elblocation[val][2] = 3;
				x += movespeed;
				elb.setLocation(x, y);
				hplb.setLocation(x, y - 30);

				try {
					Thread.sleep(6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	void doAtk(UserUnit u){
		int userhp = u.getHp() - 100;
		u.setHp(userhp);
		System.out.println(userhp);
	}
	void doMove(){
//		System.out.println("eu3 move");
		
	}
	void doHit(){
		hp-=40;
		hplb.setSize(hp, 5);
		

	}
	void doStatus(){
		attkspeed = 0;
	}

}
