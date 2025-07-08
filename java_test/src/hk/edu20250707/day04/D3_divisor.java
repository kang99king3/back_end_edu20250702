package hk.edu20250707.day04;

public class D3_divisor {

	//default 생성자: 파라미터가 없음 --> 생략 가능
	public D3_divisor() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		//약수구하는 메서드를 사용할때
		//원하는 값을 요청하고 싶다면... 메서드에 값을 전달해야됨
//		divisor(30);
		D3_divisor divisor=new D3_divisor();
		divisor.divisor(12);
		int a=divisor.greateDivisor(50, 85);
		divisor.lowestMultiple(2, 4);
		divisor.amicable(1, 4000);
		divisor.perfectNumber(1, 1000);
	}
	
	//약수를 구하는 메서드 
	public void divisor(int number) {
		for (int i = 1; i <= number; i++) {
			if(number%i==0) {
				System.out.print((i==number)?i:i+",");//삼항연산자를 활용
			}
		}
		System.out.println();
	}
	
	//최대공약수 구하는 메서드 
	public int greateDivisor(int a, int b) {
		//값을 복사해서 할당함. immutable 특징: 변경되지 않는 성질 --> 기본타입의 특징
		int tempA=a;
		int tempB=b;
		
		while(true) {
			//a가 b보다 클 경우
			if(a>b) {
				a=a-b;
			}
			//b가 a보다 클 경우
			if(b>a) {
				b=b-a;
			}
			//a와 b가 같을 경우
			if(a==b) {
				break;
			}
		}
		
		System.out.printf("%d와%d의 최대공약수는%d입니다.\n",tempA,tempB,a);
		
		return a;
	}
	
	//최소공배수 구하는 매서드 : a*b/(a,b이 최대공약수)
	public void lowestMultiple(int a, int b) {
		//최대공약수 구하기
		int number=greateDivisor(a, b);//재사용성(반복적으로 사용되는 기능)
		int result=a*b/number;
		System.out.printf("%d와 %d의 최소공배수는 %d입니다.\n",a,b,result);
	}
	
	//진약수의 합을 구하는 메서드
	public int sumDivisor(int a) {
		int sum=0;//합을 저장하는 변수
		for (int i = 1; i < a; i++) {
			if(a%i==0) {
				sum+=i;
			}
		}
		return sum;
	}
	
	//친화수 구하기
	public void amicable(int s, int e) {
		for (int i = s; i < e; i++) {
			if(i!=sumDivisor(i) && i==sumDivisor(sumDivisor(i))) {
				System.out.printf("%d와%d는 친화수 관계입니다.\n",i,sumDivisor(i));
			}
		}
	}
	
	//완전수를 구하는 메서드 : 진약수의 합과 자신의 수가 같은 경우
	//  1, 2 , 3 == 6
	public void perfectNumber(int s, int e) {
	
		for (int i = s; i < e; i++) {
			if(i==sumDivisor(i)) {
				System.out.println(i+"는 완전수입니다.");
			}
		}
	}
}








