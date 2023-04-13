import java.io.Serializable;

public class President implements Serializable {
	private String memberID;
	private String memberPW;
	private String userName;
	private boolean loginStatus;
	public String getmemberID() {
		return memberID;
	}
	public void setmemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getmemberPW() {
		return memberPW;
	}
	public void setmemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public President(String memberID, String memberPW, String userName) {
		super();
		this.memberID = memberID;
		this.memberPW = memberPW;
		this.userName = userName;
	}
	
}
