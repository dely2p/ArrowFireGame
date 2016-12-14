package ProjectFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Rank extends JPanel implements ActionListener{
	LoginMain login;
	Container con;
	JPanel rankPanel;
	JPanel myStatPanel;
	JPanel confirmPanel;
	JLabel titleLabel;
	JButton rankColumn;
	JButton userIdColumn;
	JButton scoreColumn;
	JLabel[] rankLabel = new JLabel[6];
	JLabel[] idLabel = new JLabel[6];
	JLabel[] scoreLabel = new JLabel[6];
	JButton myStatIdColumn;
	JButton myStatScoreColumn;
	JLabel myStatId;
	JLabel myStatScore;
	JButton confirm;
		
	Border border = LineBorder.createGrayLineBorder();
	
	public Rank(LoginMain login) {
		this.login = login;
		setLayout(null);

		
		/* LoginPanel's Panel */
		titleLabel = new JLabel("RANK", JLabel.CENTER);
		rankPanel = new JPanel();
		myStatPanel = new JPanel();
		confirmPanel = new JPanel();		

		titleLabel.setBounds(100, 0, 380, 25);
		/*
		rankPanel.setBounds(0, 25, 250, 300);
		myStatPanel.setBounds(250, 25, 250, 300);
		confirmPanel.setBounds(0, 325, 500, 35);*/
		
		rankPanel.setBounds(0, 25, 291, 210);
		myStatPanel.setBounds(291, 25, 291, 265);
		confirmPanel.setBounds(0, 235, 291, 55);

		
		add(rankPanel);
		add(titleLabel);
		add(myStatPanel);
		add(confirmPanel);
		
		/* rankLabel's Button&Label */
		rankPanel.setLayout(null);
		rankColumn = new JButton("RANK");
		userIdColumn = new JButton("ID");
		scoreColumn = new JButton("SCORE");
		
		rankColumn.setBounds(0,0,70,30);
		userIdColumn.setBounds(0+70,0,100,30);
		scoreColumn.setBounds(0+70+100,0,120,30);
		
		UserInfoDAO userDao = new UserInfoDAO();
		ArrayList<UserInfoVO> userVo = userDao.selectAll();

		// rankLabel 초기화
		int labelY = 30;
		for(int i=0;i<6;i++){
			rankLabel[i] = new JLabel(""+(i+1), JLabel.CENTER);
			rankLabel[i].setBounds(0,labelY,70,30);
			rankLabel[i].setBorder(border);
			rankPanel.add(rankLabel[i]);
			labelY+=30;
		}

		// idLabel 초기화
		labelY = 30;
		for(int i=0;i<6;i++){
			if(i<userVo.size()){
				idLabel[i] = new JLabel(userVo.get(i).getUserId(), JLabel.CENTER);
			}else{
				idLabel[i] = new JLabel("-", JLabel.CENTER);
			}
			
			idLabel[i].setBounds(0+70,labelY,100,30);
			idLabel[i].setBorder(border);
			rankPanel.add(idLabel[i]);
			labelY+=30;
		}

		// scoreLabel 초기화
		labelY = 30;
		for(int i=0;i<6;i++){	
			if(i<userVo.size()){
				scoreLabel[i] = new JLabel(String.valueOf(userVo.get(i).getScore()), JLabel.CENTER);
			}else{
				scoreLabel[i] = new JLabel("-", JLabel.CENTER);
			}
			scoreLabel[i].setBounds(0+70+100,labelY,120,30);
			scoreLabel[i].setBorder(border);
			rankPanel.add(scoreLabel[i]);
			labelY+=30;
		}
		
		/* myStatPanel's Button&Label&Image */
		myStatPanel.setLayout(null);
		myStatIdColumn = new JButton("MY ID");
		myStatScoreColumn = new JButton("MY Score");		
		myStatId = new JLabel(LoginMain.userId, JLabel.CENTER);
		myStatScore = new JLabel(String.valueOf(userDao.selectOne(LoginMain.userId).getScore()), JLabel.CENTER);
		
		myStatIdColumn.setBounds(0,210,146,25);
		myStatScoreColumn.setBounds(0+146,210,146,25);
		myStatId.setBounds(0,235,146,30);
		myStatScore.setBounds(0+146,235,146,30);
		myStatId.setBorder(border);
		myStatScore.setBorder(border);
		
		myStatPanel.add(myStatIdColumn);
		myStatPanel.add(myStatScoreColumn);		
		myStatPanel.add(myStatId);
		myStatPanel.add(myStatScore);
		
		/* confirmPanel's Button */
		confirmPanel.setLayout(null);
		confirm = new JButton("확인");
		confirm.setBounds(20,15,251,25);
		confirmPanel.add(confirm);
		
		/* add Panel */
		rankPanel.add(rankColumn);
		rankPanel.add(userIdColumn);
		rankPanel.add(scoreColumn);
		
		confirm.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		login.change(new Start(login));
	}
}
