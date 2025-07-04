package hk.edu20250704.day03;

import java.util.Random;
import java.util.Scanner;

public class D2_ControlEX {

	public static void main(String[] args) {
		
		//구구단 출력하기
		//2단을 출력하는 코드
		for (int i = 1; i < 10; i++) {
			// 2X1=2, 2X2=4...
//			System.out.println("2X"+i+"="+2*i);
			System.out.printf("2X%d=%d\n",i,2*i);
//			System.out.println();//줄만 바꿔줌
		}
		
		//2~9단 출력하기
		for (int i = 2; i < 10; i++) {
			System.out.println(i+"단");
			for (int j = 1; j < 10; j++) {
				System.out.printf("%dX%d=%d\n",i,j,i*j);
			}
			System.out.println("------------------");
		}
		
		//2~9단까지 출력할때.. 짝수단만 출력해보기
		for (int i = 2; i < 10; i++) {
			if(i%2==0) {//짝수 조건
				System.out.println(i+"단");
				for (int j = 1; j < 10; j++) {
					System.out.printf("%dX%d=%d\n",i,j,i*j);
				}
				System.out.println("------------------");				
			}
		}
		//2~9단까지 출력할때.. 홀수단만 출력해보기
		for (int i = 2; i < 10; i++) {
			if(i%2!=0) {//홀수 조건
				System.out.println(i+"단");
				for (int j = 1; j < 10; j++) {
					System.out.printf("%dX%d=%d\n",i,j,i*j);
				}
				System.out.println("------------------");				
			}
		}
		//1~100까지의 수의 합을 출력하자
		// 1+2+3+4 .....+100 총합
		int sum=0;// 합계를 저장할 변수 선언
		for (int i = 1; i <= 100; i++) {
//			sum=sum+i;
			sum+=i;//단축연산자
		}
		System.out.println("1~100까지의 총합:"+sum);
		
		//1~100까지의 수 중에 4의 배수의 총합을 출력하기
		int sum2=0;// 합계를 저장할 변수 선언
		for (int i = 1; i <= 100; i++) {
			if(i%4==0) {//4의 배수 조건
	//			sum2=sum+i;
				sum2+=i;//단축연산자
			}
		}
		System.out.println("1~100까지 중 4의 배수의 총합:"+sum2);
		
		//주사위 두개의 합이 5이면 실행을 멈추고, 
		//5가 아니면 계속 실행되게 코드 작성하자
		//1~6까지의 숫자로 구성, 랜덤하게 숫자 생성 
		//  ---> Math객체를 활용, 어떤 메서드를 사용하면 될까.. 
		//       Random객체를 활용
		
		//Math객체에 random()메서드를 사용
		// random()에서 생성되는 숫자 --> 0~1사이의 실수를 랜덤하게 생성
		System.out.println(Math.random());
		int number =(int)(Math.random()*6)+1;// 1~6까지 랜던숫자반환
		System.out.println(number);
		
		Random random=new Random();
		System.out.println(random.nextInt(6)+1);
		
		while(true) {
			int num1=(int)(Math.random()*6)+1;//1번주사위
			int num2=(int)(Math.random()*6)+1;//2번주사위
			System.out.printf("(%d,%d)\n",num1,num2);
			if(num1+num2==5) {
				System.out.println("합이 5가 되어서 종료할께요~~");
				break;
			}
		}
		
		//뱅킹시스템
		
		Scanner scan = new Scanner(System.in);
		
		int balance=0;
		
		while(true) {
			System.out.println("----------------------");
			System.out.println("1.예금|2.출금|3.잔고|4.종료");
			System.out.println("----------------------");
			System.out.print("선택>");
			
			int input=0;
			if(scan.hasNextInt()) {// hasNextInt는 줄바꿈을 읽지 않음, 그래서 "abcd\n"이 남아 있어서 계속 입력되는 현상발생
				input=Integer.parseInt(scan.nextLine());				
			}else {	
				System.out.println("숫자만 입력하세요");
				scan.nextLine();//남아 있는 한줄을 제거하고 새롭게 입력을 받을 준비를 함
				continue;
			}
			
			if(input==1) {//예금
				System.out.print("예금액>");
				int a=Integer.parseInt(scan.nextLine());
				balance+=a;
				System.out.println("입금완료했습니다.");
			}else if(input==2) {//출금
				System.out.print("출금액>");
				int a=Integer.parseInt(scan.nextLine());
				if(balance>a) {//잔액이 부족한지 충분한지 확인
					balance-=a;					
				}else {
					System.out.println("잔액이 부족합니다.");
					continue;
				}
				System.out.println("출금완료했습니다.");
			}else if(input==3) {//조회
				System.out.printf("잔고:%d원입니다.\n",balance);
			}else if(input==4) {
				System.out.println("종료합니다.");
				break;
			}
			
		}
		
		
		
	}
	
	

}




