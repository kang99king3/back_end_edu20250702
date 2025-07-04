package hk.edu20250704.day03;

public class D1_ControlFlow {

	public static void main(String[] args) {
		
		//if문
		//유형:3가지정도...
		//1.if(조건){실행코드}
		//2.if(조건){실행코드}else{실행코드}
		//3.if(조건){실행코드}else if(조건){실행코드}else if(조건){실행코드}....
		//4.중첩if문
		
		int num1 = 10;
		int num2 = 5;
		
		//if문을 독립적으로 사용했을 경우 : if문끼리는 영향이 없다.
		if(num1>num2) {
			System.out.println("실행결과:"+num1+">"+num2);
		}
		
		if(num1<num2) {
			System.out.println("실행결과:"+num1+"<"+num2);
		}
		
		//조건이 true나 false 결과에 따라 반드시 하나만 실행됨
		if(num1>num2) {
			System.out.println("실행결과:"+num1+">"+num2);
		}else {
			System.out.println("실행결과:"+num1+"<"+num2);
		}
		
		//switch case문: 대상값이 정수형, String 타입
		//실행 코드다음에 break를 작성하지 않으면 해당 case 이후에 case문들이 모두 실행된다.
		int num=5;
		switch (num) {
		case 1: System.out.println("1입니다.");break;
		case 5: System.out.println("5입니다.");break;
		case 10: System.out.println("10입니다.");break;
		default:
			System.out.println("일치하는 값이 없습니다.");break;
		}
	}

}




