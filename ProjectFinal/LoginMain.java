package ProjectFinal;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginMain extends JFrame implements ActionListener {
	// jpLo."login" ȭ���� �����̳�//
	JButton jbtL, jbtJ;
	JTextField jtfLID;
	JPasswordField jtfLPW;
	JButton jbLID, jbLPW;
	JPanel jpLo, jpJo;
	// jpJo."join" ȭ���� �����̳�//
	JButton jbtC, jbtO, jbJID, jbJPW, jbJRPW;
	JTextField jtfJID, jtfJPW, jtfJRPW;
	JLabel jlJM;
	// ī�� ���̾ƿ� ���� ����!//
	CardLayout layout;

	static String userId;
	
	//���� �г� �����Ű�� ���� ����//
	Start start = null;
	Game game = null;
	Rank rank = null;
	StoreShop store = null;
	
	/*JScrollPane scrollPane;
	ImageIcon icon;
	*/

	public LoginMain() {
		super("ȯ���մϴ�.");
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension ds = tool.getScreenSize();
		// int w = (int)ds.getWidth();
		// int h = (int)ds.getHeight();

		int x = (int) ds.getWidth() / 2 - 250;
		int y = (int) ds.getHeight() / 2 - 200;

		setBounds(x, y, 583, 325);

		layout = new CardLayout();
		jbtL = new JButton("����");
		jbtJ = new JButton("ȸ������");
		jtfLID = new JTextField();
		jtfLPW = new JPasswordField();
		jbLID = new JButton("ID");
		jbLPW = new JButton("PW");
		jpLo = new JPanel();
		jpJo = new JPanel();

		jbtC = new JButton("Ȯ ��");
		jbtO = new JButton("������");
		jtfJID = new JTextField();
		jtfJPW = new JTextField();
		jtfJRPW = new JTextField();
		jbJID = new JButton("ID");
		jbJPW = new JButton("PW");
		jbJRPW = new JButton("RPW:");
		jlJM = new JLabel("ȸ������");

		jpLo.setLayout(null);
		jpJo.setLayout(null);
		jpLo.setBounds(x, y, 500, 400);
		jpJo.setBounds(x, y, 500, 400);

		setLayout(layout);
		jbtL.setBounds(100, 200, 100, 40);
		jbtJ.setBounds(270, 200, 100, 40);
		jtfLID.setBounds(170, 80, 200, 45);
		jtfLPW.setBounds(170, 130, 200, 45);
		jbLID.setBounds(100, 80, 70, 45);
		jbLPW.setBounds(100, 130, 70, 45);

		jbtC.setBounds(100, 200, 100, 40);
		jbtO.setBounds(270, 200, 100, 40);
		jtfJID.setBounds(170, 50, 200, 45);
		jtfJPW.setBounds(170, 100, 200, 45);
		jtfJRPW.setBounds(170, 150, 200, 45);
		jbJID.setBounds(100, 50, 70, 45);
		jbJPW.setBounds(100, 100, 70, 45);
		jbJRPW.setBounds(100, 150, 70, 45);
		jlJM.setBounds(170, 50, 250, 70);

		jpLo.add(jbtL);
		jpLo.add(jbtJ);
		jpLo.add(jtfLID);
		jpLo.add(jtfLPW);
		jpLo.add(jbLID);
		jpLo.add(jbLPW);

		jpJo.add(jbtC);
		jpJo.add(jbtO);
		jpJo.add(jtfJID);
		jpJo.add(jtfJPW);
		jpJo.add(jtfJRPW);
		jpJo.add(jbJID);
		jpJo.add(jbJPW);
		jpJo.add(jbJRPW);
		jpJo.add(jlJM);

		add(jpLo, "login");
		add(jpJo, "join");
		
		jpLo.add(new Picture()); 
		jpJo.add(new Picture()); /////////*�̺κ��� ����̹��� �ִ� �κ�*/////////

		layout.show(getContentPane(), "login");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		jbtL.addActionListener(this);
		jbtJ.addActionListener(this);
		jbtC.addActionListener(this);
		jbtO.addActionListener(this);
	}
	
	public void change(JPanel panelname){
		getContentPane().removeAll();
		getContentPane().add(panelname);
		revalidate();
		repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		UserInfoVO userVO;
		UserInfoDAO userDao = new UserInfoDAO();
		
		if (obj == jbtJ) { // ȸ������ ��ư�� ������!
			layout.show(getContentPane(), "join"); // join panel�� ������!!
		} else if (obj == jbtO) { // ������ ��ư�� ������
			layout.show(getContentPane(), "login"); // login panel�� ������!!

		} else if (obj == jbtL) { // ���� ��ư�� Ŭ����!

			String id = jtfLID.getText();
			String pw = String.valueOf(jtfLPW.getPassword()); // id �� pw �� TextField�� ���� ���ͼ�!

			userVO = userDao.selectOne(id);
			
			if(userVO == null){
				JOptionPane.showMessageDialog(this, "���̵� �����", "����", JOptionPane.OK_OPTION);
				jtfLID.getText();jtfLID.setText("");
				jtfLPW.getPassword();jtfLPW.setText("");
			}else if(userVO.getUserPwd().equals(pw)){
				userId = userVO.getUserId();
				change(new Start(this));
		}

		} else if (obj == jbtC) {
			if (/* jtfJID.getText() && */jtfJPW.getText()
					.equals(jtfJRPW.getText()/* ����ȭ�鿡�� PW�� RPW�� ������ */)
					&& !jtfJPW.getText().isEmpty() /* PW �ؽ�Ʈ �� �ƹ��͵� ���°� �ƴҶ� */
					&& !jtfJRPW.getText().isEmpty() /* RPW �ؽ�Ʈ �� �ƹ��͵� ���°� �ƴҶ� */) {
				
				userDao.insertOne(new UserInfoVO(jtfJID.getText(), jtfJPW.getText()));
				
				JOptionPane.showMessageDialog(this, "ȸ�����Կ� �����ϼ̽��ϴ�", "����", JOptionPane.OK_OPTION);
				if (JOptionPane.OK_OPTION == 0) {
					layout.show(getContentPane(), "login");
				}
			} else if (jtfJPW.getText() != jtfJRPW
					.getText() /* jtfJID �� ������ DB�� ������ ���и� �ְ�ʹ�!! */) {
				JOptionPane.showMessageDialog(this, "ȸ�����Կ� �����ϼ̽��ϴ�.", "����", JOptionPane.OK_OPTION);
				if (JOptionPane.OK_OPTION == 0) {

					jtfJPW.setText("");
					jtfJRPW.setText("");
					jtfJID.setText("");
				}
			}
		}

	}

	public static void main(String[] args) {
		new LoginMain();
	}
}
