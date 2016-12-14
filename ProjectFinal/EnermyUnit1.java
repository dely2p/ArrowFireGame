package ProjectFinal;

import javax.swing.JLabel;

public class EnermyUnit1 extends Unit implements Runnable{
//	int cnt=0;
	JLabel elb, aelb, hplb, userHp, attkwp;
	int val;
	int stopx;
	UserUnit user;
	boolean bool=true;
	
	public EnermyUnit1(JLabel elb, JLabel aelb, JLabel hplb, JLabel userHp, JLabel attkwp, UserUnit user){	
		super();
		this.elb = elb;
		this.aelb = aelb;
		this.hplb = hplb;
		this.userHp = userHp;
		this.attkwp = attkwp;
		this.user = user;
		hp = 120;
		stopx = 0;
		movespeed = 1;
		attkspeed = 10;
		attkrange = 200;
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
				Game.elblocation[val][2] = 1;
				// System.out.println(val+" "+Game.elblocation[val][0] +" " +	 Game.elblocation[val][1]);
				x += movespeed;
				elb.setLocation(x, y);
				hplb.setLocation(x, y - 30);



				// System.out.println("eu1["+cnt+"] : "+x+" "+y);
				try {
					Thread.sleep(7);
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
		
/*		int hp = u.getHp()-5;
		System.out.println("======================");
		
		System.out.println(u.getHp());
		u.setHp(hp);*/
		
	}
	public void doHit(){
		hp-=120;
		hplb.setSize(hp, 5);
	}
	void doStatus(){
		movespeed = 0;
		System.out.println(attkspeed);
	}
}
