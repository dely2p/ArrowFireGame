package ProjectFinal;

import java.util.ArrayList;

public class UserInfoTest {
	public static void main(String[] args) {
		UserInfoDAO dao = new UserInfoDAO();
		
		ArrayList<UserInfoVO> vo = dao.selectAll();
		
		for(UserInfoVO v : vo){
			System.out.println("----------------");
			System.out.println("����: " + v.getUserId());
			System.out.println("��й�ȣ: " + v.getUserPwd());
			System.out.println("����: " + v.getScore());
		}
		
/*		UserInfoVO vo = dao.selectOne("jsang");
		System.out.println("----------------");
		System.out.println("����: " + vo.getUserId());
		System.out.println("��й�ȣ: " + vo.getUserPwd());
		System.out.println("����: " + vo.getScore());*/
		
		//dao.insertOne(new UserInfoVO("sangwon","1234"));
	}
}
