package ProjectFinal;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Rank_Backup extends JPanel implements ActionListener{
	LoginMain login;
	Container con;
	JPanel rankPanel;
	JPanel myStatPanel;
	JPanel confirmPanel;
	JLabel titleLabel;
	JButton rankColumn;
	JButton userIdColumn;
	JButton scoreColumn;
	JLabel[] rankLabel = new JLabel[9];
	JLabel[] idLabel = new JLabel[9];
	JLabel[] scoreLabel = new JLabel[9];
	JButton myStatIdColumn;
	JButton myStatScoreColumn;
	JLabel myStatId;
	JLabel myStatScore;
	JButton confirm;
		
	Border border = LineBorder.createGrayLineBorder();
	
	public Rank_Backup(LoginMain login) {
		this.login = login;
		setLayout(null);
		/*int x = (int) ds.getWidth() / 2 - 250;
		int y = (int) ds.getHeight() / 2 - 200;

		setBounds(x, y, 500, 400);*/
		
		/* LoginPanel's Panel */
		titleLabel = new JLabel("RANK", JLabel.CENTER);
		rankPanel = new JPanel();
		myStatPanel = new JPanel();
		confirmPanel = new JPanel();		

		titleLabel.setBounds(100, 0, 300, 25);
		rankPanel.setBounds(0, 25, 250, 300);
		myStatPanel.setBounds(250, 25, 250, 300);
		confirmPanel.setBounds(0, 325, 500, 35);
		
/*		//rankPanel.setBackground(Color.blue);
		myStatPanel.setBackground(Color.red);
		confirmPanel.setBackground(Color.yellow);*/
		
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
		userIdColumn.setBounds(0+70,0,90,30);
		scoreColumn.setBounds(0+70+90,0,90,30);
		
		UserInfoDAO userDao = new UserInfoDAO();
		ArrayList<UserInfoVO> userVo = userDao.selectAll();

		// rankLabel 초기화
		int labelY = 30;
		for(int i=0;i<9;i++){
			rankLabel[i] = new JLabel(""+(i+1), JLabel.CENTER);
			rankLabel[i].setBounds(0,labelY,70,30);
			rankLabel[i].setBorder(border);
			rankPanel.add(rankLabel[i]);
			labelY+=30;
		}

		// idLabel 초기화
		labelY = 30;
		for(int i=0;i<9;i++){
			if(i<userVo.size()){
				idLabel[i] = new JLabel(userVo.get(i).getUserId(), JLabel.CENTER);
			}else{
				idLabel[i] = new JLabel("-", JLabel.CENTER);
			}
			
			idLabel[i].setBounds(0+70,labelY,90,30);
			idLabel[i].setBorder(border);
			rankPanel.add(idLabel[i]);
			labelY+=30;
		}

		// scoreLabel 초기화
		labelY = 30;
		for(int i=0;i<9;i++){	
			if(i<userVo.size()){
				scoreLabel[i] = new JLabel(String.valueOf(userVo.get(i).getScore()), JLabel.CENTER);
			}else{
				scoreLabel[i] = new JLabel("-", JLabel.CENTER);
			}
			scoreLabel[i].setBounds(0+70+90,labelY,90,30);
			scoreLabel[i].setBorder(border);
			rankPanel.add(scoreLabel[i]);
			labelY+=30;
		}
		
		/* myStatPanel's Button&Label&Image */
		myStatPanel.setLayout(null);
		myStatIdColumn = new JButton("ID");
		myStatScoreColumn = new JButton("Score");		
		myStatId = new JLabel(LoginMain.userId, JLabel.CENTER);
		myStatScore = new JLabel(String.valueOf(userDao.selectOne(LoginMain.userId).getScore()), JLabel.CENTER);
		
		myStatIdColumn.setBounds(0,240,120,30);
		myStatScoreColumn.setBounds(0+120,240,120,30);
		myStatId.setBounds(0,270,120,30);
		myStatScore.setBounds(0+120,270,120,30);
		myStatId.setBorder(border);
		myStatScore.setBorder(border);
		
		myStatPanel.add(myStatIdColumn);
		myStatPanel.add(myStatScoreColumn);		
		myStatPanel.add(myStatId);
		myStatPanel.add(myStatScore);
		
		/* confirmPanel's Button */
		confirmPanel.setLayout(null);
		confirm = new JButton("확인");
		confirm.setBounds(100,5,300,25);
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
