package hk.edu20250708.day05;

public class D1_ClassTestMain {

	public static void main(String[] args) {
		//참조타입      객체명             생성자()
		D1_ClassTest classTest = new D1_ClassTest();// Heap메모리에 생성
		classTest.methodTest();//객체명.메서드() 호출
		classTest.number=10;
		
		D1_ClassTest.staticMethodTest();//클래스명.메서드() 호출
		
		//또 만들 수 있다
		D1_ClassTest classTest2 = new D1_ClassTest();
		classTest2.methodTest();
		classTest2.number=20;
		
		//인스턴스 맴버필드: 객체 각각에 대해 관리되고 있다. 서로 영향이 없다.
		System.out.println(classTest.number+":"+classTest2.number);
		
		//클래스 맴버필드
		classTest.staticNumber=30;
		System.out.println(classTest2.staticNumber);
	}
}





