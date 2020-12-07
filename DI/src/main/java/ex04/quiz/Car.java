package ex04.quiz;

public class Car {
	private IBattery battery;  // 모든 건전지를 받을 수 있는 
	
	// 생성자
	public Car(IBattery battery) {
		this.battery = battery;
	}
	
	//getter
	public IBattery getBattery() {
		return battery;
	}
}
