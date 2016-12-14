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

	LoginMain Login; // main page(JFrame) ���� JPanel�� �׸�������
	// 50�� ������ �� enermy�� x,y��ǥ�� �޾ƿ�([i][1]:x / [i][2]:y / [i][3]:enermy����)
	public static int[][] elblocation = new int[50][3];
	public static int cnt = 0;
	// heap�� ����Ǵ� enermy �ּҰ��� ������ �� JLabel
	JLabel[] elbobj = new JLabel[50];
	// user�� ��hp, ����hp, point, gold�� ����� JLabel
	JLabel userHp, point, gold;
	ImageIcon image; // ���׸� �� ������ �ߴ� ImageIcon
	ImageIcon imgIcon1, imgIcon2, imgIcon3, imgIcon4, imgIcon5, imgIcon6, imgpoint; // �� enermy JLabel�� ���� ImgIcon
	ImageIcon[] weapon = new ImageIcon[3];	// ��enermy�� ���� ����(�Ҳɰ�����) ImageIcon
	JLabel[] lb = new JLabel[3]; // ������ ����� �׸� ������ enermy
	JLabel[] alb = new JLabel[3]; // ������ ����� �׸� ������ enermy
	JLabel[] elbattk = new JLabel[3];	// enermy�� ������ �� �� ���Ⱑ ����ִ� JLabel
	JLabel sight;
	Unit[] eunit = new Unit[50]; // enermy unit���� �θ�ü�� Unit�� ������
	JLabel enermyhp; // enermy�� hp
	int mx, my; // enermy��ġ ��ǥ�� ���ؼ� �� x,y��
	JButton castle; // �� ��ư ����
	JLabel arrow; // ȭ�� ��ư ����
	JButton enermy; // �� ��ư ����
	static boolean arrowFlag = false;
	static boolean pauseFlag = true;

	ImageIcon castleImg; // �� �̹���
	UserUnit user = new UserUnit();

	// ������ �����Ŀ� ����� ������ ����
	int x, y, x1, y1, x2, y2;

	boolean hit = false; // ���Ͱ� ȭ�쿡 �¾��� ��, ȭ���� ��������� �ϱ� ���� flag.
	
	JPanel pausePanel;
	
	JButton resume, exit, store;
	
	public Game(LoginMain Login) {

		//////// GUI /////////

		this.Login = Login;
		setLayout(null);
		// ȭ�鿡 �� ������ â�� ũ�� ����
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize(); // ���� ȭ���� ũ��
		int w = (int) d.getWidth(); // ȭ���� �ʺ�
		int h = (int) d.getHeight(); // ȭ���� ����
		Login.setBounds(0, 0, 1000, 600-30); // â�� ũ�� ����

		castleImg = new ImageIcon("src/image/castle.png"); // ���� �̹���
		castle = new JButton(castleImg); // ���� ��ü ����
		//castle.setBounds(1100, 200, 206, 306); // castle�� ��ġ�� ũ�� ����
		castle.setBounds(800, 200, 206, 306); // castle�� ��ġ�� ũ�� ����

		arrow = new JLabel("src/image/arrow.png"); // ȭ�� ��ü ����

		imgIcon1 = new ImageIcon("src/image/pinkflying.gif");
		imgIcon2 = new ImageIcon("src/image/yellowflying.gif");
		imgIcon3 = new ImageIcon("src/image/grayflying.gif");
		imgIcon4 = new ImageIcon("src/image/attkpinkflying.gif");
		imgIcon5 = new ImageIcon("src/image/attkyellowflying.gif");
		imgIcon6 = new ImageIcon("src/image/attkflying.gif");
		imgpoint = new ImageIcon("src/image/point.png");

		weapon[0] = new ImageIcon("src/image/expl.png");	// enermy ����1 �̹��� url
		weapon[1] = new ImageIcon("src/image/expl.png");	// enermy ����2 �̹��� url
		weapon[2] = new ImageIcon("src/image/expl.png");	// enermy ����3 �̹��� url


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
		add(castle); // �� ��ư�� ȭ�鿡 add
		add(sight);

		setPausePanel();
		
		this.Login.setFocusable(true);
		Login.addMouseMotionListener(this);
		Login.addKeyListener(this);
		setFocusable(true); // ȭ�鿡 ��Ÿ����
		//////////////////////////////////////////////////////

		Runnable s = new Runnable() {

			@Override
			public void run() {
				while (true) {
					//arrow = new JButton(); // ȭ�� ��ü ����
					arrow = new JLabel(new ImageIcon("src/image/arrow.png")); // ȭ�� ��ü ����
					//arrow.setBounds(1250, 200, 25, 25); // ȭ�� �ʱ� ��ġ ũ�� ����
					arrow.setBounds(800, 300, 50, 14); // ȭ�� �ʱ� ��ġ ũ�� ����
					add(arrow);
					arrow.setOpaque(true);

					x = arrow.getX(); // ȭ���� x ��ǥ ����
					y = arrow.getY(); // ȭ���� y ��ǥ ����

					// ȭ�� ��ư ��ǥ
					x2 = arrow.getX(); // ȭ�� x ��ǥ�� �ʱⰪ
					y2 = arrow.getY(); // ȭ�� y ��ǥ�� �ʱⰪ

					for (int j = 0; j <= 1000; j++) {
						Unit.pauseCheckFlag = Unit.checkFlag(Game.pauseFlag);

						if (Unit.pauseCheckFlag) {
							arrow.setLocation(x, y); // ȭ���� ��ġ
							try {
								Thread.sleep(5);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

							// ������ ������
							y = (y2 - y1) * (x - x1) / (x2 - x1) + y1;
							// arrow.setLocation(x, y);
							dosleep();
							x -= 10; // x ��ǥ ����

							if (x == 0 || hit == true) { // x�� 0�̸�
								arrow.setLocation(-120, -100); // ȭ�鿡�� �������
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
					
					//// �̹��������� Label ���� ///////
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
					// ����

					/*if (eunit[i].attkrange == (Login.getWidth() - elblocation[i][1])) {
						eunit[i].doAtk(user);
					}*/
					
					///////// enermy�� user ���� //////////////
					
					System.out.println(i+" attkrange : "+eunit[i].attkrange); // enermy�� ���ݹ��� �޾ƿ�
					System.out.println(i+" move location : "+elblocation[i][0]); // 
					//System.out.println(i+" move range : "+(Login.getWidth()-elblocation[i][1])); // 
					
					//int cx = elblocation[i][0];
					if(elblocation[i][0]<eunit[i].attkrange){
						eunit[i].movespeed = 0;
						//System.out.println("attkspeed : "+eunit[i].attkspeed);						
						elbattk[i%3].setVisible(true);	// ���� �����ֱ�
						elbattk[i%3].setBounds(lb[val].getX(), (lb[val].getY()), 100, 20);	// �̵��ϴ� ���� ��ġ ������
/*						lb[0].setVisible(false);
						lb[1].setVisible(false);
						lb[2].setVisible(false);
						alb[0].setVisible(true);
						alb[1].setVisible(true);
						alb[2].setVisible(true);*/

						int x = elbattk[i%3].getX();	// ���� x��ǥ
						int y = elbattk[i%3].getY();	// ���� y��ǥ	
						System.out.println(x+" "+y);	

						
						while(elbattk[i%3].getX()<Login.getWidth()){	// �� ���� x��ǥ�� ȭ�� ���� �Ǳ� ������ while
							if (eunit[i].aliveFlag = true) {
								elbattk[i % 3].setLocation(x, y); // ��ġ�� ��� �ٲ���
								x += 1; // 1pixel�� ����
							}else{
								elbattk[i%3].setLocation(-120,-100);
							}
							if(x==800){
								System.out.println("hellooooooooooooooooooo");
								eunit[i].doAtk(user);	// enermy�� ���ݸ޼ҵ�(���ݴ��)
		                        userHp.setSize(user.getHp(), 30);
		                     }							
							try {
								Thread.sleep(5);	// 0.005�� sleep
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
		// ���� ���콺�� ��ǥ
		if (arrowFlag == false) {
			x1 = e.getX(); // ���콺�� x ��ǥ
			y1 = e.getY(); // ���콺�� y ��ǥ
			
			PointerInfo pointerinfo = MouseInfo.getPointerInfo();
			x1 = (int)(pointerinfo.getLocation().getX());
			y1 = (int)(pointerinfo.getLocation().getY());
			arrowFlag = true;
		}
		this.setCursor(this.getToolkit().createCustomCursor( new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));

		
		int x2 = e.getX(); // ���콺�� x ��ǥ
		int y2 = e.getY(); // ���콺�� y ��ǥ
		
		sight.setBackground(Color.yellow);
		sight.setOpaque(false);
		sight.setBounds(x2, y2-20, 65, 65);
		//sight.setBounds(x2, y2-20, 65, 65);

		mx = e.getX();
		my = e.getY();
		//System.out.println("���콺 : " + mx + " " + my);

		for (int i = 0; i <= cnt; i++) {
			int px = elblocation[i][0]; // label ������(�������)
			int py = elblocation[i][1];

			int cx = px + 50; // �� �߽���
			int cy = py + 50;

			int r = (int) Math.sqrt(40 * 40 + 40 * 40);
			int range = (int) Math.sqrt((x-cx) * (x-cx) + (y-cy)*(y-cy));
			//System.out.println(x +" " + y);
			
			if (range < r) { // label ���� ���� �ش� ���� �� ���̰�
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
