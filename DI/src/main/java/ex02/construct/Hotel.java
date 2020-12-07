package ex02.construct;

public class Hotel {
	// Hotel이 Chef의 의존적이다
	// Hotel은 Chef가 없다면 정상 가동이 어려우므로
	
	
	// 멤버변수
	private Chef chef;
	
	// 생성자
	public Hotel(Chef chef) {
		this.chef = chef;
	}
	
	// getter
	public Chef getChef() {
		return chef;
	}
}
