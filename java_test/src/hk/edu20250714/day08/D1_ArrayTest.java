package hk.edu20250714.day08;

import java.util.Arrays;

import hk.edu20250707.day04.D1_StarView;

public class D1_ArrayTest {

	//멤버필드에 선언
	public int [] test;//선언
	public int [][] test2;
	
	//생성자를 통해 배열 정의
	public D1_ArrayTest() {
//		test=new int[3];//정의
//		test2=new int[3][3];//정의
		this(3,3);// 중복된 초기화 코드가 있다면 this()사용하면 됨
	}
	//생성자 오버로딩을 통해 배열길이 정의
	public D1_ArrayTest(int n) {
		test=new int[n];
		test2=new int[n][n];
	}
	
	public D1_ArrayTest(int m,int n) {
		test=new int[n];
		test2=new int[m][n];
	}
	
	public static void main(String[] args) {
		//선언 방법
		//1.리터럴 방식: 기본타입처럼 선언하는 방법
		int[] a= {1,2,3,4,5};//바로 선언과 동시에 값도 초기화해야함
		int[] b;//선언과 초기화를 따로 진행할 수 없음
//			  b= {1,2,3,4,5};//(X)
			  b=a;//주소복사(얕은복사)
			  b[0]=10;
			  System.out.println(Arrays.toString(a));
		
		//값을 가져오는 방법: 인덱스를 통해서 가져온다.
		int val=a[0];//값을 가져오기
		a[0]=10;//값을 저장하기
		
		//2.new를 사용해서 정의
		int[] b2;
			  b2=new int[] {1,2,3,4,5};
	    
	    int[] b3=new int[5];
	    //값 초기화
	    for (int i = 0; i < b3.length; i++) {
			b3[i]=i+1;
		}
	    //값 가져와서 출력
	    for (int i = 0; i < b3.length; i++) {
			System.out.println(b[i]);
		}
	    b3[2]=10;
	    //Arrays 클래스: toString()사용해봤음
	    Arrays.sort(b3);//정렬함--> mutable하기때문에 원본(b3)내용 바뀜
	    System.out.println(Arrays.toString(b3));
	    
	    //immutable함--> 다시 대입해야 원본이 변경
	    String s="ss";
	    s=s.replace("s", "p");
	    System.out.println(s);
	    
	    //shallow copy(얕은 복사:주소복사)
	    int[] c= {1,2,3,4,5,6};
	    int[] d=c;//주소 전달
	    d[0]=10;//복사 받은 쪽에서 값을 바꾸면 원본(c)도 바뀐다.
	    System.out.println(Arrays.toString(c));
	    
	    //deep copy(깊은 복사:값을 복사)
	    int[]e=new int[6];
	    for (int i = 0; i < e.length; i++) {
			e[i]=c[i];
		}
	    e[3]=50;
	    System.out.println(Arrays.toString(c));
	    
	  //값이 참조타입일 경우 주소복사-> shallow copy가 됨
//	    D1_StarView[]f=new D1_StarView[5];
	    //[주소1, 주소2, 주소3..]
	    
	    //배열의 값이 기본타입을 경우 적용됨
	    System.arraycopy(c, 0, e, 0, c.length);
	    e[1]=100;
	    System.out.println(Arrays.toString(c));
	    
	    //참조타입 배열: Person 클래스 작성 --> [person,person...]
	    
	}

}














