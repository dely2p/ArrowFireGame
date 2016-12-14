package ProjectFinal;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StoreShop extends JPanel implements ActionListener {

	LoginMain login;
	JButton atkup, hpup, apply, cancel;
	JLabel nstatus, natk, nhp, ngold, nupg;
	JLabel plus1, plus2, minus;
	JLabel background;
	JTextField natkv, nhpv, ngoldv;
	JTextField atkplus , hpplus , goldminus;
	Font f1;
	Container panel;
	private BufferedImage image;
	ImageIcon img;
	
	UserUnit unit = new UserUnit();
	public StoreShop(LoginMain login) {
		// super("상점");
		setLayout(null);
		// this.main = main;
		this.login = login;
		
		setLayout(null);	// 화면에 꽉 차도록 창의 크기 설정	

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension ds = tool.getScreenSize();
		
		int x = (int) ds.getWidth() / 2 - 300;
		int y = (int) ds.getHeight() / 2 - 250;

		login.setBounds(x, y, 600, 500);
		
		
		f1 = new Font("돋움", Font.PLAIN, 30);
		atkup = new JButton("공격력 Up");
		hpup = new JButton("체력 Up");

		nstatus = new JLabel("STATUS");
		nstatus.setFont(f1);
		natk = new JLabel("현재공격력");
		nhp = new JLabel("현재체력");
		ngold = new JLabel("현재골드");
		nupg = new JLabel("UPGRADE");
		nupg.setFont(f1);

		natkv = new JTextField("100");
		nhpv = new JTextField("500");
		ngoldv = new JTextField("1000");

		apply = new JButton("적용");
		cancel = new JButton("취소");
		
		plus1 = new JLabel("+");
		plus2 = new JLabel("+");
		minus = new JLabel("-");
		
		atkplus = new JTextField("0");
		hpplus = new JTextField("0");
		goldminus = new JTextField("0");

		atkup.setBounds(380, 110, 150, 100);
		hpup.setBounds(380, 220, 150, 100);
		nupg.setBounds(380, 10, 150, 100);
		nstatus.setBounds(50, 10, 150, 100);
		natk.setBounds(30, 120, 80, 50);
		nhp.setBounds(30, 190, 80, 50);
		ngold.setBounds(30, 260, 80, 50);
		natkv.setBounds(110, 120, 60, 50);
		nhpv.setBounds(110, 190, 60, 50);
		ngoldv.setBounds(110, 260, 60, 50);
		apply.setBounds(140, 380, 120, 50);
		cancel.setBounds(310, 380, 120, 50);
		plus1.setBounds(180, 130, 30, 30);
		plus2.setBounds(180, 200, 30, 30);
		minus.setBounds(180, 270, 30, 30);
		atkplus.setBounds(210, 120, 60, 50);
		hpplus.setBounds(210, 190, 60, 50);
		goldminus.setBounds(210, 260, 60, 50);

		add(atkup);
		add(hpup);
		add(nupg);
		add(nstatus);
		add(natk);
		add(nhp);
		add(ngold);
		add(natkv);
		add(nhpv);
		add(ngoldv);
		add(apply);
		add(cancel);
		add(plus1);
		add(plus2);
		add(minus);
		add(atkplus);
		add(hpplus);
		add(goldminus);
		
		add(new Picture()); ////////이미지 넣는곳 ////////

		setVisible(true);

		atkup.addActionListener(this);
		hpup.addActionListener(this);
		apply.addActionListener(this);
		cancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int nowatk, nowhp, nowgold;
		int changeatk, changehp , changegold;
		String str = natkv.getText().trim(); // 공격력
		String str2 = nhpv.getText().trim(); // 체력
		String str3 = ngoldv.getText().trim(); // 골드
		String str4 = atkplus.getText().trim();
		String str5 = hpplus.getText().trim();
		String str6 = goldminus.getText().trim();
		String i = null; //int 타입으로 바꾼 변수를 String 타입으로 다시 담을 변수
		String s = null; //int 타입으로 바꾼 변수를 String 타입으로 다시 담을 변수
		String a = null;
		String b = null;
		Object obj = e.getSource();
		nowatk = Integer.parseInt(str);
		nowhp = Integer.parseInt(str2);
		nowgold = Integer.parseInt(str3);
		changeatk = Integer.parseInt(str4);
		changehp = Integer.parseInt(str5);
		changegold = Integer.parseInt(str6);

		if (obj == atkup) {
			if (nowgold > changegold) {
				/*nowatk += 50;
				nowgold -= 100;
				i = Integer.toString(nowatk);
				s = Integer.toString(nowgold);*/
				
				changeatk += 50;
				changegold += 100;
				a = Integer.toString(changeatk);
				b = Integer.toString(changegold);
				
				atkplus.setText(a);
				goldminus.setText(b);

			} else {
				System.out.println("업그레이드 불가!");
			}
		} else if (obj == hpup) {
			if (nowgold > changegold) {
			/*	nowhp += 100;
				nowgold -= 100;
				i = Integer.toString(nowhp);
				s = Integer.toString(nowgold);*/
				changehp += 100;
				changegold +=100;
				a = Integer.toString(changehp);
				b = Integer.toString(changegold);
				hpplus.setText(a);
				goldminus.setText(b);
			}else {
				System.out.println("업그레이드 불가");
			}
		}else if (obj == cancel){
			atkplus.setText("0");
			hpplus.setText("0");
			goldminus.setText("0");
			
		}else if (obj == apply){
			nowatk += changeatk;
			System.out.println(nowatk);
			nowhp += changehp;
			System.out.println(nowhp);
			nowgold -= changegold;
			System.out.println(nowgold);
			unit.setAttk(nowatk);
			unit.setGold(nowgold);
			unit.setHp(nowhp);
		}

	}
	@Override //Swing 에선 paint 대신 이걸 사용!
	public void paintComponents(Graphics g) {
		 g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
	     setOpaque(false);//투명하게
	     super.paint(g);

	}
	

}