package ProjectFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfoDAO{
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.50:1521:orcl";
	private String user = "scott";
	private String password = "tiger";
	private Connection conn;
	private PreparedStatement pstmt;
	private StringBuffer sb = new StringBuffer();
	private ResultSet rs;
	
	/* Constructor */
	public UserInfoDAO(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e){
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	/* Member Method */
	// selectAll Method Start
	// 전체 검색.
	public ArrayList<UserInfoVO> selectAll(){
		ArrayList<UserInfoVO> list = new ArrayList<UserInfoVO>();	
		sb.setLength(0);
		sb.append("SELECT userid, userpwd, score FROM userInfo ORDER BY score DESC");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String userId = rs.getString("userid");
				String userPwd = rs.getString("userpwd");
				int score = rs.getInt("score");
				UserInfoVO vo = new UserInfoVO(userId, userPwd, score);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	// 특정 컬럼의 값 검색.
	public UserInfoVO selectOne(String id){
		UserInfoVO vo = null;
		sb.setLength(0);
		sb.append("SELECT userid, userpwd, score FROM userInfo WHERE userid = ?");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String userId = rs.getString("userid");
				String userPwd = rs.getString("userpwd");
				int score = rs.getInt("score");
				vo = new UserInfoVO(userId, userPwd, score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(vo==null) System.out.println("사용자가 없습니다.");
		return vo;
	}
	
	public void insertOne(UserInfoVO vo){
		sb.setLength(0);
		sb.append("INSERT INTO userinfo ");
		sb.append("VALUES (?,?,0)");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPwd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOne(UserInfoVO vo){
		sb.setLength(0);
		sb.append("UPDATE userinfo");
		sb.append("SET userpwd = ? ");
		sb.append("WHERE userid = ?");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUserPwd());
			pstmt.setString(2, vo.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {

		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
