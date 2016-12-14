package ProjectFinal;

public class UserInfoVO {
	private String userId;
	private String userPwd;
	private int score;
		
	/* Constructor */
	public UserInfoVO(){}
	public UserInfoVO(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.score = 0;
	}
	public UserInfoVO(String userId, String userPwd, int score) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.score = score;
	}
	/* Get/Set Method */
	public String getUserId(){return userId;}
	public void setUserId(String userId){this.userId = userId;}
	public String getUserPwd(){return userPwd;}
	public void setUserPwd(String userPwd){this.userPwd = userPwd;}
	public int getScore(){return score;}
	public void setScore(int score){this.score = score;}
}
