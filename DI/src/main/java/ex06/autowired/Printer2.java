package ex06.autowired;

import javax.annotation.Resource;

public class Printer2 {
	
	/*
	 * @Resource는 이름 -> 타입순으로 찾는다
	 * - 세터, 멤버변수에만 적용이 가능합니다.
	 * */
	@Resource
	private Document document;
	
	//생성자
	public Printer2() {}
	public Printer2(Document document) {
		super();
		this.document = document;
	}
	
	//setter, getter
	
	public void setDocument(Document document) {
		this.document = document;
	}
	public Document getDocument() {
		return document;
	}
	
	
}	
