package ex03.setter;

public class MemberDAO {
	private DatabaseDev ds;
	
	//setter
	public void setDs(DatabaseDev ds) {
		this.ds = ds;
	}
	
	public void info() {
		System.out.println("DB 주소 : " + ds.getUrl());
		System.out.println("DB 아이디 : " + ds.getUid());
		System.out.println("DB 비밀번호 : " + ds.getUpw());
	}
}
