package hk.edu20250710.day07;

public class D1_StringMethodTest {

	//String 주요 메서드 연습
	
	//1.문자 하나를 반환
	// "문자열에서 문자하나를 인덱스로 추출하는 기능" --> "".chatAt(6)-> '문'
	//charAt(index)
	public String sTest01(String s,int idx) {
		char c=s.charAt(idx);
		String ss=c+"";//문자열을 만나면 문자열이 된다.
		String ss2=String.valueOf(c);//문자열로 변환시켜주는 메서드
		
		//예시) 숫자형태의 문자열을 숫자로 변환
		int i=Integer.parseInt("100");//"100" --> 100
		
		return ss;
	}
	
	//2.문자열에서 검색하려는 단어의 인덱스를 반환하는 기능: indexOf()
	// "ABCD" -> "BC" 검색 ->  "ABCD".indexOf("BC")
	// 반환값은??  해당 단어의 첫번째 인덱스를 반환
	// 종류: indexOf(), lastIndexOf() -->차이점: 앞에서부터, 뒤에서부터 검색
	//     0 1 2 3            3 2 1 0 X
	public void sTest2(String s) {//파라미터 -> "ABCDEF"
		int s1=s.indexOf("AB");//단어를 처음부터 검색
		int s2=s.indexOf("C",2);// 검색 시작 인덱스도 지정 가능
		int s3=s.indexOf("DF",2,5);
		int s4=s.lastIndexOf("F");
		
		System.out.printf("%d,%d,%d,%d\n",s1,s2,s3,s4);
		
		//해당 단어가 존재하는지 확인하는 용도
		//해당 단어가 없으면 -1을 반환한다.
		if(s.indexOf("A")!=-1) { // 문자열에서 해당 단어가 존재한다면..
			System.out.println("A가 존재합니다.");
		}
	}
	
	//3.문자열 길이 반환: length()
	public int sTest03(String s) {
		return s.length();
	}
	
	//4.문자열의 내용 변환 : replace("원본","새로운 내용")
	public void sTest04() {
		String s="자바프로그래밍자바웹개발자바스크립트";
		s.replace("자바", "java");//원본 s의 내용이 바뀌지 않는다 (immutable)
		s=s.replace("자바", "java");//다시 대입해야 된다.
		System.out.println(s);
	}
}










