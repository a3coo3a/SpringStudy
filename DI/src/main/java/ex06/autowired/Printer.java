package ex06.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class Printer {
	
	/*
	 * 
	 * @Autowired - 자동주입 명령
	 * 스프링 파일내에 찾는 것이 있다면 자동 주입
	 * 타입 -> 이름 순으로 찾음
	 * 타입으로 찾았는데 없다면 이름으로 찾음
	 * 
	 * */
	
	
	private Document document;
	
	//생성자
	@Autowired
	public Printer(Document document) {
		this.document = document;
	}

	
	// getter, setter
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	

}
