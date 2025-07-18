package hk.edu20250718.day12;

//추상메서드를 포함하려면 추상 클래스로 정의해야 된다.
public abstract class D1_Computer {

	//어떤 종류의 컴퓨터라도 모두 전원켜고,끄는 기능은 있다. --> 공통기능
	//전원 켜고, 끄는 기능들을 미리 구현함
	public void turnOn() {
		System.out.println("전원을 켭니다.");
	}
	
	public void turnOff() {
		System.out.println("전원을 끕니다.");
	}
	
	//display 기능: 컴퓨터 유형별로 다르기 때문에 여기서 구현을 못함
	public abstract void display();//추상메서드를 포함하려면 추상클래스가 선언되어야 한다.
	//typing 기능: 컴퓨터 유형별로 다르기 때문에 여기서 구현을 못함
	//-> 하위클래스에서 구현하도록 상속 강요
	public abstract void typing();//추상메서드
}




