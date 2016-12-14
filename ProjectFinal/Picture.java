package ProjectFinal;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Picture extends JPanel{
	
	public Picture(){
		super();
		initialize();
		
	}

	public void initialize(){ 
		this.setSize(600,500); //�׸� ũ�� ���ϱ�
		this.setLayout(new GridBagLayout());
	}
	
	public void paintComponent(Graphics g) {
		Image image = new ImageIcon("src/Image/background.png").getImage();
		                            /*�׸� ���� �ִ°�!*/
		  /*jpLo.add(new Pictuer());*/ /*<--�̰�ó�� �̹��� �ִ°��� add�� �����ָ��!*/
		g.drawImage(image, 0, 0, this);
	};
}
