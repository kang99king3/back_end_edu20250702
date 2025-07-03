package hk.edu20250702.day01;//최상단에 작성

//명명법, 식별자 확인
//클래스명: 파스칼 (첫글자를 대문자로 시작)
public class HelloJava {// H 첫글자를 대문자로 사용

	//main메서드: 자바코드를 실행시켜줌
	public static void main(String[] args) {
		
		System.out.println("java테스트:Hello JAVA!!!");
		System.out.println("java는 컴파일 언어이다");
		
		testMethod();
		
		HelloJava hello=new HelloJava();//new이용한 객체생성
		hello.testMethod2();
	}

	//메서드 선언
	//메서드명 정의: 카멜방식 - 소문자로 시작 , 의미가 바뀌면 대문자
	public static void testMethod() {
		//변수명 정의: 카멜방식
		boolean isS=true;
		int i= 100;//선언할때 타입을 정의
		i=200;//사용할때 타입정의X
		System.out.println("메서드 실행결과:"+i);
	}
	//블럭변수: 변수의 사용범위(scope)
	public void testMethod2() {
		int i=10;
		if(i<15) {
			int ii=5;
			ii=ii+i;
		}
//		i=ii+i;//오류발생 ii변수는 하위블럭에서 선언한 변수임
	}
}




