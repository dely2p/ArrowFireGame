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
		this.setSize(600,500); //그림 크기 정하기
		this.setLayout(new GridBagLayout());
	}
	
	public void paintComponent(Graphics g) {
		Image image = new ImageIcon("src/Image/background.png").getImage();
		                            /*그림 파일 넣는곳!*/
		  /*jpLo.add(new Pictuer());*/ /*<--이것처럼 이미지 넣는곳에 add만 시켜주면됨!*/
		g.drawImage(image, 0, 0, this);
	};
}
