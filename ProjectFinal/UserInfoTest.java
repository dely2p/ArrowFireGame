package ProjectFinal;

import java.util.ArrayList;

public class UserInfoTest {
	public static void main(String[] args) {
		UserInfoDAO dao = new UserInfoDAO();
		
		ArrayList<UserInfoVO> vo = dao.selectAll();
		
		for(UserInfoVO v : vo){
			System.out.println("----------------");
			System.out.println("계정: " + v.getUserId());
			System.out.println("비밀번호: " + v.getUserPwd());
			System.out.println("점수: " + v.getScore());
		}
		
/*		UserInfoVO vo = dao.selectOne("jsang");
		System.out.println("----------------");
		System.out.println("계정: " + vo.getUserId());
		System.out.println("비밀번호: " + vo.getUserPwd());
		System.out.println("점수: " + vo.getScore());*/
		
		//dao.insertOne(new UserInfoVO("sangwon","1234"));
	}
}
