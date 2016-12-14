package ProjectFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Start extends JPanel implements ActionListener {
	LoginMain Login;
	JButton startbtn, rankbtn;
	
	public Start(LoginMain Login) {
		this.Login = Login;
		setLayout(null);
		setBounds(0, 0, 800, 600);
		//setBackground(Color.yellow);
		
		startbtn = new JButton("START");
		rankbtn = new JButton("RANK");
		
		startbtn.setBounds(200, 50, 100, 40);
		rankbtn.setBounds(200, 150, 100, 40);	
		
		add(startbtn);
		add(rankbtn);
		add(new Picture()); /////////이미지 넣는 곳/////////////
		
		startbtn.addActionListener(this);
		rankbtn.addActionListener(this);
		setVisible(true);	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==startbtn){
			Login.change(new Game(Login));
			//win = new TestWin();
			//win.change(new Game(win));
		}else if(obj==rankbtn){
			Login.change(new Rank(Login));
			//win.change(new Rank(win));
		}
	}

}
