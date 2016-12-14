package ProjectFinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JPanel implements KeyListener, MouseMotionListener, ActionListener {

	LoginMain Login; // main page(JFrame) 위에 JPanel을 그리기위함
	// 50개 생성될 각 enermy의 x,y좌표를 받아옴([i][1]:x / [i][2]:y / [i][3]:enermy종류)
	public static int[][] elblocation = new int[50][3];
	public static int cnt = 0;
	// heap에 저장되는 enermy 주소값을 저장해 둘 JLabel
	JLabel[] elbobj = new JLabel[50];
	// user의 총hp, 현재hp, point, gold를 출력할 JLabel
	JLabel userHp, point, gold;
	ImageIcon image; // 배경그릴 때 쓰려고 했던 ImageIcon
	ImageIcon imgIcon1, imgIcon2, imgIcon3, imgIcon4, imgIcon5, imgIcon6, imgpoint; // 각 enermy JLabel에 붙일 ImgIcon
	ImageIcon[] weapon = new ImageIcon[3];	// 각enermy가 가진 무기(불꽃같은거) ImageIcon
	JLabel[] lb = new JLabel[3]; // 앞으로 사용할 그림 부착된 enermy
	JLabel[] alb = new JLabel[3]; // 앞으로 사용할 그림 부착된 enermy
	JLabel[] elbattk = new JLabel[3];	// enermy가 공격할 때 쓸 무기가 들어있는 JLabel
	JLabel sight;
	Unit[] eunit = new Unit[50]; // enermy unit으로 부모객체인 Unit을 생성함
	JLabel enermyhp; // enermy의 hp
	int mx, my; // enermy위치 좌표를 위해서 쓸 x,y값
	JButton castle; // 성 버튼 선언
	JLabel arrow; // 화살 버튼 선언
	JButton enermy; // 적 버튼 선언
	static boolean arrowFlag = false;
	static boolean pauseFlag = true;

	ImageIcon castleImg; // 성 이미지
	UserUnit user = new UserUnit();

	// 직선의 방정식에 사용할 변수들 선언
	int x, y, x1, y1, x2, y2;

	boolean hit = false; // 몬스터가 화살에 맞았을 때, 화살이 사라지도록 하기 위한 flag.
	
	JPanel pausePanel;
	
	JButton resume, exit, store;
	
	public Game(LoginMain Login) {

		//////// GUI /////////

		this.Login = Login;
		setLayout(null);
		// 화면에 꽉 차도록 창의 크기 설정
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize(); // 현재 화면의 크기
		int w = (int) d.getWidth(); // 화면의 너비
		int h = (int) d.getHeight(); // 화면의 높이
		Login.setBounds(0, 0, 1000, 600-30); // 창의 크기 설정

		castleImg = new ImageIcon("src/image/castle.png"); // 성의 이미지
		castle = new JButton(castleImg); // 성의 객체 선언
		//castle.setBounds(1100, 200, 206, 306); // castle의 위치와 크기 설정
		castle.setBounds(800, 200, 206, 306); // castle의 위치와 크기 설정

		arrow = new JLabel("src/image/arrow.png"); // 화살 객체 선언

		imgIcon1 = new ImageIcon("src/image/pinkflying.gif");
		imgIcon2 = new ImageIcon("src/image/yellowflying.gif");
		imgIcon3 = new ImageIcon("src/image/grayflying.gif");
		imgIcon4 = new ImageIcon("src/image/attkpinkflying.gif");
		imgIcon5 = new ImageIcon("src/image/attkyellowflying.gif");
		imgIcon6 = new ImageIcon("src/image/attkflying.gif");
		imgpoint = new ImageIcon("src/image/point.png");

		weapon[0] = new ImageIcon("src/image/expl.png");	// enermy 무기1 이미지 url
		weapon[1] = new ImageIcon("src/image/expl.png");	// enermy 무기2 이미지 url
		weapon[2] = new ImageIcon("src/image/expl.png");	// enermy 무기3 이미지 url


		userHp = new JLabel("user Hp");
		point = new JLabel("point");
		gold = new JLabel("gold");
		sight = new JLabel(imgpoint);

		userHp.setBounds(0, 505, 1000, 30);
		point.setBounds(500, 50, 100, 30);
		gold.setBounds(600, 50, 100, 30);
		

		userHp.setBackground(Color.red);
		userHp.setForeground(Color.white);
		userHp.setOpaque(true);
		add(userHp);
		add(point);
		add(gold);
		add(castle); // 성 버튼을 화면에 add
		add(sight);

		setPausePanel();
		
		this.Login.setFocusable(true);
		Login.addMouseMotionListener(this);
		Login.addKeyListener(this);
		setFocusable(true); // 화면에 나타나게
		//////////////////////////////////////////////////////

		Runnable s = new Runnable() {

			@Override
			public void run() {
				while (true) {
					//arrow = new JButton(); // 화살 객체 선언
					arrow = new JLabel(new ImageIcon("src/image/arrow.png")); // 화살 객체 선언
					//arrow.setBounds(1250, 200, 25, 25); // 화살 초기 위치 크기 설정
					arrow.setBounds(800, 300, 50, 14); // 화살 초기 위치 크기 설정
					add(arrow);
					arrow.setOpaque(true);

					x = arrow.getX(); // 화살의 x 좌표 선언
					y = arrow.getY(); // 화살의 y 좌표 선언

					// 화살 버튼 좌표
					x2 = arrow.getX(); // 화살 x 좌표의 초기값
					y2 = arrow.getY(); // 화살 y 좌표의 초기값

					for (int j = 0; j <= 1000; j++) {
						Unit.pauseCheckFlag = Unit.checkFlag(Game.pauseFlag);

						if (Unit.pauseCheckFlag) {
							arrow.setLocation(x, y); // 화살의 위치
							try {
								Thread.sleep(5);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

							// 직선의 방정식
							y = (y2 - y1) * (x - x1) / (x2 - x1) + y1;
							// arrow.setLocation(x, y);
							dosleep();
							x -= 10; // x 좌표 감소

							if (x == 0 || hit == true) { // x가 0이면
								arrow.setLocation(-120, -100); // 화면에서 사라지게
								arrowFlag = false;
								hit = false;
								break;
							}
						}

					}
				}
			}
		};

		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i < 60; i++) {
					
					//// 이미지부착한 Label 생성 ///////
					lb[0] = new JLabel(imgIcon1);
					lb[1] = new JLabel(imgIcon2);
					lb[2] = new JLabel(imgIcon3);
					alb[0] = new JLabel(imgIcon4);
					alb[1] = new JLabel(imgIcon5);
					alb[2] = new JLabel(imgIcon6);
					elbattk[0] = new JLabel(weapon[0]);
					elbattk[1] = new JLabel(weapon[1]);
					elbattk[2] = new JLabel(weapon[2]);
					
					enermyhp = new JLabel("hp");
					lb[0].setBounds(-100, (int) (Math.random() * 300 + 100), 100, 100);
					lb[1].setBounds(-100, (int) (Math.random() * 300 + 100), 100, 100);
					lb[2].setBounds(-100, (int) (Math.random() * 300 + 100), 100, 100);
					alb[0].setBounds(-100, lb[0].getY(), 100, 100);
					alb[1].setBounds(-100, lb[1].getY(), 100, 100);
					alb[2].setBounds(-100, lb[2].getY(), 100, 100);
					elbattk[0].setBounds(-100, lb[0].getY(), 100, 100);
					elbattk[1].setBounds(-100, lb[1].getY(), 100, 100);
					elbattk[2].setBounds(-100, lb[2].getY(), 100, 100);
					

					add(lb[0]);
					add(lb[1]);
					add(lb[2]);
					alb[0].setOpaque(false);
					alb[1].setOpaque(false);
					alb[2].setOpaque(false);
					alb[0].setVisible(false);
					alb[1].setVisible(false);
					alb[2].setVisible(false);
					add(alb[0]);
					add(alb[1]);
					add(alb[2]);
					elbattk[0].setOpaque(false);
					elbattk[1].setOpaque(false);
					elbattk[2].setOpaque(false);
					elbattk[0].setVisible(false);
					elbattk[1].setVisible(false);
					elbattk[2].setVisible(false);
					add(elbattk[0]);
					add(elbattk[1]);
					add(elbattk[2]);

					int val = (int) (Math.random() * 3);
					//System.out.println("val : " + val);

					enermyhp.setBounds(-100, lb[val].getY() - 30, 120, 5);
					enermyhp.setBackground(Color.blue);
					enermyhp.setForeground(Color.white);
					enermyhp.setOpaque(true);
					add(enermyhp);

					// Unit u = null;
					eunit[i] = null;
					if (val == 0)
						eunit[i] = new EnermyUnit1(lb[val], alb[val], enermyhp, userHp, elbattk[val], user);
						//System.out.println(val+" "+Game.elblocation[val][0] +" " + Game.elblocation[val][1]);
					else if (val == 1)
						eunit[i] = new EnermyUnit2(lb[val], alb[val], enermyhp, userHp, elbattk[val], user);
					else if (val == 2)
						eunit[i] = new EnermyUnit3(lb[val], alb[val], enermyhp, userHp, elbattk[val], user);

					
					
					elbobj[i] = lb[val];					
				
					Thread th = new Thread(eunit[i]);
					th.start();
					
					try {
						Thread.sleep((int) (Math.random() * 3000 + 2000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/////////////////////////////////////////////////////
					// 공격

					/*if (eunit[i].attkrange == (Login.getWidth() - elblocation[i][1])) {
						eunit[i].doAtk(user);
					}*/
					
					///////// enermy가 user 공격 //////////////
					
					System.out.println(i+" attkrange : "+eunit[i].attkrange); // enermy의 공격범위 받아옴
					System.out.println(i+" move location : "+elblocation[i][0]); // 
					//System.out.println(i+" move range : "+(Login.getWidth()-elblocation[i][1])); // 
					
					//int cx = elblocation[i][0];
					if(elblocation[i][0]<eunit[i].attkrange){
						eunit[i].movespeed = 0;
						//System.out.println("attkspeed : "+eunit[i].attkspeed);						
						elbattk[i%3].setVisible(true);	// 무기 보여주기
						elbattk[i%3].setBounds(lb[val].getX(), (lb[val].getY()), 100, 20);	// 이동하는 무기 위치 조정함
/*						lb[0].setVisible(false);
						lb[1].setVisible(false);
						lb[2].setVisible(false);
						alb[0].setVisible(true);
						alb[1].setVisible(true);
						alb[2].setVisible(true);*/

						int x = elbattk[i%3].getX();	// 무기 x좌표
						int y = elbattk[i%3].getY();	// 무기 y좌표	
						System.out.println(x+" "+y);	

						
						while(elbattk[i%3].getX()<Login.getWidth()){	// 각 무기 x좌표가 화면 끝이 되기 전까지 while
							if (eunit[i].aliveFlag = true) {
								elbattk[i % 3].setLocation(x, y); // 위치를 계속 바꿔줌
								x += 1; // 1pixel씩 증가
							}else{
								elbattk[i%3].setLocation(-120,-100);
							}
							if(x==800){
								System.out.println("hellooooooooooooooooooo");
								eunit[i].doAtk(user);	// enermy의 공격메소드(공격대상)
		                        userHp.setSize(user.getHp(), 30);
		                     }							
							try {
								Thread.sleep(5);	// 0.005씩 sleep
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						cnt++;
					}
					
					
				}
			}
		};
		new Thread(r).start();
		new Thread(s).start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyChar();
		//System.out.println(key);
		//pausePanel.setVisible(true);
		if (key == 27) {
			// Login.change(new Pause(Login));
			Game.pauseFlag = false;
			pausePanel.setVisible(true);
	
			resume.addActionListener(this);
			exit.addActionListener(this);
			store.addActionListener(this);
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// 현재 마우스의 좌표
		if (arrowFlag == false) {
			x1 = e.getX(); // 마우스의 x 좌표
			y1 = e.getY(); // 마우스의 y 좌표
			
			PointerInfo pointerinfo = MouseInfo.getPointerInfo();
			x1 = (int)(pointerinfo.getLocation().getX());
			y1 = (int)(pointerinfo.getLocation().getY());
			arrowFlag = true;
		}
		this.setCursor(this.getToolkit().createCustomCursor( new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));

		
		int x2 = e.getX(); // 마우스의 x 좌표
		int y2 = e.getY(); // 마우스의 y 좌표
		
		sight.setBackground(Color.yellow);
		sight.setOpaque(false);
		sight.setBounds(x2, y2-20, 65, 65);
		//sight.setBounds(x2, y2-20, 65, 65);

		mx = e.getX();
		my = e.getY();
		//System.out.println("마우스 : " + mx + " " + my);

		for (int i = 0; i <= cnt; i++) {
			int px = elblocation[i][0]; // label 꼭지점(좌측상단)
			int py = elblocation[i][1];

			int cx = px + 50; // 라벨 중심점
			int cy = py + 50;

			int r = (int) Math.sqrt(40 * 40 + 40 * 40);
			int range = (int) Math.sqrt((x-cx) * (x-cx) + (y-cy)*(y-cy));
			//System.out.println(x +" " + y);
			
			if (range < r) { // label 범위 내면 해당 라벨을 안 보이게
				if(eunit[i].hp <= 0){
					hit = true;
					eunit[i].aliveFlag = false;
					elbobj[i].setLocation(-120, -100);
				}else if(elblocation[i][0] >= 600){
					elbobj[i].setLocation(-120, -100);
				}else{
					hit = true;
					eunit[i].doHit();	
				}
			}
		}
	}

	void dosleep() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	void setPausePanel() {
		pausePanel = new JPanel();

		pausePanel.setLayout(null);
		pausePanel.setBounds(300, 150, 300, 190);
		pausePanel.setBackground(Color.white);
		resume = new JButton("resume");
		exit = new JButton("exit");
		store = new JButton("store");

		resume.setBounds(100, 10, 100, 50);
		exit.setBounds(100, 10 + (50 + 10), 100, 50);
		store.setBounds(100, 10 + (50 + 10) + (50 + 10), 100, 50);

		pausePanel.add(resume);
		pausePanel.add(exit);
		pausePanel.add(store);

		add(pausePanel);
		pausePanel.add(new Picture());
		pausePanel.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == resume) {
			Game.pauseFlag = true;
			pausePanel.setVisible(false);
		} else if (obj == exit) {
			Login.change(new Start(Login));
		} else if (obj == store) {
			Login.change(new StoreShop(Login));
		}
	}
}
