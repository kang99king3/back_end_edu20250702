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
		 	☆☆☆☆★
		    ☆☆☆★★★
		    ☆☆★★★★★
		    ☆★★★★★★★
		    ★★★★★★★★★
		    	
		 */
		
	}//main종료

}






