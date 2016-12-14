package ProjectFinal;

import javax.swing.JLabel;

public class UserUnit extends Unit{

	JLabel userHp;
	private int hp;
	private int movespeed;
	private int attkspeed;
	private int attk;
	private int attkrange;
	private int gold;

	public UserUnit(){
		hp = 1000;
		movespeed = 0;
		attkspeed = 5;
		attk = 100;
		gold = 0;
		System.out.println("hp의 기본 생성자의 기본값 : "+hp);
	}
	
	public UserUnit(JLabel userHp){
		super();
		System.out.println("hp의 기본값 : "+hp);
		this.userHp = userHp;	
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
//		hp = 1111;
		//System.out.println("hp : "+hp);
//		userHp.setSize(hp, 30);
	}

	public int getAttk() {
		return attk;
	}

	public void setAttk(int attk) {
		this.attk = attk;
		System.out.println("attk : "+attk);
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
		System.out.println("gold : "+gold);
	}


	
/*	public UserUnit(int hp , int attk , int gold){
		this.hp = hp;
		this.attk = attk;
		this.gold = gold;
	}*/
	
	public void doAtk(Unit u){
		u.hp-=1;
	}
	public void doHit(){
		
	}
	
}
