package hk.edu20250707.day04;

import java.util.Iterator;

public class D1_StarView {

	public static void main(String[] args) {
		/*
		    ★ 
		 	★★
		 	★★★
		 	★★★★
		 	★★★★★
		 	a0+n*d = 1+i  (i는 n을 의미함)
		 */
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i+1; j++) {
				System.out.print("★");				
			}
			System.out.println();
		}
		
		/*
			☆☆☆☆★       공백을 출력한 다음 별을 출력
			☆☆☆★★       공백: 4 3 2 1 0 
			☆☆★★★           a0+i*d -> 4+i*-1= 4-i
			☆★★★★       별:  1 2 3 4 5
			★★★★★           i+1
		 */
		System.out.println();
		for (int i = 0; i < 5; i++) {
			//공백
			for (int j = 0; j < 4-i; j++) {
				System.out.print("☆");
			}
			//별출력
			for (int j = 0; j < i+1; j++) {
				System.out.print("★");
			}
			System.out.println();//줄바꿈만 실행
		}
		
		/*
		 	☆☆☆☆★           공백: 4-i
		    ☆☆☆★★★      
		    ☆☆★★★★★         별출력:  1 3 5 7 9 
		    ☆★★★★★★★             1+i*2
		    ★★★★★★★★★
		    	
		 */
		System.out.println();
		for (int i = 0; i < 5; i++) {
			//공백
			for (int j = 0; j < 4-i; j++) {
				System.out.print("☆");
			}
			//별출력
			for (int j = 0; j < 1+i*2; j++) {
				System.out.print("★");
			}
			System.out.println();//줄바꿈만 실행
		}
		
		/*  ★★★★★★★★★	
		 	☆★★★★★★★
		 	☆☆★★★★★
		 	☆☆☆★★★
		 	☆☆☆☆★
		 */
		System.out.println();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("☆");
			}
			for (int j = 0; j < 9-i*2; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		/*
		 	★★★★★        
		 	★★★★       
		 	★★★             
		 	★★               
		 	★
		 */
		/*
	 	★★★★★        
	 	 ★★★★       
	 	  ★★★             
	 	   ★★               
	 	    ★
	    */
		/*  마름모 출력
	        ★
	 	   ★★★
	 	  ★★★★★
	 	 ★★★★★★★
	 	★★★★★★★★★
	 	 ★★★★★★★
	 	  ★★★★★
	 	   ★★★
	 	    ★
	    */
		System.out.println();
		int number=7;//변수 설정하여 구현
		for (int i = 0; i < number; i++) {
			//공백
			for (int j = 0; j < number-1-i; j++) {
				System.out.print("☆");
			}
			//별출력
			for (int j = 0; j < 1+i*2; j++) {
				System.out.print("★");
			}
			System.out.println();//줄바꿈만 실행
		}
		
		for (int i = 0; i < number-1; i++) {
			for (int j = 0; j < i+1; j++) {
				System.out.print("☆");
			}
			for (int j = 0; j < ((number-1)*2-1)-i*2; j++) {   //  (5-1)*2-1
				System.out.print("★");
			}
			System.out.println();
		}
		
		System.out.println("--------------------------");
		for (int i = 0; i < 5; i++) {
			//공백
			for (int j = 0; j < 4-i; j++) {
				System.out.print("☆");
			}
			//별출력
			for (int j = 0; j < 1+i*2; j++) {
				System.out.print("★");
			}
			System.out.println();//줄바꿈만 실행
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < i+1; j++) {
				System.out.print("☆");
			}
			for (int j = 0; j < (4*2-1)-i*2; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
		//절대값 활용해서 마름모 구현
		System.out.println("==========================");
		int num=7;//홀수값만 가능함
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < Math.abs(num/2-i) ; j++) {
				System.out.print("☆");
			}
			for (int j = 0; j < num-Math.abs(num-1-i*2); j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
	}//main종료

}






