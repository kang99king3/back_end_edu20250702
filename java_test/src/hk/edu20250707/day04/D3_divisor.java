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
	
}








