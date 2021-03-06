package ex03.setter;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		// MemberDAO가 DatabaseDev에 의존적
//		DatabaseDev db = new DatabaseDev();
//		db.setUrl("database address");
//		db.setUid("db id");
//		db.setUpw("db passward");
//		
//		MemberDAO dao = new MemberDAO();
//		dao.setDs(db);
//		
//		dao.info();
		
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext("application-context.xml");
		
		DatabaseDev db = ctx.getBean(DatabaseDev.class);
		System.out.println(db.getUrl());
		
		MemberDAO dao = ctx.getBean(MemberDAO.class);
		dao.info();
		
		
		
	}
}
