package ex06.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Printer {
	
	/*
	 * 
	 * @Autowired - 자동주입 명령
	 * 스프링 파일내에 찾는 것이 있다면 자동 주입
	 * 타입 -> 이름 순으로 찾음
	 * 타입으로 찾았는데 없다면 이름으로 찾음
	 * 생성자, 세터, 멤버변수에 사용이 가능합니다
	 * */
	
	/* @Autowired(required = false)  
	 * false : 스프링이 주입할 빈이 없으면 자동으로 에러르 발생시키는데,
	 * 이를 무시하고 지나가 주세요 라는 속성
	 * */
	
	/*@Qualifier("id명")
	 * 컨테이너 안에 객체가 여러개 있을 때 id이름으로 강제 연결해주는 이노테이션
	 * 
	 * */
	
	@Autowired(required = false)  
	@Qualifier("doc2")   // doc1이름과 연결해주세요
	private Document document;
	
	//생성자
	public Printer() {}   // 기본생성자는 습관적으로 만들어 버릇해야 함. 없을시 문제가 생기는 경우가 종종 있음.
	public Printer(Document document) {
		super();
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
